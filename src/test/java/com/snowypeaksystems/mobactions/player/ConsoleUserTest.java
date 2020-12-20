package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.mock.FakeConsoleCommandSender;
import com.snowypeaksystems.mobactions.mock.FakeWarp;
import com.snowypeaksystems.mobactions.mock.FakeWorld;
import org.bukkit.Location;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConsoleUser.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ConsoleUserTest {

  @Test
  void canUseWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canUseWarp(new FakeWarp("")));
  }

  @Test
  void canRunCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canRunCommand(new CommandData("", "", "")));
  }

  @Test
  void canCreate() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canCreate());
  }

  @Test
  void canRemove() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canRemove());
  }

  @Test
  void canUseWarpCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canUseWarpCommand());
  }

  @Test
  void canSetWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canSetWarp());
  }

  @Test
  void canRemoveWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertTrue(console.canRemoveWarp());
  }

  @Test
  void canReload() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertTrue(console.canReload());
  }

  @Test
  void getStatus() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());

    try {
      console.getStatus();
      fail();
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
    }
  }

  @Test
  void teleport() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());

    try {
      console.teleport(new Location(new FakeWorld(""), 0, 0, 0));
      fail();
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
    }
  }

  @Test
  void performCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());

    try {
      console.performCommand("");
      fail();
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
    }
  }

  @Test
  void getDisplayName() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());

    assertEquals("Console", console.getDisplayName());
  }

  @Test
  void getLocation() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());

    try {
      console.getLocation();
      fail();
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
    }
  }
}