package com.example.game.application;

import com.example.game.application.session.SessionService;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Central context for services used in application.
 */
public interface ApplicationContext {
  SessionService sessionService();
  ScheduledExecutorService executor();
  Application application();
}
