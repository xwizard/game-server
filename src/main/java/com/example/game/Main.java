package com.example.game;

import com.example.game.http.RootHttpContextHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Main application entry point
 */
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    try {
      HttpServer httpServer = HttpServer.create();
      httpServer.bind(new InetSocketAddress(8080), 500);
      httpServer.createContext("/", new RootHttpContextHandler());
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
