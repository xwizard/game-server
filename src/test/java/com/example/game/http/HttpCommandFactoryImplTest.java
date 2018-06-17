package com.example.game.http;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HttpCommandFactoryImplTest {

  private HttpCommandFactoryImpl factory;

  @Before
  public void before() {
    factory = new HttpCommandFactoryImpl();
  }

  @Test
  @Ignore
  public void createShouldCreateLoginCommand() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/score?sessionkey=UICSNDK"));
    assertTrue(actual instanceof LoginHttpCommand);
    LoginHttpCommand command = (LoginHttpCommand) actual;
  }
}