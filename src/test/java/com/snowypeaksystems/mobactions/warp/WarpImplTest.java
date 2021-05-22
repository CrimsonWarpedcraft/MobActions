package com.snowypeaksystems.mobactions.warp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.snowypeaksystems.mobactions.mock.FakeWorld;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for WarpImpl.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WarpImplTest {
  private static File file;
  private static World world; //Need to keep a reference ourselves or gc will set to null
  private WarpManager wm;

  @BeforeAll
  static void setUp() {
    file = new File("testDir");
    if (file.exists() || !file.mkdirs()) {
      fail();
    }

    world = new FakeWorld();
  }

  @AfterAll
  static void tearDown() {
    if (file.exists()) {
      if (!file.delete()) {
        fail();
      }
    }
  }

  @BeforeEach
  void setUpTest() throws FileNotFoundException {
    wm = new WarpManagerImpl(file);
  }

  @Test
  void getAlias() throws IOException {
    Warp warp = wm.makeWarp("test", new Location(world, 0, 0, 0));
    assertEquals("test", warp.getAlias());
    warp.delete();
  }

  @Test
  void save() throws IOException {
    Warp warp = wm.makeWarp("test1", new Location(world, 0, 0, 0));
    warp.delete();
    assertFalse(new File(file, "110251487").exists());

    warp.save();
    assertTrue(new File(file, "110251487").exists());

    warp.delete();
  }

  @Test
  void delete() throws IOException {
    Warp warp = wm.makeWarp("test2", new Location(world, 0, 0, 0));

    assertTrue(new File(file, "110251488").exists());

    assertTrue(warp.delete());

    assertFalse(new File(file, "110251488").exists());
  }

  @Test
  void getDestination() throws IOException {
    Location loc = new Location(world, 0, 0, 0);
    Warp warp = wm.makeWarp("", loc);

    assertEquals(loc, warp.getDestination());
    warp.delete();
  }
}