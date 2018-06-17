package com.example.game.http;

/**
 * Carries result and status of {@link HttpCommand}.
 */
public interface CommandResult {
  /**
   * Generates HTTP response body.
   * @return string with body
   */
  String toResponse();
}
