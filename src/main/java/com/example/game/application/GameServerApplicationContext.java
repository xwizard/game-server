package com.example.game.application;

import com.example.game.application.score.PostUserScoreService;
import com.example.game.application.score.PostUserScoreServiceImpl;
import com.example.game.application.session.SessionService;
import com.example.game.application.session.SessionServiceImpl;
import com.example.game.application.session.SessionValidatorServiceImpl;
import com.example.game.application.session.SessionValidatorService;
import com.example.game.core.repository.MemoryUserScoreRepository;
import com.example.game.core.repository.MemorySessionRepository;
import com.example.game.core.repository.SessionRepository;
import com.example.game.core.repository.UserScoreRepository;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Basic implementation of {@link ApplicationContext}.
 */
public class GameServerApplicationContext implements ApplicationContext {

  private ScheduledExecutorService executor;
  private SessionService sessionService;
  private SessionValidatorService sessionValidatorService;
  private PostUserScoreService postUserScoreService;
  private Application application;


  public GameServerApplicationContext(Application application) {
    this.application = application;
    executor = new ScheduledThreadPoolExecutor(50);

    SessionRepository sessionRepository = new MemorySessionRepository();
    UserScoreRepository userScoreRepository = new MemoryUserScoreRepository();
    postUserScoreService = new PostUserScoreServiceImpl(userScoreRepository);
    sessionService = new SessionServiceImpl(sessionRepository, userScoreRepository, executor);
    sessionValidatorService = new SessionValidatorServiceImpl(sessionRepository);
  }

  @Override
  public SessionService sessionService() {
    return sessionService;
  }

  @Override
  public ScheduledExecutorService executor() {
    return executor;
  }

  @Override
  public Application application() {
    return application;
  }

  @Override
  public SessionValidatorService sessionValidatorService() {
    return sessionValidatorService;
  }

  @Override
  public PostUserScoreService postUserScoreService() {
    return postUserScoreService;
  }
}
