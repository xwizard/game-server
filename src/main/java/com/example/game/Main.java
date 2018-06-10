package com.example.game;

import com.example.game.application.Application;
import com.example.game.application.GameServerApplication;

/**
 * Main application entry point
 */
public class Main {
  public static void main(String[] args) {
    Application application = new GameServerApplication();
    application.run();
  }
}
