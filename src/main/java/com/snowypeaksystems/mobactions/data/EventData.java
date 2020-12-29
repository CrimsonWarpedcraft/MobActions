package com.snowypeaksystems.mobactions.data;

import static com.snowypeaksystems.mobactions.util.Messages.gm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class EventData implements IEventData {
  private final String name;
  private final MobData data;

  /** Create a new EventData object from a name, maxPlayers, and data. */
  public EventData(String name, MobData data) {
    this.name = name;
    this.data = data;
  }

  /** Create EventData from the supplied LivingEntity and plugin. */
  public EventData(LivingEntity entity, JavaPlugin plugin) throws IncompleteDataException {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey aliasKey = new NamespacedKey(plugin, EVENT_ALIAS_KEY);
    NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);

    if (!container.has(aliasKey, PersistentDataType.STRING)
        || !container.has(eventKey, PersistentDataType.STRING)) {
      throw new IncompleteDataException();
    }

    this.name = container.get(aliasKey, PersistentDataType.STRING);

    try {
      Constructor<? extends MobData> c = MobData.DATA_KEY_MAP.get(
          container.get(eventKey, PersistentDataType.STRING))
          .getConstructor(LivingEntity.class, JavaPlugin.class);
      data = c.newInstance(entity, plugin);

      if (data instanceof IEventData) {
        throw new IllegalArgumentException("Cannot use stored EventData as an event's data");
      }
    } catch (InvocationTargetException | IllegalAccessException | InstantiationException
        | NoSuchMethodException e) {
      if (e.getCause() instanceof IncompleteDataException) {
        throw (IncompleteDataException) e.getCause();
      }

      throw new IncompleteDataException();
    }
  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public MobData getEventData() {
    return data;
  }

  @Override
  public void store(LivingEntity entity, JavaPlugin plugin) {
    if (data != null) {
      PersistentDataContainer container = entity.getPersistentDataContainer();
      NamespacedKey aliasKey = new NamespacedKey(plugin, EVENT_ALIAS_KEY);
      NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);

      container.set(aliasKey, PersistentDataType.STRING, name);
      container.set(eventKey, PersistentDataType.STRING, data.getKeyString());

      data.store(entity, plugin);
    }
  }

  @Override
  public void purge(LivingEntity entity, JavaPlugin plugin) {
    PersistentDataContainer container = entity.getPersistentDataContainer();
    NamespacedKey aliasKey = new NamespacedKey(plugin, EVENT_ALIAS_KEY);
    NamespacedKey eventKey = new NamespacedKey(plugin, EVENT_KEY);

    container.remove(aliasKey);
    container.remove(eventKey);

    if (data != null) {
      data.purge(entity, plugin);
    }
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
