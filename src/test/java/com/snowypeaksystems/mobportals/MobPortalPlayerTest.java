package com.snowypeaksystems.mobportals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.snowypeaksystems.mobportals.mock.FakeMobWritable;
import com.snowypeaksystems.mobportals.mock.FakePlayer;
import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for MobPortalPlayers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobPortalPlayerTest {
  private static HashMap<Player, MobPortalPlayer.Creation> editors;

  @BeforeClass
  public static void setUp() {
    editors = new HashMap<>();
  }

  @Test
  public void setCreation() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);
    IMobWritable data = new FakeMobWritable();

    player.setCreation(IMobPortalPlayer.Type.PORTAL, data);
    assertEquals(data, player.getCreation());
    assertEquals(IMobPortalPlayer.Type.PORTAL, player.getCreationType());

    player.setCreation(IMobPortalPlayer.Type.NONE, null);
    assertEquals(IMobPortalPlayer.Type.NONE, player.getCreationType());

    player.setDestroying(true);
    player.setCreation(IMobPortalPlayer.Type.COMMAND, data);
    assertEquals(data, player.getCreation());
    assertEquals(IMobPortalPlayer.Type.COMMAND, player.getCreationType());

    player.setCreation(IMobPortalPlayer.Type.PORTAL, data);
    player.setDestroying(true);
    assertEquals(IMobPortalPlayer.Type.NONE, player.getCreationType());
  }

  @Test
  public void setDestroying() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);

    player.setDestroying(true);
    assertTrue(player.isDestroying());
    assertEquals(IMobPortalPlayer.Type.NONE, player.getCreationType());

    player.setDestroying(false);
    assertFalse(player.isDestroying());

    player.setCreation(IMobPortalPlayer.Type.PORTAL, new FakeMobWritable());
    player.setDestroying(true);
    assertTrue(player.isDestroying());
    assertEquals(IMobPortalPlayer.Type.NONE, player.getCreationType());

    IMobWritable data = new FakeMobWritable();
    player.setDestroying(true);
    player.setCreation(IMobPortalPlayer.Type.PORTAL, data);
    assertFalse(player.isDestroying());
    assertEquals(data, player.getCreation());
    assertEquals(IMobPortalPlayer.Type.PORTAL, player.getCreationType());
  }

  @Test
  public void isCreating() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);

    player.setCreation(IMobPortalPlayer.Type.PORTAL, null);
    assertTrue(player.isCreating());

    player.setCreation(IMobPortalPlayer.Type.NONE, null);
    assertFalse(player.isCreating());

    player.setDestroying(true);
    assertFalse(player.isCreating());

    player.setCreation(IMobPortalPlayer.Type.PORTAL, null);
    player.setDestroying(true);
    assertFalse(player.isCreating());

    player.setDestroying(true);
    player.setCreation(IMobPortalPlayer.Type.PORTAL, null);
    assertTrue(player.isCreating());
  }


  @Test
  public void isDestroying() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);

    player.setDestroying(true);
    assertTrue(player.isDestroying());

    player.setDestroying(false);
    assertFalse(player.isDestroying());

    player.setCreation(IMobPortalPlayer.Type.PORTAL, null);
    player.setDestroying(true);
    assertTrue(player.isDestroying());

    player.setDestroying(true);
    player.setCreation(IMobPortalPlayer.Type.NONE, null);
    assertTrue(player.isDestroying());

    player.setDestroying(true);
    player.setCreation(IMobPortalPlayer.Type.PORTAL, null);
    assertFalse(player.isDestroying());
  }
}