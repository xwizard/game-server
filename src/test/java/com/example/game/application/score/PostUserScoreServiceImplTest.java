package com.example.game.application.score;

import com.example.game.core.LevelScore;
import com.example.game.core.UserScore;
import com.example.game.core.repository.UserScoreRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostUserScoreServiceImplTest {
  private PostUserScoreService service;
  private UserScore userScore;

  @Before
  public void before() {
    userScore = UserScore.create(1);
    UserScoreRepository userScoreRepository = mock(UserScoreRepository.class);
    when(userScoreRepository.get(eq(1))).thenReturn(Optional.of(userScore));
    service = new PostUserScoreServiceImpl(userScoreRepository);
  }

  @Test(expected = IllegalStateException.class)
  public void postShouldThrowWhenUserScoreIsMissing() {
    service.post(0, 0, 100);
  }

  @Test
  public void postShouldPostCorrectScore() {
    service.post(1, 1, 100);

    List<LevelScore> actual = userScore.getUserSores();
    assertEquals(1, actual.size());
    LevelScore ls = actual.get(0);
    assertEquals(Integer.valueOf(1), ls.getLevelId());
    assertEquals(Integer.valueOf(100), ls.getScore());
  }
}