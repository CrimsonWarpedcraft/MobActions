package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.snowypeaksystems.mobactions.mob.data.command.CommandData;
import com.snowypeaksystems.mobactions.mob.data.command.ICommandData;
import org.junit.jupiter.api.Test;

/**
 * Tests for Status class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class StatusTest {

  @Test
  void getMode() {
    IStatus status = new Status();
    assertEquals(IStatus.Mode.NONE, status.getMode());

    status.setMode(IStatus.Mode.CREATING);
    assertEquals(IStatus.Mode.CREATING, status.getMode());

    status.setMode(IStatus.Mode.DESTROYING);
    assertEquals(IStatus.Mode.DESTROYING, status.getMode());

    status.setMode(IStatus.Mode.NONE);
    assertEquals(IStatus.Mode.NONE, status.getMode());

    try {
      status.setMode(null);
      fail();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  @Test
  void getMobData() {
    ICommandData command = new CommandData("", "");

    IStatus status = new Status();
    assertNull(status.getMobData());

    status.setMode(IStatus.Mode.CREATING);
    status.setMobData(command);
    assertEquals(command, status.getMobData());

    status.setMode(IStatus.Mode.NONE);
    assertNull(status.getMobData());

    status.setMobData(command);
    assertNull(status.getMobData());
  }
}