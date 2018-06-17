package com.example.game;

import com.example.game.application.Application;
import com.example.game.application.GameServerApplication;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Main application entry point.
 */
public class Main {
  public static void main(String[] args) {
    Application application = new GameServerApplication();
    setupLogger(isDebugEnabled(args));
    application.run();
  }

  private static boolean isDebugEnabled(String[] args) {
    return args.length > 0 && "-d".equals(args[0]);
  }

  private static void setupLogger(boolean debug) {
    Level level = debug ? Level.FINE : Level.INFO;

    Logger rootLogger = LogManager.getLogManager().getLogger("");
    rootLogger.setLevel(level);
    for (Handler h : rootLogger.getHandlers()) {
      h.setLevel(level);
    }
  }
}
