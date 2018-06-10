package com.example.game.application;

/**
 * Generic application launcher.
 */
public interface Application {
  /**
   * Sets up and launches application.
   */
  void run();

  /**
   * Shutdown routines.
   */
  void shutdown();
}
