package com.example.game.core;

import com.example.game.core.repository.Entity;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents high score for level.
 */
public class LevelHighScore implements Entity<Integer> {
  private final Integer levelId;
  private final Set<Integer> usersWhoScored;
  private List<UserHighScore> highScores;
  private final UserHighScoreComparator comparator;
  private final Lock lock;

  public LevelHighScore(Integer levelId) {
    this.levelId = levelId;
    this.usersWhoScored = new HashSet<>();
    this.highScores = new ArrayList<>();
    this.comparator = new UserHighScoreComparator();
    this.lock = new ReentrantLock(true);
  }

  public static LevelHighScore create(Integer levelId) {
    if (levelId == null || levelId < 0) throw new IllegalArgumentException("Level id cannot be null or negative");
    return new LevelHighScore(levelId);
  }

  @Override
  public Integer getId() {
    return levelId;
  }

  public void postScore(Integer userId, Integer score) {
    if (userId == null || score == null || score < 0 || userId < 0) throw new IllegalArgumentException("Score and user id have to be positive integers");
    lock.lock();

    usersWhoScored.add(userId);
    Optional<UserHighScore> lastHighScore = findHighScore(userId);
    UserHighScore currentScore = new UserHighScore(userId, score);
    if (!lastHighScore.isPresent()) {
      highScores.add(currentScore);
      sortAndTruncateScores();
    } else {
      if (currentScore.getScore() > lastHighScore.get().getScore()) {
        highScores.remove(lastHighScore.get());
        highScores.add(currentScore);
        sortAndTruncateScores();
      }
    }

    lock.unlock();
  }

  private void sortAndTruncateScores() {
    highScores.sort(comparator);
    if (highScores.size() > 15) {
      highScores = highScores.subList(0, 15);
    }
  }

  private Optional<UserHighScore> findHighScore(Integer userId) {
    return highScores.stream().filter(hs -> hs.getUserId().equals(userId)).findFirst();
  }

  public List<UserHighScore> getHighScore(Integer userId) {
    lock.lock();

    if (!usersWhoScored.contains(userId)) {
      return Collections.emptyList();
    }

    List<UserHighScore> highScores = new ArrayList<>(this.highScores);

    lock.unlock();
    return highScores;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LevelHighScore highScore = (LevelHighScore) o;
    return Objects.equals(levelId, highScore.levelId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(levelId);
  }
}
