package com.snowypeaksystems.mobactions.mob.data.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for CommandData.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class CommandDataTest {
  @Test
  void replace() {
    ICommandData data = new CommandData("", "te{}st");
    assertEquals("test test", data.replace("st te"));
    assertEquals("test test", data.replace("st te", "test"));

    data = new CommandData("", "{}st te{}");
    assertEquals("test test", data.replace("te", "st"));
    assertEquals("test te{}", data.replace("te"));
  }

  @Test
  void getTokenCount() {
    ICommandData data = new CommandData("", "test");
    assertEquals(0, data.getTokenCount());

    data = new CommandData("", "te{}st");
    assertEquals(1, data.getTokenCount());

    data = new CommandData("", "{}st te{}");
    assertEquals(2, data.getTokenCount());

    data = new CommandData("", "{}st{}te{}");
    assertEquals(3, data.getTokenCount());
  }

  @Test
  void getAlias() {
    ICommandData data = new CommandData("test", "");
    assertEquals("test", data.getAlias());
  }
}