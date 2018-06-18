package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.example.game.application.score.PostUserScoreService;
import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionValidatorService;

import java.util.Optional;

public class PostScoreHttpCommand implements HttpCommand {

  private final Integer levelId;
  private final SessionId sessionId;

  private final SessionValidatorService sessionValidatorService;
  private final PostUserScoreService postUserScoreService;

  private PostScoreHttpCommand(SessionValidatorService sessionValidatorService, PostUserScoreService postUserScoreService,
                               Integer levelId, SessionId sessionId, Integer score) {
    this.levelId = levelId;
    this.postUserScoreService = postUserScoreService;
    this.score = score;
    this.sessionId = sessionId;
    this.sessionValidatorService = sessionValidatorService;
  }

  private final Integer score;

  public static HttpCommand of(ApplicationContext ctx, Integer levelId, SessionId sessionId, Integer score) {
    return new PostScoreHttpCommand(ctx.sessionValidatorService(), ctx.postUserScoreService(), levelId, sessionId, score);
  }


  @Override
  public CommandResult execute() {
    Optional<Integer> userId = sessionValidatorService.getUserId(sessionId);
    if (!userId.isPresent()) {
      return ForbiddenCommandResult.of("Session has expired for sessionId: " + sessionId);
    }

    postUserScoreService.post(userId.get(), levelId, score);

    return new CommandResult() {
      @Override
      public String toResponse() {
        return "";
      }

      @Override
      public int httpStatus() {
        return 201;
      }
    };
  }

  @Override
  public String toString() {
    return "PostScoreHttpCommand{" +
        "levelId=" + levelId +
        ", sessionId=" + sessionId +
        ", score=" + score +
        '}';
  }
}
