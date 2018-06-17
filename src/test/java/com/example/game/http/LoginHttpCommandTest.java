package com.example.game.http;

import com.example.game.application.session.SessionId;
import com.example.game.application.session.SessionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginHttpCommandTest {

  private final static SessionId SESSION_ID = SessionId.random();

  private SessionService sessionService;

  @Before
  public void before() {
    sessionService = mock(SessionService.class);
    when(sessionService.login(anyInt())).thenReturn(SESSION_ID);
  }

  @Test
  public void ofShouldReturnInvalidCommandForNonNumericalUserIds() {
    HttpCommand actual = LoginHttpCommand.of(sessionService, "incorrect", HttpMethod.GET);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnInvalidCommandForPOST() {
    HttpCommand actual = LoginHttpCommand.of(sessionService, "123", HttpMethod.POST);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnInvalidCommandForUNKNOWN() {
    HttpCommand actual = LoginHttpCommand.of(sessionService, "123", HttpMethod.UNKNOWN);
    assertEquals(InvalidHttpCommand.class, actual.getClass());
  }

  @Test
  public void ofShouldReturnValidLoginCommand() {
    HttpCommand actual = command();
    assertEquals(LoginHttpCommand.class, actual.getClass());
    assertEquals(new Integer(123), ((LoginHttpCommand) actual).getUserId());
  }

  @Test
  public void executeShouldPassToService() {
    CommandResult actual = command().execute();
    assertEquals(LoginCommandResult.class, actual.getClass());
    LoginCommandResult result = (LoginCommandResult) actual;
    assertEquals(SESSION_ID, result.getSessionId());
  }

  private HttpCommand command() {
    return LoginHttpCommand.of(sessionService, "123", HttpMethod.GET);
  }
}