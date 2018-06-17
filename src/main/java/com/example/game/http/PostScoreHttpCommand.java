package com.example.game.http;

import com.example.game.application.session.SessionId;

public class PostScoreHttpCommand implements HttpCommand {

  private final Integer levelId;
  private final String sessionId;

  private PostScoreHttpCommand(String sessionId, Integer levelId, Integer score) {
    this.levelId = levelId;
    this.score = score;
    this.sessionId = sessionId;
  }

  private final Integer score;

  public static HttpCommand of(Integer levelId, SessionId sessionId, Integer score) {
    return null;
  }


  @Override
  public CommandResult execute() {
    return null;
  }
}
