package com.example.game.http;

import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionService;


public class LoginHttpCommand implements HttpCommand {
  private final Integer userId;
  private final SessionService sessionService;

  private LoginHttpCommand(SessionService sessionService, Integer userId) {
    this.userId = userId;
    this.sessionService = sessionService;
  }

  public static HttpCommand of(SessionService sessionService, Integer userId, HttpMethod method) {
    if (method != HttpMethod.GET) return InvalidHttpCommand.of("Only GET is allowed for login");

    if (userId < 0) {
      return InvalidHttpCommand.of(String.format("Invalid user id: %d", userId));
    }

    return new LoginHttpCommand(sessionService, userId);
  }

  public Integer getUserId() {
    return userId;
  }

  @Override
  public CommandResult execute() {
    SessionId sessionId = sessionService.login(userId);
    return LoginCommandResult.of(sessionId);
  }

  @Override
  public String toString() {
    return "LoginHttpCommand{" +
        "userId=" + userId +
        '}';
  }
}
