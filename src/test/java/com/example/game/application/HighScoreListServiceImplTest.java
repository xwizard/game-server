package com.example.game.application;

import com.example.game.core.LevelHighScore;
import com.example.game.core.UserHighScore;
import com.example.game.core.repository.LevelHighScoreRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HighScoreListServiceImplTest {

  private LevelHighScore levelHighScore;
  private HighScoreListService service;

  @Before
  public void before() {
    levelHighScore = LevelHighScore.create(1);
    levelHighScore.postScore(0, 200);
    LevelHighScoreRepository repository = mock(LevelHighScoreRepository.class);
    when(repository.get(eq(1))).thenReturn(Optional.of(levelHighScore));

    service = new HighScoreListServiceImpl(repository);
  }

  @Test
  public void listShouldReturnEmptyListForNonExistingLevel() {
    List<UserHighScore> actual = service.listHighScores(0, 0);
    assertTrue(actual.isEmpty());
  }

  @Test
  public void listShouldReturnHighScore() {
    List<UserHighScore> actual = service.listHighScores(0, 1);
    assertEquals(1, actual.size());
    assertEquals(Integer.valueOf(0), actual.get(0).getUserId());
    assertEquals(Integer.valueOf(200), actual.get(0).getScore());
  }
}