package com.example.game.http;


import com.example.game.application.session.SessionId;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

public class URIWrapper {

  private final static Logger LOG = Logger.getLogger(URIWrapper.class.getCanonicalName());

  private final Integer id;
  private final String command;
  private SessionId sessionId;

  public URIWrapper(Integer id, String command, SessionId sessionId) {
    this.id = id;
    this.command = command;
    this.sessionId = sessionId;
  }

  public static URIWrapper of(URI uri) {
    return new URIWrapper(parseId(uri), parseCommand(uri), parseSessionId(uri));
  }

  private static String parseCommand(URI uri) {
    String path = uri.getPath();
    String[] parts = path.split("/");
    if (parts.length > 2 && !parts[2].isEmpty()) {
      return parts[2];
    }
    return null;
  }

  private static Integer parseId(URI uri) {
    String path = uri.getPath();
    String[] parts = path.split("/");
    if (parts.length > 1 && !parts[1].isEmpty()) {
      return toInteger(parts[1]);
    }
    return null;
  }

  private static Integer toInteger(String part) {
    try {
      Integer i = Integer.parseInt(part);
      if (i >= 0) {
        return i;
      }
    } catch (NumberFormatException e) {
      //it's fine ^^
    }
    LOG.fine(part + "is not a number");
    return null;
  }

  private static SessionId parseSessionId(URI uri) {
    String query = uri.getQuery();
    if (query == null) {
      return null;
    }
    String[] parts = query.split("=");
    if (parts.length == 2) {
      try {
        return SessionId.of(parts[1]);
      } catch (IllegalArgumentException e) {
        // this is fine ^^
      }
    }
    LOG.fine("Session id is null or invalid");
    return null;
  }

  public Optional<Integer> getId() {
    return Optional.ofNullable(id);
  }

  public Optional<String> getCommand() {
    return Optional.ofNullable(command);
  }

  public Optional<SessionId> getSessionId() {
    return Optional.ofNullable(sessionId);
  }
}
