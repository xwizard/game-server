package com.example.game.http;

import com.example.game.application.Application;

public class QuitHttpCommand implements HttpCommand {
  private final Application application;

  private QuitHttpCommand(Application application) {
    this.application = application;
  }

  public static QuitHttpCommand of(Application application) {
    return new QuitHttpCommand(application);
  }

  @Override
  public CommandResult execute() {
    application.shutdown();
    return new CommandResult() {
      @Override
      public String toResponse() {
        return "Exit in progress…";
      }

      @Override
      public int httpStatus() {
        return 200;
      }
    };
  }

  @Override
  public String toString() {
    return "QuitHttpCommand";
  }
}
