package com.snowypeaksystems.mobportals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for Command class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobCommandTest {
  @Test
  public void getString() {
    String message = "TEST";
    MobCommand m = new MobCommand(message, message);
    assertEquals(message, m.getName());
    assertEquals(message, m.getString());

    m = new MobCommand("TEST", "T{}T");
    assertEquals("TEST", m.getString("ES"));

    m = new MobCommand("TEST", "T{} {}T");
    assertEquals("TEST TEST", m.getString("EST", "TES"));

    try {
      m = new MobCommand("TEST", "{}");
      m.getString();
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true); // Satisfy checks
    }

    try {
      m = new MobCommand("TEST", "{} {}");
      m.getString("TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }

    try {
      m = new MobCommand("TEST", "{}");
      m.getString("TEST", "TEST");
    } catch (IllegalArgumentException ignored) {
      fail();
    }
  }
}