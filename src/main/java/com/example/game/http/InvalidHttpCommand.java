package com.example.game.http;

public class InvalidHttpCommand implements HttpCommand {
  private final String message;

  private InvalidHttpCommand(String message) {
    this.message = message;
  }

  public static HttpCommand of(String message) {
    return new InvalidHttpCommand(message);
  }

  @Override
  public CommandResult execute() {
    return BadRequestCommandResult.of(message);
  }

  @Override
  public String toString() {
    return "InvalidHttpCommand{" +
        "message='" + message + '\'' +
        '}';
  }
}
