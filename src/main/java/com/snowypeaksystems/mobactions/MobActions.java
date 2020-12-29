package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.listener.CommandListener;
import com.snowypeaksystems.mobactions.listener.EventListener;
import com.snowypeaksystems.mobactions.listener.ICommandListener;
import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.player.ConsoleUser;
import com.snowypeaksystems.mobactions.player.MobActionsPlayer;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.util.Messages;
import com.snowypeaksystems.mobactions.warp.IWarpManager;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Main class which handles initialization of the plugin. Additionally,
 * provides methods to be used throughout the codebase.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobActions extends AMobActions {
  private IWarpManager warps;
  private IMobEventManager events;
  private Map<Player, MobActionsUser> players;

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();

    File warpDir = new File(getDataFolder(), "warps");
    if (!warpDir.exists() && !warpDir.mkdirs()) {
      getLogger().log(Level.SEVERE, "Could not create warp data folder! Aborting!");
      setEnabled(false);
      return;
    }

    try {
      warps = new WarpManager(warpDir);
      warps.reload();
    } catch (FileNotFoundException e) {
      getLogger().log(Level.SEVERE, e.getMessage(), e);
      setEnabled(false);
      return;
    }

    PluginCommand cmd = getCommand("mac");
    if (cmd == null) {
      getLogger().severe("PluginCommand \"mac\" not found");
      setEnabled(false);
      return;
    }

    ICommandListener cl = new CommandListener(this);
    cmd.setExecutor(cl);
    cmd.setTabCompleter(cl);

    players = new HashMap<>();
    events = new MobEventManager(this);

    getServer().getPluginManager().registerEvents(new EventListener(this, players), this);

    getLogger().info("Rise and shine, MobActions is ready to go!");
    getLogger().info("Please consider donating at https://github.com/sponsors/leviem1/");
  }

  @Override
  public IWarpManager getWarpManager() {
    return warps;
  }

  @Override
  public MobActionsUser getPlayer(Player player) {
    if (players.containsKey(player)) {
      return players.get(player);
    }

    MobActionsUser wrappedPlayer = new MobActionsPlayer(player);

    players.put(player, wrappedPlayer);

    return wrappedPlayer;
  }

  @Override
  public MobActionsUser getPlayer(CommandSender sender) {
    if (sender instanceof Player) {
      return getPlayer((Player) sender);
    }

    return new ConsoleUser((ConsoleCommandSender) sender);
  }

  @Override
  public IInteractiveMob getInteractiveMob(LivingEntity entity) throws IncompleteDataException {
    return new InteractiveMob(entity, this);
  }

  @Override
  public IMobEventManager getMobEventManager() {
    return events;
  }

  @Override
  public void reloadConfig() {
    super.reloadConfig();
    Messages.initialize();
    warps.reload();
    players.clear();
    events.clear();

    getLogger().info("MobActions reloaded successfully!");
  }
}