package com.snowypeaksystems.mobactions.data.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.CommandDataImpl;
import org.junit.jupiter.api.Test;

/**
 * Tests for CommandDataImpl.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class CommandDataImplTest {
  @Test
  void replace() {
    CommandData data = new CommandDataImpl("te{}s\\\\t");
    assertEquals("test tes\\\\t", data.getCommand("st te"));

    data = new CommandDataImpl("{}st; {}st");
    assertEquals("test; test", data.getCommand("te"));
    data = new CommandDataImpl("{}st\\; {}st");
    assertEquals("test\\; test", data.getCommand("te"));

    data = new CommandDataImpl("\\\\{} \\{\\} {}");
    assertEquals("\\test {} test", data.getCommand("test"));
    data = new CommandDataImpl("\\{\\} \\\\{} {}");
    assertEquals("{} \\test test", data.getCommand("test"));
    data = new CommandDataImpl("{} \\{\\} \\\\{}");
    assertEquals("test {} \\test", data.getCommand("test"));
  }
}