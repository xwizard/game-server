package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.repository.SessionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionValidatorServiceImplTest {
  private final static SessionId SESSION_ID = SessionId.random();
  private final static SessionId EXPIRED_SESSION_ID = SessionId.random();

  private SessionValidatorService service;

  @Before
  public void before() {
    SessionRepository sessionRepository = mock(SessionRepository.class);
    Session session = Session.createFor(1, LocalDateTime.now());
    Session expiredSession = Session.createFor(1, LocalDateTime.of(1990, 1, 1, 1, 1, 1));
    when(sessionRepository.get(any(SessionId.class))).then(new Answer<Optional<Session>>() {
      @Override
      public Optional<Session> answer(InvocationOnMock invocationOnMock) {
        SessionId sessionId = invocationOnMock.getArgument(0);
        if (SESSION_ID.equals(sessionId)) {
          return Optional.of(session);
        }
        if (EXPIRED_SESSION_ID.equals(sessionId)) {
          return Optional.of(expiredSession);
        }
        return Optional.empty();
      }
    });

    service = new SessionValidatorServiceImpl(sessionRepository);
  }

  @Test
  public void getUserIdShouldReturnUserId() {
    Optional<Integer> actual = service.getUserId(SESSION_ID);
    assertTrue(actual.isPresent());
    assertEquals(Integer.valueOf(1), actual.get());
  }

  @Test
  public void getUserIdShouldReturnEmptyOptionalForExpiredSession() {
    Optional<Integer> actual = service.getUserId(EXPIRED_SESSION_ID);
    assertFalse(actual.isPresent());
  }

  @Test
  public void getUserIdShouldReturnEmptyOptionalForNoSession() {
    Optional<Integer> actual = service.getUserId(SessionId.random());
    assertFalse(actual.isPresent());
  }
}