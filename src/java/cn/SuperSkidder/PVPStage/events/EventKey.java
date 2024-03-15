package cn.SuperSkidder.PVPStage.events;

import cn.SuperSkidder.PVPStage.manager.event.events.Event;

public class EventKey implements Event {
    int key;

    public EventKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
