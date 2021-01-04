package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class EventData implements IEventData {
  private final String name;

  /** Create a new EventData object from a name, maxPlayers, and data. */
  public EventData(String name) {
    this.name = name;
  }

  /** Create EventData from the supplied LivingEntity and plugin. */
  public EventData(LivingEntity entity, JavaPlugin plugin) throws IncompleteDataException {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);

    if (!container.has(eventKey, PersistentDataType.STRING)) {
      throw new IncompleteDataException();
    }

    this.name = container.get(eventKey, PersistentDataType.STRING);
  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public void store(LivingEntity entity, JavaPlugin plugin) {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);
    container.set(eventKey, PersistentDataType.STRING, name);
  }

  @Override
  public void purge(LivingEntity entity, JavaPlugin plugin) {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);
    container.remove(eventKey);
  }

  @Override
  public String getKeyString() {
    return EVENT_KEY;
  }

  @Override
  public String getNametagString() {
    return gm("nametag-event-text", name);
  }
}
