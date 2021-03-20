package com.snowypeaksystems.mobactions.data.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.ICommandData;
import org.junit.jupiter.api.Test;

/**
 * Tests for CommandData.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class CommandDataTest {
  @Test
  void replace() {
    ICommandData data = new CommandData("te{}s\\\\t");
    assertEquals("test tes\\\\t", data.getCommand("st te"));

    data = new CommandData("{}st; {}st");
    assertEquals("test; test", data.getCommand("te"));
    data = new CommandData("{}st\\; {}st");
    assertEquals("test\\; test", data.getCommand("te"));

    data = new CommandData("\\\\{} \\{\\} {}");
    assertEquals("\\test {} test", data.getCommand("test"));
    data = new CommandData("\\{\\} \\\\{} {}");
    assertEquals("{} \\test test", data.getCommand("test"));
    data = new CommandData("{} \\{\\} \\\\{}");
    assertEquals("test {} \\test", data.getCommand("test"));
  }
}