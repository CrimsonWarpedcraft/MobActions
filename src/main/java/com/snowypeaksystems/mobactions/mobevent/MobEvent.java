package com.snowypeaksystems.mobactions.mobevent;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.AMobActions;
import com.snowypeaksystems.mobactions.actions.MobAction;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.scheduler.BukkitRunnable;

public class MobEvent implements IMobEvent {
  private final int maxPlayers;
  private final Set<MobActionsUser> users;
  private final BukkitRunnable timeout;
  private final BukkitRunnable countdown;

  MobEvent(String name, int maxPlayers, long timeout, MobAction action, AMobActions plugin) {
    this.maxPlayers = maxPlayers;
    users = new HashSet<>();

    this.countdown = new BukkitRunnable() {
      private int seconds = 10;
      @Override
      public void run() {
        if (seconds < 1) {
          plugin.getMobEventManager().removeEvent(name);

          for (MobActionsUser user : users) {
            if (user.isOnline()) {
              try {
                action.run(user);
              } catch (PlayerException e) {
                user.sendMessage(e.getPlayerFormattedString());
              }
            }
          }
        } else {
          seconds--;

          for (MobActionsUser user : users) {
            if (user.isOnline()) {
              user.sendMessage(gm("countdown-text", name, String.valueOf(seconds)));
            }
          }
        }
      }
    };

    this.timeout = new BukkitRunnable() {
      @Override
      public void run() {
          countdown.runTaskTimer(plugin, 0, 20);
      }
    };

    this.timeout.runTaskLater(plugin, timeout * 20);
  }

  @Override
  public int getMaxPlayers() {
    return maxPlayers;
  }

  @Override
  public BukkitRunnable getRunnableTask() {
    return timeout;
  }

  @Override
  public void addPlayer(MobActionsUser player) {
    users.add(player);
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
}
