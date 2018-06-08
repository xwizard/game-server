package com.example.game.application.session;

import java.util.UUID;

/**
 * Manages user sessions. Should be thread safe
 */
public interface SessionService {
  UUID login(String userId);
}
