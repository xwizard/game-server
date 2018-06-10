package com.example.game.eventbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marks method of a class a handler for an event.
 */
@Documented
@Target(ElementType.METHOD)
public @interface Handler {
}
