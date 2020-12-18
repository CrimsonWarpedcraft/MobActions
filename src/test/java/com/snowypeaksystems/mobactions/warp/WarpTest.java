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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Warp.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WarpTest {
  private static File file;
  private IWarpManager wm;

  @BeforeAll
  static void setUp() {
    file = new File("testDir");
    if (file.exists() || !file.mkdirs()) {
      fail();
    }
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
    wm = new WarpManager(file);
  }

  @Test
  void getAlias() throws IOException {
    IWarp warp = wm.makeWarp("test", new Location(new FakeWorld(), 0, 0, 0));
    assertEquals("test", warp.getAlias());
    warp.delete();
  }

  @Test
  void save() throws IOException {
    IWarp warp = wm.makeWarp("", new Location(new FakeWorld(), 0, 0, 0));
    warp.delete();
    assertFalse(new File(file, "0").exists());

    warp.save();
    assertTrue(new File(file, "0").exists());

    warp.delete();
  }

  @Test
  void delete() throws IOException {
    IWarp warp = wm.makeWarp("", new Location(new FakeWorld(), 0, 0, 0));

    warp.save();
    assertTrue(warp.delete());

    assertFalse(new File(file, "0").exists());
  }

  @Test
  void getDestination() throws IOException {
    Location loc = new Location(new FakeWorld(), 0, 0, 0);
    IWarp warp = wm.makeWarp("", loc);

    assertEquals(loc, warp.getDestination());
    warp.delete();
  }
}