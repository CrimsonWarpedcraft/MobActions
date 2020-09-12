package com.snowypeaksystems.mobportals.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for classes in the messages package.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MessagesTest {
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
    assertEquals(message, m.getMessage());

    message = "&6TEST";
    m = new Message(message);
    assertEquals("§6TEST", m.getMessage());

    m = new Message("&6{}T");
    assertEquals("§6§rTES§r§6T", m.getMessage("TES"));

    m = new Message("&6{} {}T");
    assertEquals("§6§rTEST§r§6 §rTES§r§6T", m.getMessage("TEST", "TES"));

    m = new Message("&6{&c}T");
    assertEquals("§6§r§cTES§r§6T", m.getMessage("TES"));

    try {
      m = new Message("&6{} {}");
      m.getMessage("TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }

    try {
      m = new Message("&6{}");
      m.getMessage("TEST", "TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }
  }
}