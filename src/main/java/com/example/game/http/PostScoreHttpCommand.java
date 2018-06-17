package com.example.game.http;

import com.example.game.application.session.SessionId;

public class PostScoreHttpCommand implements HttpCommand {

  private final Integer levelId;
  private final SessionId sessionId;

  private PostScoreHttpCommand(Integer levelId, SessionId sessionId, Integer score) {
    this.levelId = levelId;
    this.score = score;
    this.sessionId = sessionId;
  }

  private final Integer score;

  public static HttpCommand of(Integer levelId, SessionId sessionId, Integer score) {
    return new PostScoreHttpCommand(levelId, sessionId, score);
  }


  @Override
  public CommandResult execute() {
    return null;
  }
}
