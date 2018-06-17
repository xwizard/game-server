package com.example.game.http;

public class BadRequestCommandResult implements CommandResult {

  private final String message;

  private BadRequestCommandResult(String message) {
    this.message = message;
  }

  public static BadRequestCommandResult of(String message) {
    return new BadRequestCommandResult(message);
  }

  @Override
  public String toResponse() {
    return message;
  }

  @Override
  public int httpStatus() {
    return 400;
  }
}