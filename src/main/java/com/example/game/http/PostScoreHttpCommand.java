package com.example.game.http;

public class PostScoreHttpCommand implements HttpCommand {

  private final Integer levelId;
  private final String sessionId;

  private PostScoreHttpCommand(String sessionId, Integer levelId, Integer score) {
    this.levelId = levelId;
    this.score = score;
    this.sessionId = sessionId;
  }

  private final Integer score;

  public static HttpCommand of(String sessionId, String levelId, Integer score) {
    return null;
  }


  @Override
  public CommandResult execute() {
    return null;
  }
}
