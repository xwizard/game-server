package com.example.game.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

class StubHttpExchange extends HttpExchange {

  private final URI uri;
  private final String method;
  private final String body;

  private StubHttpExchange(URI uri, String method, String body) {
    this.uri = uri;
    this.method = method;
    this.body = body;
  }

  public static StubHttpExchange of(String uri, String method, String body) {
    try {
      return new StubHttpExchange(new URI(uri), method, body);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static HttpExchange of(String uri, String method) {
    return of(uri, method, "");
  }

  public static HttpExchange ofBody(String body) {
    return of("http://example.com", null, body);
  }

  @Override
  public Headers getRequestHeaders() {
    return null;
  }

  @Override
  public Headers getResponseHeaders() {
    return null;
  }

  @Override
  public URI getRequestURI() {
    return uri;
  }

  @Override
  public String getRequestMethod() {
    return method;
  }

  @Override
  public HttpContext getHttpContext() {
    return null;
  }

  @Override
  public void close() {

  }

  @Override
  public InputStream getRequestBody() {
    return new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public OutputStream getResponseBody() {
    return null;
  }

  @Override
  public void sendResponseHeaders(int i, long l) throws IOException {

  }

  @Override
  public InetSocketAddress getRemoteAddress() {
    return null;
  }

  @Override
  public int getResponseCode() {
    return 0;
  }

  @Override
  public InetSocketAddress getLocalAddress() {
    return null;
  }

  @Override
  public String getProtocol() {
    return null;
  }

  @Override
  public Object getAttribute(String s) {
    return null;
  }

  @Override
  public void setAttribute(String s, Object o) {

  }

  @Override
  public void setStreams(InputStream inputStream, OutputStream outputStream) {

  }

  @Override
  public HttpPrincipal getPrincipal() {
    return null;
  }
}
