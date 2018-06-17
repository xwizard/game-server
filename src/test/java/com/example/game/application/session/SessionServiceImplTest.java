package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.UserScore;
import com.example.game.core.repository.SessionRepository;
import com.example.game.core.repository.UserScoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SessionServiceImplTest {

  private SessionServiceImpl service;
  private SessionRepository sessionRepository;
  private UserScoreRepository userScoreRepository;
  private ScheduledExecutorService scheduler;

  @Before
  public void before() {
    sessionRepository = mock(SessionRepository.class);
    userScoreRepository = mock(UserScoreRepository.class);
    UserScore userScore = UserScore.create(10);
    when(userScoreRepository.get(anyInt())).then((Answer<Optional<UserScore>>) invocationOnMock -> invocationOnMock.getArgument(0).equals(10) ? Optional.of(userScore) : Optional.empty());
    scheduler = mock(ScheduledExecutorService.class);

    service = new SessionServiceImpl(sessionRepository, userScoreRepository, scheduler);
  }

  @Test
  public void loginShouldReturnSessionId() {
    SessionId actual = service.login(10);
    assertNotNull(actual);
  }

  @Test
  public void loginShouldscheduleExpiry() {
    service.login(10);
    verify(scheduler).schedule(any(Runnable.class), eq(10L), eq(TimeUnit.MINUTES));
  }

  @Test
  public void loginShouldSaveSession() {
    service.login(10);
    verify(sessionRepository).save(any(Session.class));
  }

  @Test
  public void loginShouldCreateNonExistingUserScore() {
    service.login(5);
    verify(userScoreRepository).save(any(UserScore.class));
  }

  @Test
  public void loginShouldNotCreateExistingUserScore() {
    service.login(10);
    verify(userScoreRepository, times(0)).save(any(UserScore.class));
  }
}