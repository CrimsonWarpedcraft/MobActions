package com.snowypeaksystems.mobactions.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.snowypeaksystems.mobactions.data.CommandData;
import com.snowypeaksystems.mobactions.data.CommandDataImpl;
import org.junit.jupiter.api.Test;

/**
 * Tests for StatusImpl class.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class StatusImplTest {

  @Test
  void getMode() {
    Status status = new StatusImpl();
    assertEquals(Status.Mode.NONE, status.getMode());

    status.setMode(Status.Mode.CREATING);
    assertEquals(Status.Mode.CREATING, status.getMode());

    status.setMode(Status.Mode.DESTROYING);
    assertEquals(Status.Mode.DESTROYING, status.getMode());

    status.setMode(Status.Mode.NONE);
    assertEquals(Status.Mode.NONE, status.getMode());

    assertThrows(NullPointerException.class, () -> status.setMode(null));
  }

  @Test
  void getMobData() {
    CommandData command = new CommandDataImpl("", "");

    Status status = new StatusImpl();
    assertNull(status.getMobData());

    status.setMode(Status.Mode.CREATING);
    status.setMobData(command);
    assertEquals(command, status.getMobData());

    status.setMode(Status.Mode.DESTROYING);
    assertNull(status.getMobData());

    status.setMobData(command);
    assertNull(status.getMobData());

    status.setMode(Status.Mode.CREATING);
    status.setMobData(command);
    status.setMode(Status.Mode.NONE);
    assertNull(status.getMobData());

    status.setMobData(command);
    assertNull(status.getMobData());
  }
}