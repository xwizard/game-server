package com.example.game.http;

public class InvalidHttpCommand implements HttpCommand {
  public final String message;

  private InvalidHttpCommand(String message) {
    this.message = message;
  }
}
