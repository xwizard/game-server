package com.example.game.eventbus;

/**
 * Simple annotation driven event bus.
 * Inspired by Guava event bus.
 */
public interface EventBus {
  /**
   * Registers an event handler.
   * Handler has to declare at least one method annotated with {@link Handler}
   * @param handler event handler instance
   */
  void register(Object handler);

  /**
   * Emits given event.
   * @param event event to emit
   */
  void emit(Object event);
}
