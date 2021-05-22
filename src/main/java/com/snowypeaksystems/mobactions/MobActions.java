package com.snowypeaksystems.mobactions;

import com.snowypeaksystems.mobactions.data.IncompleteDataException;
import com.snowypeaksystems.mobactions.listener.CommandListener;
import com.snowypeaksystems.mobactions.listener.CommandListenerImpl;
import com.snowypeaksystems.mobactions.listener.EventListenerImpl;
import com.snowypeaksystems.mobactions.mobevent.MobEventManager;
import com.snowypeaksystems.mobactions.mobevent.MobEventManagerImpl;
import com.snowypeaksystems.mobactions.player.ConsoleUser;
import com.snowypeaksystems.mobactions.player.MobActionsPlayer;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.util.Messages;
import com.snowypeaksystems.mobactions.warp.WarpManager;
import com.snowypeaksystems.mobactions.warp.WarpManagerImpl;
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
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MobActions extends AbstractMobActions {
  private WarpManager warps;
  private MobEventManager events;
  private Map<Player, MobActionsUser> players;

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();
    Messages.setDataDir(getDataFolder());

    File warpDir = new File(getDataFolder(), "warps");
    if (!warpDir.exists() && !warpDir.mkdirs()) {
      getLogger().log(Level.SEVERE, "Could not create warp data folder! Aborting!");
      setEnabled(false);
      return;
    }

    try {
      warps = new WarpManagerImpl(warpDir);
    } catch (FileNotFoundException e) {
      getLogger().log(Level.SEVERE, e.getMessage(), e);
      setEnabled(false);
      return;
    }

    File eventDir = new File(getDataFolder(), "events");
    if (!eventDir.exists() && !eventDir.mkdirs()) {
      getLogger().log(Level.SEVERE, "Could not create event data folder! Aborting!");
      setEnabled(false);
      return;
    }

    try {
      events = new MobEventManagerImpl(this, eventDir);
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

    CommandListener cl = new CommandListenerImpl(this);
    cmd.setExecutor(cl);
    cmd.setTabCompleter(cl);

    players = new HashMap<>();

    getServer().getPluginManager().registerEvents(new EventListenerImpl(this, players), this);

    getLogger().info("Rise and shine, MobActions is ready to go!");
    getLogger().info("Please consider donating at https://github.com/sponsors/leviem1/");
  }

  @Override
  public WarpManager getWarpManager() {
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

    return new ConsoleUser((ConsoleCommandSender) sender, getServer());
  }

  @Override
  public InteractiveMob getInteractiveMob(LivingEntity entity) throws IncompleteDataException {
    return new InteractiveMobImpl(entity, this);
  }

  @Override
  public MobEventManager getMobEventManager() {
    return events;
  }

  @Override
  public void reloadConfig() {
    super.reloadConfig();
    Messages.setDataDir(getDataFolder());
    Messages.initialize();
    warps.reload();
    players.clear();
    events.reload();

    getLogger().info("MobActions reloaded successfully!");
  }
}