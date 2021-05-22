package com.snowypeaksystems.mobactions.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.bukkit.ChatColor;
import org.junit.jupiter.api.Test;

/**
 * Tests for MessageImpl class.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class MessageImplTest {
  @Test
  void replace() {
    Message message = new MessageImpl("&ctes\\\\t");
    assertEquals(ChatColor.translateAlternateColorCodes('&', "&ctes\\\\t"), message.replace());

    message = new MessageImpl("test {} test");
    assertEquals("test test test", message.replace("test"));
    assertThrows(IllegalArgumentException.class, message::replace);
    assertThrows(IllegalArgumentException.class, message::replace);

    message = new MessageImpl("\\\\{} \\{\\} {}");
    assertEquals("\\test {} test", message.replace("test", "test", "test"));
    message = new MessageImpl("\\{\\} \\\\{} {}");
    assertEquals("{} \\test test", message.replace("test", "test", "test"));
    message = new MessageImpl("{} \\{\\} \\\\{}");
    assertEquals("test {} \\test", message.replace("test", "test", "test"));

    message = new MessageImpl("&ctest {} test");
    assertEquals(
        ChatColor.translateAlternateColorCodes('&', "&ctest test test"),
        message.replace("test"));

    message = new MessageImpl("&ctest \\\\{&d} test");
    assertEquals(
        ChatColor.translateAlternateColorCodes('&', "&ctest \\&r&dtest&r&c test"),
        message.replace("test"));

    message = new MessageImpl("&ctest {&d} &n {&e} test");
    assertEquals(
        ChatColor.translateAlternateColorCodes('&', "&ctest &r&dtest&r&c &n &r&etest&r&c&n test"),
        message.replace("test", "test"));
  }
}