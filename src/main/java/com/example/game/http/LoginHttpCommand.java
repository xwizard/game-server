package com.example.game.http;

public class LoginHttpCommand implements HttpCommand {
  private final Integer userId;

  private LoginHttpCommand(Integer userId, HttpMethod method) {
    this.userId = userId;
  }

  public static HttpCommand of(String unparsedUserId, HttpMethod method) {
    if (method != HttpMethod.GET) return InvalidHttpCommand.of("Only GET is allowed for login");

    try {
      Integer userId = Integer.parseInt(unparsedUserId);
      return new LoginHttpCommand(userId, method);
    } catch (NumberFormatException e) {
      return InvalidHttpCommand.of(String.format("Invalid user id: %s", unparsedUserId));
    }
  }

  public Integer getUserId() {
    return userId;
  }
}
