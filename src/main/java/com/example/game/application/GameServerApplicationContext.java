package com.example.game.application;

import com.example.game.application.session.SessionService;

/**
 * Basic implementation of {@link ApplicationContext}.
 */
public class GameServerApplicationContext implements ApplicationContext {

  private final SessionService sessionService;

  public GameServerApplicationContext(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  public SessionService sessionService() {
    return sessionService;
  }
}
