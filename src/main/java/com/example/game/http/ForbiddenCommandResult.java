package com.example.game.http;

public class ForbiddenCommandResult implements CommandResult {

  private final String message;

  private ForbiddenCommandResult(String message) {
    this.message = message;
  }

  public static ForbiddenCommandResult of(String message) {
    return new ForbiddenCommandResult(message);
  }

  @Override
  public String toResponse() {
    return message;
  }

  @Override
  public int httpStatus() {
    return 403;
  }
}