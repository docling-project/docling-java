package ai.docling.client.tester.client.ghcr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.core.HttpHeaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import ai.docling.client.tester.config.Config;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;

class GHCRClientLoggerTests {

    private Config config;
    private GHCRClientLogger logger;

    @BeforeEach
    void setUp() {
        config = mock(Config.class);
        logger = new GHCRClientLogger(config);
    }

    @Test
    void shouldNotLogResponseWhenDisabled() {
        when(config.logResponses()).thenReturn(false);
        HttpClientResponse response = mock(HttpClientResponse.class);

        logger.logResponse(response, false);

        verify(response, never()).bodyHandler(any());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{\"token\":\"secret123\"}",
            "{\"token\":\"very-secret-token-value\"}",
            "[{\"token\":\"secret1\"}, {\"token\":\"secret2\"}]",
            "Plain text response",
            ""
    })
    void shouldLogResponseBody(String rawBody) {
        when(config.logResponses()).thenReturn(true);
        MockedResponse mr = mockedResponse(200, MultiMap.caseInsensitiveMultiMap());

        logger.logResponse(mr.response, false);
        mr.triggerBody(Buffer.buffer(rawBody));

        verify(mr.response).statusCode();
        verify(mr.response).headers();
    }

    @Test
    void shouldHandleNullBodyInResponse() {
        when(config.logResponses()).thenReturn(true);
        MockedResponse mr = mockedResponse(204, MultiMap.caseInsensitiveMultiMap());

        logger.logResponse(mr.response, false);
        mr.triggerBody(null);

        verify(mr.response).statusCode();
    }

    @Test
    void shouldMaskSetCookieHeaderInResponse() {
        when(config.logResponses()).thenReturn(true);

        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.SET_COOKIE, "session=very-long-session-id");
        MockedResponse mr = mockedResponse(200, headers);

        logger.logResponse(mr.response, false);
        mr.triggerBody(Buffer.buffer("{}"));

        verify(mr.response).headers();
    }

    @Test
    void shouldNotLogRequestWhenDisabled() {
        when(config.logRequests()).thenReturn(false);
        HttpClientRequest request = mock(HttpClientRequest.class);

        logger.logRequest(request, Buffer.buffer("{\"test\":\"data\"}"), false);

        verify(request, never()).getMethod();
        verify(request, never()).absoluteURI();
    }

    @Test
    void shouldLogRequestWhenEnabled() {
        when(config.logRequests()).thenReturn(true);

        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("Content-Type", "application/json");
        HttpClientRequest request = mockedRequest(HttpMethod.GET, "https://ghcr.io/v2/test/tags/list", headers);

        logger.logRequest(request, Buffer.buffer("{\"test\":\"data\"}"), false);

        verify(request).getMethod();
        verify(request).absoluteURI();
        verify(request).headers();
    }

    @Test
    void shouldLogRequestWithNullBody() {
        when(config.logRequests()).thenReturn(true);
        HttpClientRequest request = mockedRequest(
                HttpMethod.GET, "https://ghcr.io/token", MultiMap.caseInsensitiveMultiMap());

        logger.logRequest(request, null, false);

        verify(request).getMethod();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Bearer very-long-secret-token", "abc" })
    void shouldMaskAuthorizationHeader(String authValue) {
        when(config.logRequests()).thenReturn(true);

        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.AUTHORIZATION, authValue);
        HttpClientRequest request = mockedRequest(
                HttpMethod.GET, "https://ghcr.io/v2/test/tags/list", headers);

        logger.logRequest(request, null, false);

        verify(request).headers();
    }

    private static class MockedResponse {
        final HttpClientResponse response;
        private final ArgumentCaptor<Handler<Buffer>> captor;

        MockedResponse(HttpClientResponse response, ArgumentCaptor<Handler<Buffer>> captor) {
            this.response = response;
            this.captor = captor;
        }

        void triggerBody(Buffer body) {
            captor.getValue().handle(body);
        }
    }

    @SuppressWarnings("unchecked")
    private MockedResponse mockedResponse(int statusCode, MultiMap headers) {
        HttpClientResponse response = mock(HttpClientResponse.class);
        when(response.statusCode()).thenReturn(statusCode);
        when(response.headers()).thenReturn(headers);

        ArgumentCaptor<Handler<Buffer>> captor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(captor.capture())).thenReturn(response);

        return new MockedResponse(response, captor);
    }

    private HttpClientRequest mockedRequest(HttpMethod method, String uri, MultiMap headers) {
        HttpClientRequest request = mock(HttpClientRequest.class);
        when(request.getMethod()).thenReturn(method);
        when(request.absoluteURI()).thenReturn(uri);
        when(request.headers()).thenReturn(headers);
        return request;
    }
}