package com.example.game.http;


import java.net.URI;
import java.util.Optional;

public class URIWrapper {
  private final String id;
  private final String command;
  private String sessionId;

  public URIWrapper(String id, String command, String sessionId) {
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

  private static String parseId(URI uri) {
    String path = uri.getPath();
    String[] parts = path.split("/");
    if (parts.length > 1 && !parts[1].isEmpty()) {
      return parts[1];
    }
    return null;
  }

  private static String parseSessionId(URI uri) {
    String query = uri.getQuery();
    if (query == null) {
      return null;
    }
    String[] parts = query.split("=");
    if (parts.length == 2) {
      return parts[1];
    }

    return null;
  }

  public Optional<String> getId() {
    return Optional.ofNullable(id);
  }

  public Optional<String> getCommand() {
    return Optional.ofNullable(command);
  }

  public Optional<String> getSessionId() {
    return Optional.ofNullable(sessionId);
  }
}
