package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.snowypeaksystems.mobactions.mock.FakePlayer;
import org.junit.jupiter.api.Test;

/**
 * Tests for MobActionsPlayer.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class MobActionsPlayerTest {
  @Test
  void canUseWarp() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canUseWarp("test1"));

    fake.setPermission("mobactions.warp.test1", true);
    assertTrue(player.canUseWarp("test1"));
    fake.setPermission("mobactions.warp.test1", false);

    fake.setPermission("mobactions.warp.*", true);
    assertTrue(player.canUseWarp("test1"));
    fake.setPermission("mobactions.warp.*", false);
  }

  @Test
  void canRunCommand() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canRunCommand());

    fake.setPermission("mobactions.command", true);
    assertTrue(player.canRunCommand());
    fake.setPermission("mobactions.command", false);
  }

  @Test
  void canCreate() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canCreate());

    fake.setPermission("mobactions.admin.create", true);
    assertTrue(player.canCreate());
    fake.setPermission("mobactions.admin.create", false);
  }

  @Test
  void canRemove() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canRemove());

    fake.setPermission("mobactions.admin.remove", true);
    assertTrue(player.canRemove());
    fake.setPermission("mobactions.admin.remove", false);
  }

  @Test
  void canUseWarpCommand() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canUseWarpCommand());

    fake.setPermission("mobactions.warp", true);
    assertTrue(player.canUseWarpCommand());
    fake.setPermission("mobactions.warp", false);
  }

  @Test
  void canSetWarp() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canSetWarp());

    fake.setPermission("mobactions.admin.warps.set", true);
    assertTrue(player.canSetWarp());
    fake.setPermission("mobactions.admin.warps.set", false);
  }

  @Test
  void canRemoveWarp() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canRemoveWarp());

    fake.setPermission("mobactions.admin.warps.remove", true);
    assertTrue(player.canRemoveWarp());
    fake.setPermission("mobactions.admin.warps.remove", false);
  }

  @Test
  void canReload() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canReload());

    fake.setPermission("mobactions.admin.reload", true);
    assertTrue(player.canReload());
    fake.setPermission("mobactions.admin.reload", false);
  }

  @Test
  void canUseWarpsCommand() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canUseWarpsCommand());

    fake.setPermission("mobactions.warp", true);
    assertTrue(player.canUseWarpsCommand());
    fake.setPermission("mobactions.warp", false);
  }

  @Test
  void canJoinEvent() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canJoinEvent("test1"));

    fake.setPermission("mobactions.event.test1", true);
    assertTrue(player.canJoinEvent("test1"));
    fake.setPermission("mobactions.event.test1", false);

    fake.setPermission("mobactions.event.*", true);
    assertTrue(player.canJoinEvent("test1"));
    fake.setPermission("mobactions.event.*", false);
  }

  @Test
  void canStartEvents() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canStartEvents());

    fake.setPermission("mobactions.admin.events.start", true);
    assertTrue(player.canStartEvents());
    fake.setPermission("mobactions.admin.events.start", false);
  }

  @Test
  void canCancelEvents() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canCancelEvents());

    fake.setPermission("mobactions.admin.events.stop", true);
    assertTrue(player.canCancelEvents());
    fake.setPermission("mobactions.admin.events.stop", false);
  }

  @Test
  void canCreateEvents() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canCreateEvents());

    fake.setPermission("mobactions.admin.events.create", true);
    assertTrue(player.canCreateEvents());
    fake.setPermission("mobactions.admin.events.create", false);
  }

  @Test
  void canRemoveEvents() {
    FakePlayer fake = new FakePlayer();
    MobActionsUser player = new MobActionsPlayer(fake);

    assertFalse(player.canRemoveEvents());

    fake.setPermission("mobactions.admin.events.remove", true);
    assertTrue(player.canRemoveEvents());
    fake.setPermission("mobactions.admin.events.remove", false);
  }
}