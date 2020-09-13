package com.snowypeaksystems.mobportals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
  private static HashMap<Player, IMobWritable> editors;

  @BeforeClass
  public static void setUp() {
    editors = new HashMap<>();
  }

  @Test
  public void setCreation() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);
    IMobWritable data = new FakeMobWritable();

    player.setCreation(data);
    assertEquals(data, player.getCreation());

    player.setCreation(null);
    assertNull(player.getCreation());

    player.setDestroying(true);
    player.setCreation(data);
    assertEquals(data, player.getCreation());

    player.setCreation(data);
    player.setDestroying(true);
    assertNull(player.getCreation());
  }

  @Test
  public void setDestroying() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);

    player.setDestroying(true);
    assertTrue(player.isDestroying());
    assertNull(player.getCreation());

    player.setDestroying(false);
    assertFalse(player.isDestroying());

    player.setCreation(new FakeMobWritable());
    player.setDestroying(true);
    assertTrue(player.isDestroying());
    assertNull(player.getCreation());

    IMobWritable data = new FakeMobWritable();
    player.setDestroying(true);
    player.setCreation(data);
    assertFalse(player.isDestroying());
    assertEquals(data, player.getCreation());
  }

  @Test
  public void isCreating() {
    IMobPortalPlayer player = new MobPortalPlayer(new FakePlayer(), editors);
    IMobWritable data = new FakeMobWritable();

    player.setCreation(data);
    assertTrue(player.isCreating());

    player.setCreation(null);
    assertFalse(player.isCreating());

    player.setCreation(data);
    player.setDestroying(true);
    assertFalse(player.isCreating());
  }
}