package com.example.game.http;

import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionValidatorService;

import java.util.Optional;

public class PostScoreHttpCommand implements HttpCommand {

  private final Integer levelId;
  private final SessionId sessionId;

  private final SessionValidatorService sessionValidatorService;

  private PostScoreHttpCommand(SessionValidatorService sessionValidatorService,
                               Integer levelId, SessionId sessionId, Integer score) {
    this.levelId = levelId;
    this.score = score;
    this.sessionId = sessionId;
    this.sessionValidatorService = sessionValidatorService;
  }

  private final Integer score;

  public static HttpCommand of(SessionValidatorService sessionValidatorService,
                               Integer levelId, SessionId sessionId, Integer score) {
    return new PostScoreHttpCommand(sessionValidatorService, levelId, sessionId, score);
  }


  @Override
  public CommandResult execute() {
    Optional<Integer> userId = sessionValidatorService.getUserId(sessionId);
    if (!userId.isPresent()) {
      return ForbiddenCommandResult.of("Session has expired for sessionId: " + sessionId);
    }
    return null;
  }
}
