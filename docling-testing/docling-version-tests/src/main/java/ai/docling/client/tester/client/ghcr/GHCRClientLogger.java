package ai.docling.client.tester.client.ghcr;

import static java.util.stream.Collectors.joining;

import java.util.stream.StreamSupport;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;

import org.jboss.resteasy.reactive.client.api.ClientLogger;

import io.quarkus.logging.Log;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;

import ai.docling.client.tester.config.Config;

@ApplicationScoped
public class GHCRClientLogger implements ClientLogger {
  private final Config config;

  public GHCRClientLogger(Config config) {
    this.config = config;
  }

  @Override
  public void setBodySize(int bodySize) {
    // ignore
  }

  @Override
  public void logResponse(HttpClientResponse response, boolean redirect) {
    if (config.logResponses() && Log.isInfoEnabled()) {
      response.bodyHandler(body -> {
        try {
          Log.infof("""
              Response:
              - status code: %s
              - headers: %s
              - body: %s
              """,
              response.statusCode(),
              inOneLine(response.headers()),
              maskToken(body));
        } catch (Exception e) {
          Log.warn("Failed to log response", e);
        }
      });
    }
  }

  @Override
  public void logRequest(HttpClientRequest request, Buffer body, boolean omitBody) {
    if (config.logRequests() && Log.isInfoEnabled()) {
      try {
        Log.infof("""
            Request:
            - method: %s
            - url: %s
            - headers: %s
            - body: %s
            """,
            request.getMethod(),
            request.absoluteURI(),
            inOneLine(request.headers()),
            bodyToString(body));
      }
      catch (Exception e) {
        Log.warn("Failed to log request", e);
      }
    }
  }

  private static String bodyToString(Buffer body) {
    return (body != null) ? body.toString() : "";
  }

  private static String maskToken(Buffer body) {
    var bodyString = bodyToString(body);

    if ((bodyString == null) || bodyString.isEmpty()) {
      return bodyString;
    }

    try {
      // Try to parse as JSON and mask token field
      if (bodyString.trim().startsWith("{") || bodyString.trim().startsWith("[")) {
        return bodyString.replaceAll(
            "(\"token\"\\s*:\\s*\")([^\"]+)(\")",
            "$1***MASKED***$3"
        );
      }
      return bodyString;
    }
    catch (Exception e) {
      // If JSON parsing fails, return original body
      return bodyString;
    }
  }

  private static String inOneLine(MultiMap headers) {
    return StreamSupport.stream(headers.spliterator(), false)
        .map(header -> {
          String headerKey = header.getKey();
          String headerValue = header.getValue();

          headerValue = switch (headerKey) {
            case HttpHeaders.AUTHORIZATION, HttpHeaders.SET_COOKIE -> maskValue(headerValue);
            default -> headerValue;
          };

          return String.format("[%s: %s]", headerKey, headerValue);
        })
        .collect(joining(", "));
  }

  private static String maskValue(String value) {
    try {
      if (value.length() <= 4) {
        return value;
      }

      return value.substring(0, 2)
          + "..."
          + value.substring(value.length() - 2);
    }
    catch (Exception e) {
      return "Failed to mask the API key.";
    }
  }
}
