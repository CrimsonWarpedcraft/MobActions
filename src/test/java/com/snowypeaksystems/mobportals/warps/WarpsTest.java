package com.snowypeaksystems.mobportals.warps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.snowypeaksystems.mobportals.mock.FakeServer;
import com.snowypeaksystems.mobportals.mock.FakeWorld;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import org.bukkit.Location;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for Warps class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
public class WarpsTest {
  private static File warpDir;
  private static IWarps warps;
  private static Location loc;

  /** Creates a WarpTest with a warpDir File. */
  @BeforeClass
  public static void setup() {
    loc = new Location(new FakeWorld(), 0, 0, 0);
    warpDir = new File("tests/warps/");
    warpDir.delete();
    warpDir.mkdirs();
    try {
      warps = new Warps(warpDir, new FakeServer(loc.getWorld()));
    } catch (FileNotFoundException e) {
      fail();
    }
  }

  @Test
  public void add() {
    IWarp warp1 = warps.create("test", loc);
    assertNull(warps.add(warp1));
    assertEquals(1, warps.size());

    IWarp warp2 = warps.create("test", loc);
    assertEquals(warp1, warps.add(warp2));
    assertEquals(1, warps.size());

    warps.remove("test");
  }

  @Test
  public void remove() {
    warps.add(warps.create("Test", loc));
    warps.remove("test");
    assertEquals(0, warps.size());
  }

  @Test
  public void get() {
    IWarp warp = warps.create("Test", loc);
    warps.add(warp);
    assertEquals(warp, warps.get("test"));
    warps.remove("Test");
  }

  @Test
  public void exists() {
    warps.add(warps.create("Test", loc));
    assertTrue(warps.exists("test"));
    warps.remove("test");
  }

  @Test
  public void getWarpNames() {
    warps.add(warps.create("Test", loc));
    warps.add(warps.create("Test2", loc));
    Set<String> names = warps.getWarpNames();
    assertTrue(names.contains("Test"));
    assertTrue(names.contains("Test2"));
    assertEquals(2, names.size());
    warps.remove("Test");
    warps.remove("test2");
  }

  @Test
  public void reload() {
    IWarp warp = warps.create("Test", loc);
    try {
      warp.save();
    } catch (IOException e) {
      fail();
    }
    warps.add(warp);
    warps.reload();
    assertEquals(warp.getDestination(), warps.get("Test").getDestination());
    warps.remove("Test").delete();
    warps.reload();
    assertEquals(0, warps.size());
  }

  @AfterClass
  public static void tearDown() {
    warpDir.delete();
    assertFalse(warpDir.exists());
  }
}