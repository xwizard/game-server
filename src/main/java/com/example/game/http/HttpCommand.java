package com.example.game.http;

import java.net.URI;

/**
 * Common interface for HTTP commands.
 */
public interface HttpCommand {
  /**
   * Returns request URI
   * @return URI
   */
  URI getURI();

  /**
   * Returns HTTP method used.
   * @return HTTP method
   */
  HttpMethod getMethod();
}
