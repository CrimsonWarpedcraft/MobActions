package com.snowypeaksystems.mobactions.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for Message class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class MessageTest {
  @Test
  void getTokenCount() {
    Message data = new Message("test");
    assertEquals(0, data.getTokenCount());

    data = new Message("te{}st");
    assertEquals(1, data.getTokenCount());

    data = new Message("{}st te{}");
    assertEquals(2, data.getTokenCount());

    data = new Message("{}st{}te{}");
    assertEquals(3, data.getTokenCount());
  }
}