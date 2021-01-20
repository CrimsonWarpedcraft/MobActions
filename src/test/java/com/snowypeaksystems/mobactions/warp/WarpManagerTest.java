package com.snowypeaksystems.mobactions.warp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
 * Tests for the WarpManager class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WarpManagerTest {
  private static File file;
  private static World world; //Need to keep a reference ourselves or gc will set to null
  private Location testLoc1;
  private Location testLoc2;
  private IWarpManager testWarpManager;
  private IWarp testWarp1;

  @BeforeAll
  static void setUp() {
    file = new File("test");
    if (!file.mkdirs()) {
      fail();
    }

    world = new FakeWorld();
  }

  @AfterAll
  static void tearDown() {
    if (file == null || !file.delete()) {
      fail();
    }
  }

  @BeforeEach
  void start() throws FileNotFoundException {
    testLoc1 = new Location(world, 0, 0, 0);
    testLoc2 = new Location(world, 0, 0, 0);

    testWarpManager = new WarpManager(file);
  }

  @Test
  void makeWarp() throws IOException {
    testWarp1 = testWarpManager.makeWarp("TESTWARP1", testLoc1);

    assertEquals("testwarp1", testWarp1.getAlias());
    assertEquals(testLoc1, testWarp1.getDestination());

    IWarp testWarp2 = testWarpManager.makeWarp("testWarp1", testLoc2);
    assertEquals(testWarp2, testWarpManager.getWarp("testwarp1"));

    assertTrue(testWarp1.delete());
    assertFalse(testWarp2.delete());
  }

  @Test
  void getWarp() throws IOException {
    assertNull(testWarpManager.getWarp("testWarp1"));
    testWarp1 = testWarpManager.makeWarp("testWarp1", testLoc1);

    assertEquals(testWarp1, testWarpManager.getWarp("testwarp1"));

    testWarp1.delete();
  }

  @Test
  void unregister() throws IOException {
    testWarp1 = testWarpManager.makeWarp("testWarp1", testLoc1);
    assertTrue(testWarpManager.exists("testWarp1"));

    testWarpManager.unregister("testWarp1");
    assertFalse(testWarpManager.exists("testWarp1"));

    assertFalse(testWarp1.delete());
  }

  @Test
  void exists() throws IOException {
    assertFalse(testWarpManager.exists("testWarp"));
    testWarpManager.makeWarp("testWarp", testLoc1).delete();
    assertTrue(testWarpManager.exists("testWarp"));
  }

  @Test
  void getLoadedWarpNames() throws IOException {
    assertEquals(0, testWarpManager.getLoadedWarpNames().size());

    testWarpManager.makeWarp("testWarp", testLoc1).delete();
    assertEquals(1, testWarpManager.getLoadedWarpNames().size());
    testWarpManager.makeWarp("testwarp", testLoc2).delete();
    assertEquals(1, testWarpManager.getLoadedWarpNames().size());

    testWarpManager.makeWarp("testWarp2", testLoc1).delete();
    assertEquals(2, testWarpManager.getLoadedWarpNames().size());

    assertTrue(testWarpManager.getLoadedWarpNames().contains("testwarp"));
    assertTrue(testWarpManager.getLoadedWarpNames().contains("testwarp2"));
  }
}