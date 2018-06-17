package com.example.game.http;

import com.example.game.application.session.SessionId;

public class LoginCommandResult implements CommandResult {

  private final SessionId sessionId;

  private LoginCommandResult(SessionId sessionId) {
    this.sessionId = sessionId;
  }

  public static LoginCommandResult of(SessionId sessionId) {
    return new LoginCommandResult(sessionId);
  }

  @Override
  public String toResponse() {
    return toString();
  }

  @Override
  public int httpStatus() {
    return 200;
  }

  @Override
  public String toString() {
    return sessionId.toString();
  }

  public SessionId getSessionId() {
    return sessionId;
  }
}
