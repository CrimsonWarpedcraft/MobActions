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
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ConsoleUserTest {
  @Test
  void canUseWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canUseWarp(""));
  }

  @Test
  void canRunCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canRunCommand());
  }

  @Test
  void canCreate() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canCreate());
  }

  @Test
  void canRemove() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canRemove());
  }

  @Test
  void canUseWarpCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canUseWarpCommand());
  }

  @Test
  void canSetWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canSetWarp());
  }

  @Test
  void canRemoveWarp() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canRemoveWarp());
  }

  @Test
  void canReload() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canReload());
  }

  @Test
  void canUseWarpsCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canUseWarpsCommand());
  }

  @Test
  void getStatus() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertThrows(UnsupportedOperationException.class, console::getStatus);
  }

  @Test
  void teleport() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    World world = new FakeWorld(); //Need to keep a reference or otherwise gc will set to null
    assertThrows(UnsupportedOperationException.class,
        () -> console.teleport(new Location(world, 0, 0, 0)));
  }

  @Test
  void performCommand() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertThrows(UnsupportedOperationException.class, () -> console.performCommand(""));
  }

  @Test
  void getDisplayName() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertEquals("Console", console.getName());
  }

  @Test
  void getLocation() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertThrows(UnsupportedOperationException.class, console::getLocation);
  }

  @Test
  void canJoinEvent() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canJoinEvent(""));
  }

  @Test
  void canStartEvents() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canStartEvents());
  }

  @Test
  void canCancelEvents() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canCancelEvents());
  }

  @Test
  void canCreateEvents() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canCreateEvents());
  }

  @Test
  void canRemoveEvents() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canRemoveEvents());
  }

  @Test
  void canGetEventInfo() {
    MobActionsUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertTrue(console.canGetEventInfo());
  }

  @Test
  void canUseConsoleCommand() {
    ConsoleActionUser console = new ConsoleUser(new FakeConsoleCommandSender(), null);
    assertFalse(console.canUseConsoleCommand());
  }
}