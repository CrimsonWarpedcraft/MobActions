package com.snowypeaksystems.mobactions.listener;

import com.snowypeaksystems.mobactions.AMobActions;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * Handles all events relevant to the plugin, excluding command handlers.
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EventListener implements IEventListener {
  private final AMobActions ma;

  public EventListener(AMobActions parent) {
    ma = parent;
  }

  @Override
  public void onMobInteract(PlayerInteractEntityEvent event) {
    /*
    if (event.getHand().equals(EquipmentSlot.OFF_HAND)
        || !(event.getRightClicked() instanceof LivingEntity)
        || event.getRightClicked() instanceof Player) {
      return;
    }
    */

    //TODO: The rest
  }

  @Override
  public void onMobDamage(EntityDamageEvent event) {
    /*
    if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player) {
      return;
    }
    */


    //TODO: The rest
  }

  @Override
  public void onWorldLoad(WorldLoadEvent event) {
    ma.getWarpManager().reload();
  }
}
