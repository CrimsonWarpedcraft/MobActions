package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.listener.CommandListener;
import com.snowypeaksystems.mobactions.listener.EventListener;
import com.snowypeaksystems.mobactions.listener.ICommandListener;
import com.snowypeaksystems.mobactions.mob.data.warp.IWarpManager;
import com.snowypeaksystems.mobactions.mob.data.warp.WarpManager;
import com.snowypeaksystems.mobactions.player.IMobActionsPlayer;
import com.snowypeaksystems.mobactions.player.MobActionsPlayer;
import com.snowypeaksystems.mobactions.util.Messages;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

/**
 * Main class which handles initialization of the plugin. Additionally,
 * provides methods to be used throughout the codebase.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobActions extends AMobActions {
  private IWarpManager warps;
  private HashMap<Player, IMobActionsPlayer> players;

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
      warps = new WarpManager(warpDir);
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

    players = new HashMap<>();

    ICommandListener cl = new CommandListener(this);
    cmd.setExecutor(cl);
    cmd.setTabCompleter(cl);

    getServer().getPluginManager().registerEvents(new EventListener(this), this);

    getLogger().info("Rise and shine, MobActions is ready to go!");
    getLogger().info("Please consider donating at https://github.com/sponsors/leviem1/");
  }

  @Override
  public IWarpManager getWarpManager() {
    return warps;
  }

  @Override
  public IMobActionsPlayer getPlayer(Player player) {
    if (players.containsKey(player)) {
      return players.get(player);
    }

    IMobActionsPlayer wrappedPlayer = new MobActionsPlayer(player);

    players.put(player, wrappedPlayer);

    return wrappedPlayer;
  }

  @Override
  public void reloadConfig() {
    super.reloadConfig();
    Messages.initialize();
    warps.reload();
    players = new HashMap<>();

    getLogger().info("MobActions reloaded successfully!");
  }
}