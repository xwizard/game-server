package com.example.game.http;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Poor man's error handling, just returns stack trace in response body.
 */
public class ApplicationErrorResult implements CommandResult {
  private final String error;

  private ApplicationErrorResult(String error) {
    this.error = error;
  }

  public static CommandResult of(RuntimeException e) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    return new ApplicationErrorResult(stringWriter.toString());
  }

  @Override
  public String toResponse() {
    return error;
  }

  @Override
  public int httpStatus() {
    return 500;
  }
}
