package com.snowypeaksystems.mobportals.mobs;

import com.snowypeaksystems.mobportals.IMobCommand;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Objects that represent mobs that run commands.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public interface ICommandMob extends IMob<IMobCommand> {
  /** Returns the key used for this IMob's persistent storage. */
  static NamespacedKey getCommandKey(JavaPlugin plugin) {
    return new NamespacedKey(plugin, "commandRaw");
  }

  /** Returns the key used for this IMob's persistent storage. */
  static NamespacedKey getNameKey(JavaPlugin plugin) {
    return new NamespacedKey(plugin, "commandName");
  }
}
