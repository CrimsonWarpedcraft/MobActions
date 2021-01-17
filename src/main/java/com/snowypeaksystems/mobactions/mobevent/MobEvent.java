package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.actions.EventMobStartAction;
import com.snowypeaksystems.mobactions.actions.IEventMobStartAction;
import com.snowypeaksystems.mobactions.data.MobData;
import com.snowypeaksystems.mobactions.event.MobEventStartEvent;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class MobEvent implements IMobEvent {
  private final String name;
  private final int maxPlayers;
  private final long timeout;
  private final BukkitRunnable timeoutCounter;
  private final BukkitRunnable countdown;
  private final Set<MobActionsUser> users;
  private final AMobActions plugin;
  private final MobData data;
  private State state;
  // todo: declare file variable

  MobEvent(String name, MobData data, long timeout, AMobActions plugin, int maxPlayers) {
    this.name = name;
    this.data = data;
    this.timeout = timeout;
    this.plugin = plugin;
    this.maxPlayers = maxPlayers;
    state = State.CLOSED;
    users = new HashSet<>();
    // todo: instantiate file variable

    MobEvent e = this;
    IEventMobStartAction action = new EventMobStartAction(data, plugin);

    this.countdown = new BukkitRunnable() {
      private int seconds = 10;
      @Override
      public void run() {
        if (seconds < 1) {
          state = State.CLOSED;

          MobEventStartEvent event = new MobEventStartEvent(e);
          Bukkit.getPluginManager().callEvent(event);
          if (!event.isCancelled()) {
            for (MobActionsUser user : users) {
              try {
                action.run(user);
              } catch (PlayerException e) {
                user.sendMessage(e.getPlayerFormattedString());
              }
            }
          } else {
            DebugLogger.getLogger().log("Event cancelled");
          }

        } else {
          seconds--;
          for (MobActionsUser user : users) {
            user.sendMessage(gm("event-countdown-text", name, String.valueOf(seconds)));
          }
        }
      }
    };

    this.timeoutCounter = new BukkitRunnable() {
      @Override
      public void run() {
        state = State.COUNTDOWN;
        countdown.runTaskTimer(plugin, 0, 20);
      }
    };
  }

  // todo: create constructor for loading from file

  @Override
  public void addPlayer(MobActionsUser player) throws EventStateException {
    if (state != State.OPEN) {
      throw new EventStateException(gm("event-closed-error", name));
    }

    users.add(player);

    if (users.size() == maxPlayers) {
      state = State.COUNTDOWN;
      force();
    }
  }

  @Override
  public void removePlayer(MobActionsUser player) {
    users.remove(player);
  }

  @Override
  public boolean hasPlayerJoined(MobActionsUser player) {
    return users.contains(player);
  }

  @Override
  public Set<MobActionsUser> getPlayerSet() {
    return Set.copyOf(users);
  }

  @Override
  public void open() throws EventStateException {
    if (state != State.CLOSED) {
      throw new EventStateException(gm("event-already-open-error", name));
    }

    state = State.OPEN;
    timeoutCounter.runTaskLater(plugin, timeout * 20);
  }

  @Override
  public void forceStart() throws EventStateException {
    if (state != State.OPEN) {
      throw new EventStateException(gm("event-closed-error", name));
    }

    state = State.COUNTDOWN;
    force();
  }

  @Override
  public void cancel() {
    if (!timeoutCounter.isCancelled()) {
      timeoutCounter.cancel();
    }

    if (!countdown.isCancelled()) {
      countdown.cancel();
    }
  }

  @Override
  public State getState() {
    return state;
  }

  private void force() {
    if (!timeoutCounter.isCancelled()) {
      timeoutCounter.cancel();
    }

    countdown.runTaskTimer(plugin, 0, 20);
  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public void save() throws IOException {
    // todo: write data to file
    // name, timeout, max players, data key
    // if data is command, command string
    // if data is warp, warp name
  }

  @Override
  public boolean delete() {
    // todo: delete file
    return false;
  }
}
