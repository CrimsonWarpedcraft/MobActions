package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.snowypeaksystems.mobactions.mob.data.command.CommandData;
import com.snowypeaksystems.mobactions.mob.data.command.ICommandData;
import com.snowypeaksystems.mobactions.mob.data.warp.IWarp;
import com.snowypeaksystems.mobactions.mock.FakePlayer;
import com.snowypeaksystems.mobactions.mock.FakeWarp;
import org.junit.jupiter.api.Test;

/**
 * Tests for MobActionsPlayer.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class MobActionsPlayerTest {
  @Test
  void canUseWarp() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);
    IWarp warp = new FakeWarp("test1");

    assertFalse(player.canUseWarp(warp));

    fake.setPermission("mobactions.warp.test1", true);
    assertTrue(player.canUseWarp(warp));
    fake.setPermission("mobactions.warp.test1", false);

    fake.setPermission("mobactions.warp.*", true);
    assertTrue(player.canUseWarp(warp));
    fake.setPermission("mobactions.warp.*", false);
  }

  @Test
  void canRunCommand() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);
    ICommandData command = new CommandData("test1", "");

    assertFalse(player.canRunCommand(command));

    fake.setPermission("mobactions.command.test1", true);
    assertTrue(player.canRunCommand(command));
    fake.setPermission("mobactions.command.test1", false);

    fake.setPermission("mobactions.command.*", true);
    assertTrue(player.canRunCommand(command));
    fake.setPermission("mobactions.command.*", false);
  }

  @Test
  void canCreatePortal() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canCreatePortal());

    fake.setPermission("mobactions.admin.createportal", true);
    assertTrue(player.canCreatePortal());
    fake.setPermission("mobactions.admin.createportal", false);
  }

  @Test
  void canCreateCommand() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canCreateCommand());

    fake.setPermission("mobactions.admin.createcommand", true);
    assertTrue(player.canCreateCommand());
    fake.setPermission("mobactions.admin.createcommand", false);
  }

  @Test
  void canRemove() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canRemove());

    fake.setPermission("mobactions.admin.remove", true);
    assertTrue(player.canRemove());
    fake.setPermission("mobactions.admin.remove", false);
  }

  @Test
  void canUseWarpCommand() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canUseWarpCommand());

    fake.setPermission("mobactions.warp", true);
    assertTrue(player.canUseWarpCommand());
    fake.setPermission("mobactions.warp", false);
  }

  @Test
  void canSetWarp() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canSetWarp());

    fake.setPermission("mobactions.admin.setwarp", true);
    assertTrue(player.canSetWarp());
    fake.setPermission("mobactions.admin.setwarp", false);
  }

  @Test
  void canRemoveWarp() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canRemoveWarp());

    fake.setPermission("mobactions.admin.delwarp", true);
    assertTrue(player.canRemoveWarp());
    fake.setPermission("mobactions.admin.delwarp", false);
  }

  @Test
  void canReload() {
    FakePlayer fake = new FakePlayer();
    IMobActionsPlayer player = new MobActionsPlayer(fake);

    assertFalse(player.canReload());

    fake.setPermission("mobactions.admin.reload", true);
    assertTrue(player.canReload());
    fake.setPermission("mobactions.admin.reload", false);
  }
}