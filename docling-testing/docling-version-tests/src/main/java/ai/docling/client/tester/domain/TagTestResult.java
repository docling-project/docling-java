package ai.docling.client.tester.domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public record TagTestResult(String tag, Result result, String serverLogs) {
  public record Result(Status status, String message, String fullStackTrace) {
    public enum Status { SUCCESS, FAILURE }

    public static Result failure(String message, Throwable error) {
      return new Result(Status.FAILURE, message, getFullStackTrace(error));
    }

    public static Result failure(Throwable error) {
      return failure(error.getMessage(), error);
    }

    public static Result success(String message) {
      return new Result(Status.SUCCESS, message, null);
    }

    private static String getFullStackTrace(Throwable error) {
      if (error != null) {
        try (var sw = new StringWriter();
             var pw = new PrintWriter(sw)) {
          error.printStackTrace(pw);
          return sw.toString();
        }
        catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      return "";
    }
  }

  public TagTestResult(Builder builder) {
    this(builder.tag, builder.result, builder.serverLogs);
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String tag;
    private Result result;
    private String serverLogs;

    private Builder() {
    }

    private Builder(TagTestResult result) {
      this.tag = result.tag();
      this.result = result.result();
      this.serverLogs = result.serverLogs();
    }

    public Builder tag(String tag) {
      this.tag = tag;
      return this;
    }

    public Builder result(Result result) {
      this.result = result;
      return this;
    }

    public Builder serverLogs(String serverLogs) {
      this.serverLogs = serverLogs;
      return this;
    }

    public TagTestResult build() {
      return new TagTestResult(this);
    }
  }
}
