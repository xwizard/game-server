package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.repository.SessionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionServiceImplTest {

  private SessionServiceImpl service;
  private SessionRepository sessionRepository;
  private ScheduledExecutorService scheduler;

  @Before
  public void before() {
    sessionRepository = mock(SessionRepository.class);
    scheduler = mock(ScheduledExecutorService.class);

    service = new SessionServiceImpl(sessionRepository, scheduler);
  }

  @Test
  public void loginShouldReturnSessionId() {
    UUID actual = service.login("user");
    assertNotNull(actual);
  }

  @Test
  public void loginShouldscheduleExpiry() {
    service.login("user");
    verify(scheduler).schedule(any(Runnable.class), eq(10L), eq(TimeUnit.MINUTES));
  }

  @Test
  public void loginShouldSaveSession() {
    service.login("user");
    verify(sessionRepository).save(any(Session.class));
  }
}