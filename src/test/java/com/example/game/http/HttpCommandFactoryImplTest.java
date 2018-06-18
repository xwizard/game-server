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
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/login"));
    assertEquals(LoginHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreateQuitCommand() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/quit"));
    assertEquals(QuitHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreateInvalidCommand() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/3/wrong?sessionkey=UICSNDK"));
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreateInvalidCommandWhenCommandIsMissing() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/1?sessionkey=UICSNDK"));
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreateInvalidCommandWhenIdIsMissing() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081?sessionkey=UICSNDK"));
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreateInvalidCommandForScoreMissing() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/score?sessionkey=UICSNDK", "POST"));
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void createShouldCreatePostScoreCommand() {
    HttpCommand actual = factory.create(StubHttpExchange.of("http://localhost:8081/2/score?sessionkey=UICSNDK", "POST", "123"));
    assertEquals(PostScoreHttpCommand.class, actual.getClass());
  }
}