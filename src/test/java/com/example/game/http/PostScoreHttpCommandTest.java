package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.example.game.application.score.PostUserScoreService;
import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionValidatorService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PostScoreHttpCommandTest {
  private final static SessionId SESSION_ID = SessionId.random();

  private SessionValidatorService sessionValidatorService;
  private PostUserScoreService postUserScoreService;
  private ApplicationContext applicationContext;

  @Before
  public void before() {
    sessionValidatorService = mock(SessionValidatorService.class);
    when(sessionValidatorService.getUserId(eq(SESSION_ID))).thenReturn(Optional.of(4));
    postUserScoreService = mock(PostUserScoreService.class);
    applicationContext = mock(ApplicationContext.class);
    when(applicationContext.sessionValidatorService()).thenReturn(sessionValidatorService);
    when(applicationContext.postUserScoreService()).thenReturn(postUserScoreService);
  }

  @Test
  public void executeShouldPassToService() {
    HttpCommand command = PostScoreHttpCommand.of(applicationContext, 1, SESSION_ID, 3);
    command.execute();
    verify(postUserScoreService).post(4, 1, 3);
  }
}