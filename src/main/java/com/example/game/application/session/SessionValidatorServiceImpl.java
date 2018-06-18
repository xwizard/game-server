package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

public class SessionValidatorServiceImpl implements SessionValidatorService {

  private final static Logger LOG = Logger.getLogger(SessionValidatorServiceImpl.class.getCanonicalName());

  private final SessionRepository sessionRepository;

  public SessionValidatorServiceImpl(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  @Override
  public Optional<Integer> getUserId(SessionId sessionId) {
    Optional<Session> session = sessionRepository.get(sessionId);
    if (!session.isPresent()) {
      LOG.fine("No session for id: " + sessionId);
      return Optional.empty();
    }
    if (session.get().expired(LocalDateTime.now())) {
      LOG.fine("Session expired: " + sessionId);
      return Optional.empty();
    }

    return Optional.of(session.get().getUserId());
  }
}
