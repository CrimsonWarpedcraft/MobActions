package com.snowypeaksystems.mobportals.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests for classes in the messages package.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MessageTests {
  @Test
  public void gm() {
    try {
      Messages.gm("test-message");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    MutableMessages.put("test-message", new Message("Test"));
    assertEquals("Test", Messages.gm("test-message"));
  }

  @Test
  public void initialize() {
    MutableMessages.put("test-message", new Message("Test"));
    Messages.initialize();
    assertFalse(MutableMessages.contains("test-message"));
  }

  @Test
  public void getMessage() {
    String message = "TEST";
    Message m = new Message(message);
    assertEquals(message, m.getString());

    message = "&6TEST";
    m = new Message(message);
    assertEquals("§6TEST", m.getString());

    m = new Message("&6{}T");
    assertEquals("§6§rTES§r§6T", m.getString("TES"));

    m = new Message("&6{} {}T");
    assertEquals("§6§rTEST§r§6 §rTES§r§6T", m.getString("TEST", "TES"));

    m = new Message("&6{&c}T");
    assertEquals("§6§r§cTES§r§6T", m.getString("TES"));

    try {
      m = new Message("&6{}");
      m.getString();
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true); // Satisfy checks
    }

    try {
      m = new Message("&6{} {}");
      m.getString("TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }

    try {
      m = new Message("&6{}");
      m.getString("TEST", "TEST");
    } catch (IllegalArgumentException ignored) {
      fail();
    }
  }
}