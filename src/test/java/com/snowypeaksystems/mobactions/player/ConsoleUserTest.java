package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.snowypeaksystems.mobactions.mock.FakeConsoleCommandSender;
import com.snowypeaksystems.mobactions.mock.FakeWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConsoleUser.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ConsoleUserTest {
  @Test
  void canUseWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canUseWarp(""));
  }

  @Test
  void canRunCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertFalse(console.canRunCommand(""));
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
  void canUseWarpsCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertTrue(console.canUseWarpsCommand());
  }

  @Test
  void getStatus() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertThrows(UnsupportedOperationException.class, console::getStatus);
  }

  @Test
  void teleport() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    World world = new FakeWorld(); //Need to keep a reference or otherwise gc will set to null
    assertThrows(UnsupportedOperationException.class,
        () -> console.teleport(new Location(world, 0, 0, 0)));
  }

  @Test
  void performCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertThrows(UnsupportedOperationException.class, () -> console.performCommand(""));
  }

  @Test
  void getDisplayName() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertEquals("Console", console.getName());
  }

  @Test
  void getLocation() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender());
    assertThrows(UnsupportedOperationException.class, console::getLocation);
  }
}