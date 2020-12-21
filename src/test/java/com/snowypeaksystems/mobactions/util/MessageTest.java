package com.snowypeaksystems.mobactions.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for Message class.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class MessageTest {
  @Test
  void replace() {
    /*
    1) "&ctest".replace() "§ctest"
    2) "test {} test".replace("test") "test §rtest§r test"
    3) "&ctest {} test".replace("test") "§ctest §rtest§r§c test"
    4) "&ctest {&d} test".replace("test") "§ctest §r§dtest§r§c test"
    5) "&ctest {&d} §n {&e} test".replace("test", "test") "§ctest §r§dtest§r§c §n §r§etest§r§c§n test"
    6) "test {} test".replace() "fail"
    7) "test {} test".replace("test", "test") "fail"
     */
    IMessage message = new Message("&ctest");
    assertEquals("§ctest", message.replace());

    message = new Message("test {} test");
    assertEquals("test §rtest§r test", message.replace("test"));
    assertThrows(IllegalArgumentException.class, message::replace);
    assertThrows(IllegalArgumentException.class, message::replace);

    message = new Message("&ctest {} test");
    assertEquals("§ctest §rtest§r§c test", message.replace("test"));

    message = new Message("&ctest {&d} test");
    assertEquals("§ctest §r§dtest§r§c test", message.replace("test"));

    message = new Message("&ctest {&d} §n {&e} test");
    assertEquals("§ctest §r§dtest§r§c §n §r§etest§r§c§n test",
        message.replace("test", "test"));
  }
}