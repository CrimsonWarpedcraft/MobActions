package com.snowypeaksystems.mobportals;

import com.snowypeaksystems.mobportals.listeners.CommandListener;
import com.snowypeaksystems.mobportals.listeners.EventListener;
import com.snowypeaksystems.mobportals.messages.Messages;
import com.snowypeaksystems.mobportals.mobs.IPortalMob;
import com.snowypeaksystems.mobportals.mobs.PortalMob;
import com.snowypeaksystems.mobportals.persistence.IMobWritable;
import com.snowypeaksystems.mobportals.warps.IWarps;
import com.snowypeaksystems.mobportals.warps.Warps;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

/**
 * Main class which handles initialization of the plugin. Additionally,
 * provides methods to be used throughout the codebase.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobPortals extends AbstractMobPortals {
  private IWarps warps;
  private HashMap<Player, IMobWritable> editors;

  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();

    File warpDir = new File(getDataFolder(), "warps");
    if (!warpDir.exists()) {
      warpDir.mkdirs(); // If false, will be caught below
    }

    try {
      warps = new Warps(warpDir, getServer());
    } catch (FileNotFoundException e) {
      getLogger().log(Level.SEVERE, e.getMessage(), e);
      setEnabled(false);
      return;
    }

    PluginCommand cmd = getCommand("mp");
    if (cmd == null) {
      getLogger().severe("PluginCommand \"mp\" not found");
      setEnabled(false);
      return;
    }

    editors = new HashMap<>();

    TabExecutor cl = new CommandListener(this);
    cmd.setExecutor(cl);
    cmd.setTabCompleter(cl);

    getServer().getPluginManager().registerEvents(new EventListener(this), this);

    getLogger().config("Rise and shine, MobPortals is ready to go!");
    getLogger().config("Please consider donating at https://github.com/sponsors/leviem1/");
  }

  @Override
  public IPortalMob getPortalMob(LivingEntity entity) {
    if (entity.getPersistentDataContainer().has(
        IPortalMob.getKey(this), PersistentDataType.STRING)) {
      return new PortalMob(entity, this);
    }

    return null;
  }

  @Override
  public IWarps getWarps() {
    return warps;
  }

  @Override
  public IMobPortalPlayer getPlayer(Player player) {
    return new MobPortalPlayer(player, editors);
  }

  @Override
  public void reloadConfig() {
    super.reloadConfig();
    Messages.initialize();
    warps.reload();
    editors = new HashMap<>();

    getLogger().info("MobPortals reloaded successfully!");
  }
}