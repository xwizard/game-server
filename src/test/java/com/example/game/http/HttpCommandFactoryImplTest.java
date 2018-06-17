package com.example.game.http;

import com.example.game.application.ApplicationContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class HttpCommandFactoryImplTest {

  private HttpCommandFactoryImpl factory;

  @Before
  public void before() {
    factory = new HttpCommandFactoryImpl(mock(ApplicationContext.class));
  }

  @Test
  public void createShouldCreateLoginCommand() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/score?sessionkey=UICSNDK"));
    assertEquals(LoginHttpCommand.class, actual.getClass());
  }
}