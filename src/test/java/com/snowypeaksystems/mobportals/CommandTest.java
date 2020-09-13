package com.snowypeaksystems.mobportals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for Command class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandTest {
  @Test
  public void getString() {
    String message = "TEST";
    Command m = new Command(message, message);
    assertEquals(message, m.getName());
    assertEquals(message, m.getString());

    m = new Command("TEST", "T{}T");
    assertEquals("TEST", m.getString("ES"));

    m = new Command("TEST", "T{} {}T");
    assertEquals("TEST TEST", m.getString("EST", "TES"));

    try {
      m = new Command("TEST", "{}");
      m.getString();
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true); // Satisfy checks
    }

    try {
      m = new Command("TEST", "{} {}");
      m.getString("TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }

    try {
      m = new Command("TEST", "{}");
      m.getString("TEST", "TEST");
      fail();
    } catch (IllegalArgumentException ignored) {
      assertTrue(true);
    }
  }
}