package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.repository.SessionRepository;

import java.util.Optional;
import java.util.logging.Logger;

public class ExpireSessionTask implements Runnable {

  private final static Logger LOG = Logger.getLogger(ExpireSessionTask.class.getCanonicalName());

  private final SessionRepository sessionRepository;
  private final SessionId sessionId;

  public ExpireSessionTask(SessionRepository sessionRepository, SessionId id) {
    this.sessionRepository = sessionRepository;
    this.sessionId = id;
  }

  @Override
  public void run() {
    LOG.fine("Invalidating session: "+ sessionId.toString());

    Optional<Session> session = sessionRepository.remove(sessionId);
    if (!session.isPresent()) {
      LOG.warning("Session was already invalidated: " + sessionId.toString());
    }
  }
}
