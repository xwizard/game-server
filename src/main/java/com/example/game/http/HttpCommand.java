package com.example.game.http;

/**
 * Common interface for HTTP commands.
 */
public interface HttpCommand {
  /**
   * Executes the command.
   */
  CommandResult execute();
}
