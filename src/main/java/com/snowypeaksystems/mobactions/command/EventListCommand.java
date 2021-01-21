package com.snowypeaksystems.mobactions.command;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import com.snowypeaksystems.mobactions.mobevent.IMobEventManager;
import com.snowypeaksystems.mobactions.player.MobActionsUser;
import com.snowypeaksystems.mobactions.player.PermissionException;
import com.snowypeaksystems.mobactions.player.PlayerException;
import com.snowypeaksystems.mobactions.util.DebugLogger;
import java.util.Set;

public class EventListCommand implements IEventListCommand {
  private final IMobEventManager em;

  public EventListCommand(IMobEventManager em) {
    this.em = em;
  }

  @Override
  public void run(MobActionsUser player) throws PlayerException {
    DebugLogger.getLogger().log("Listing events");
    if (!player.canGetEventInfo()) {
      DebugLogger.getLogger().log("Permission error");
      throw new PermissionException();
    }

    Set<String> events = em.getLoadedEventNames();

    if (events.size() > 0) {
      String eventsStr = String.join(", ", events);
      player.sendMessage(gm("event-list-message"), eventsStr);
    } else {
      player.sendMessage(gm("event-list-empty-message"));
    }

    DebugLogger.getLogger().log("Events listed");
  }
}
