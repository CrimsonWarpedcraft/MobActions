package com.snowypeaksystems.mobactions.data;

public interface IEventData extends MobData {
  String EVENT_KEY = "event";
  String EVENT_ALIAS_KEY = "event-alias";

  MobData getEventData();
}
