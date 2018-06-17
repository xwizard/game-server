package com.example.game.application.session;

import com.example.game.core.Session;
import com.example.game.core.UserScore;
import com.example.game.core.repository.SessionRepository;
import com.example.game.core.repository.UserScoreRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SessionServiceImpl implements SessionService {

  private final static Logger LOG = Logger.getLogger(SessionServiceImpl.class.getCanonicalName());

  private final SessionRepository sessionRepository;
  private final UserScoreRepository userScoreRepository;
  private final ScheduledExecutorService scheduler;

  public SessionServiceImpl(SessionRepository sessionRepository, UserScoreRepository userScoreRepository, ScheduledExecutorService scheduler) {
    this.sessionRepository = sessionRepository;
    this.userScoreRepository = userScoreRepository;
    this.scheduler = scheduler;
  }

  @Override
  public SessionId login(Integer userId) {
    Optional<UserScore> userScore = userScoreRepository.get(userId);
    if (!userScore.isPresent()) {
      userScoreRepository.save(UserScore.create(userId));
      LOG.fine("Creating UserScore for userId: " + userId);
    }

    LocalDateTime now = LocalDateTime.now();
    Session session = Session.createFor(userId, now);

    sessionRepository.save(session);
    scheduler.schedule(new ExpireSessionTask(sessionRepository, session.getId()), ChronoUnit.MINUTES.between(now, session.getExpiryDate()), TimeUnit.MINUTES);

    LOG.fine("Session: " + session.getId() + " has been created for user: " + session.getUserId());

    return session.getId();
  }
}
