package com.example.game.application.session;

import java.util.Optional;

/**
 * Validates user session key.
 */
public interface SessionValidatorService {
  Optional<Integer> getUserId(SessionId sessionId);
}
