package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.example.game.application.HighScoreListService;
import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionValidatorService;
import com.example.game.core.UserHighScore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HighScoreHttpCommand implements HttpCommand {

  private final SessionValidatorService sessionValidatorService;
  private final HighScoreListService highScoreListService;

  private final SessionId sessionId;
  private final Integer levelId;

  private HighScoreHttpCommand(SessionValidatorService sessionValidatorService, HighScoreListService highScoreListService, SessionId sessionId, Integer levelId) {
    this.sessionValidatorService = sessionValidatorService;
    this.highScoreListService = highScoreListService;
    this.sessionId = sessionId;
    this.levelId = levelId;
  }

  public static HttpCommand of(ApplicationContext ctx, SessionId sessionId, Integer levelId, HttpMethod method) {
    if (method != HttpMethod.GET) return InvalidHttpCommand.of("Only GET is allowed for login");
    return new HighScoreHttpCommand(ctx.sessionValidatorService(), ctx.highScoreListService(), sessionId, levelId);
  }

  @Override
  public CommandResult execute() {
    Optional<Integer> userId = sessionValidatorService.getUserId(sessionId);
    if (!userId.isPresent()) {
      return ForbiddenCommandResult.of("Session has expired for sessionId: " + sessionId);
    }

    List<UserHighScore> highScores = highScoreListService.listHighScores(userId.get(), levelId);

    return new CommandResult() {
      @Override
      public String toResponse() {
        return String.join(",", highScores.stream().map(UserHighScore::toString).collect(Collectors.toList()));
      }

      @Override
      public int httpStatus() {
        return 200;
      }
    };
  }
}
