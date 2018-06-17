package com.example.game.application;

import com.example.game.application.session.SessionService;

/**
 * Central context for services used in application.
 */
public interface ApplicationContext {
  SessionService sessionService();
}
