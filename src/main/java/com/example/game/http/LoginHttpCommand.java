package com.example.game.http;

public class LoginHttpCommand implements HttpCommand {
  private final Integer userId;

  private LoginHttpCommand(Integer userId, HttpMethod method) {
    this.userId = userId;
  }

  public static HttpCommand of(Integer userId, HttpMethod method) {
    return new LoginHttpCommand(userId, method);
  }
}
