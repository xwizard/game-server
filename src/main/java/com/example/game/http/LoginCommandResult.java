package com.example.game.http;

import java.util.UUID;

public class LoginCommandResult implements CommandResult {

  private final UUID sessionId;

  private LoginCommandResult(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public static LoginCommandResult of(UUID sessionId) {
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

  public UUID getSessionId() {
    return sessionId;
  }
}
