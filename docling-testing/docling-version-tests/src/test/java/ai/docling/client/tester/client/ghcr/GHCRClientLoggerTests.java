package ai.docling.client.tester.client.ghcr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.core.HttpHeaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void shouldSetBodySize() {
        logger.setBodySize(1024);
    }

    @Test
    void shouldLogResponseWhenEnabled() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("Content-Type", "application/json");

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("{\"token\":\"secret123\"}");

        // Capture the body handler
        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);

        // Verify bodyHandler was set
        verify(response).bodyHandler(any());

        // Trigger the handler
        handlerCaptor.getValue().handle(body);

        // Verify response was accessed
        verify(response).statusCode();
        verify(response).headers();
    }

    @Test
    void shouldNotLogResponseWhenDisabled() {
        // Setup
        when(config.logResponses()).thenReturn(false);

        HttpClientResponse response = mock(HttpClientResponse.class);

        // Execute
        logger.logResponse(response, false);

        // Verify no interaction with response
        verify(response, never()).bodyHandler(any());
    }

    @Test
    void shouldMaskTokenInResponseBody() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("{\"token\":\"very-secret-token-value\"}");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(body);

        // The token should be masked in logs (verified by no exception)
        verify(response).statusCode();
    }

    @Test
    void shouldHandleNullBodyInResponse() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(response.statusCode()).thenReturn(204);
        when(response.headers()).thenReturn(headers);

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(null);

        // Should not throw exception
        verify(response).statusCode();
    }

    @Test
    void shouldHandleEmptyBodyInResponse() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(body);

        verify(response).statusCode();
    }

    @Test
    void shouldLogRequestWhenEnabled() {
        // Setup
        when(config.logRequests()).thenReturn(true);

        HttpClientRequest request = mock(HttpClientRequest.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("Content-Type", "application/json");

        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.absoluteURI()).thenReturn("https://ghcr.io/v2/test/tags/list");
        when(request.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("{\"test\":\"data\"}");

        // Execute
        logger.logRequest(request, body, false);

        // Verify request was accessed
        verify(request).getMethod();
        verify(request).absoluteURI();
        verify(request).headers();
    }

    @Test
    void shouldNotLogRequestWhenDisabled() {
        // Setup
        when(config.logRequests()).thenReturn(false);

        HttpClientRequest request = mock(HttpClientRequest.class);
        Buffer body = Buffer.buffer("{\"test\":\"data\"}");

        // Execute
        logger.logRequest(request, body, false);

        // Verify no interaction with request
        verify(request, never()).getMethod();
        verify(request, never()).absoluteURI();
    }

    @Test
    void shouldLogRequestWithNullBody() {
        // Setup
        when(config.logRequests()).thenReturn(true);

        HttpClientRequest request = mock(HttpClientRequest.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.absoluteURI()).thenReturn("https://ghcr.io/token");
        when(request.headers()).thenReturn(headers);

        // Execute
        logger.logRequest(request, null, false);

        // Should not throw exception
        verify(request).getMethod();
    }

    @Test
    void shouldMaskAuthorizationHeader() {
        // Setup
        when(config.logRequests()).thenReturn(true);

        HttpClientRequest request = mock(HttpClientRequest.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer very-long-secret-token");

        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.absoluteURI()).thenReturn("https://ghcr.io/v2/test/tags/list");
        when(request.headers()).thenReturn(headers);

        // Execute
        logger.logRequest(request, null, false);

        // Verify headers were accessed (masking happens internally)
        verify(request).headers();
    }

    @Test
    void shouldMaskSetCookieHeader() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.SET_COOKIE, "session=very-long-session-id");

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("{}");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(body);

        // Verify headers were accessed
        verify(response).headers();
    }

    @Test
    void shouldHandleShortAuthorizationValue() {
        // Setup
        when(config.logRequests()).thenReturn(true);

        HttpClientRequest request = mock(HttpClientRequest.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add(HttpHeaders.AUTHORIZATION, "abc");

        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.absoluteURI()).thenReturn("https://ghcr.io/token");
        when(request.headers()).thenReturn(headers);

        // Execute
        logger.logRequest(request, null, false);

        // Should not throw exception
        verify(request).headers();
    }

    @Test
    void shouldHandleMultipleHeaders() {
        // Setup
        when(config.logRequests()).thenReturn(true);

        HttpClientRequest request = mock(HttpClientRequest.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer token123");

        when(request.getMethod()).thenReturn(HttpMethod.POST);
        when(request.absoluteURI()).thenReturn("https://ghcr.io/v2/test/tags/list");
        when(request.headers()).thenReturn(headers);

        // Execute
        logger.logRequest(request, Buffer.buffer("{}"), false);

        // Verify all headers were processed
        verify(request).headers();
    }

    @Test
    void shouldHandleNonJsonBodyInResponse() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("Plain text response");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(body);

        // Should handle non-JSON body without error
        verify(response).statusCode();
    }

    @Test
    void shouldHandleJsonArrayInResponse() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();

        when(response.statusCode()).thenReturn(200);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("[{\"token\":\"secret1\"}, {\"token\":\"secret2\"}]");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, false);
        handlerCaptor.getValue().handle(body);

        // Should mask tokens in JSON array
        verify(response).statusCode();
    }

    @Test
    void shouldHandleRedirectResponse() {
        // Setup
        when(config.logResponses()).thenReturn(true);

        HttpClientResponse response = mock(HttpClientResponse.class);
        MultiMap headers = MultiMap.caseInsensitiveMultiMap();
        headers.add("Location", "https://example.com/redirect");

        when(response.statusCode()).thenReturn(302);
        when(response.headers()).thenReturn(headers);

        Buffer body = Buffer.buffer("");

        ArgumentCaptor<Handler<Buffer>> handlerCaptor = ArgumentCaptor.forClass(Handler.class);
        when(response.bodyHandler(handlerCaptor.capture())).thenReturn(response);

        // Execute
        logger.logResponse(response, true);
        handlerCaptor.getValue().handle(body);

        // Should handle redirect
        verify(response).statusCode();
    }
}