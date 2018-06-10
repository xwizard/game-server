package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.repository.SessionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionServiceImpl implements SessionService {
  private final SessionRepository sessionRepository;
  private final ScheduledExecutorService scheduler;

  public SessionServiceImpl(SessionRepository sessionRepository, ScheduledExecutorService scheduler) {
    this.sessionRepository = sessionRepository;
    this.scheduler = scheduler;
  }

  @Override
  public UUID login(Integer userId) {
    LocalDateTime now = LocalDateTime.now();
    Session session = Session.createFor(userId, now);

    sessionRepository.save(session);
    scheduler.schedule(new ExpireSessionTask(), ChronoUnit.MINUTES.between(now, session.getExpiryDate()), TimeUnit.MINUTES);

    return session.getId();
  }
}
