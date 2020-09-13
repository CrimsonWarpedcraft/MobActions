package com.snowypeaksystems.mobportals.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for classes in the messages package.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MessageTests {
  @Test
  public void gm() {
    Messages.getMessages().put("test-message", new Message("Test"));
    assertEquals("Test", Messages.gm("test-message"));
  }

  @Test
  public void initialize() {
    Messages.getMessages().put("test-message", new Message("Test"));
    Messages.initialize();
    try {
      Messages.gm("test-message");
      fail();
    } catch (RuntimeException ignored) {
      assertTrue(true);
    }
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
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }
  }
}