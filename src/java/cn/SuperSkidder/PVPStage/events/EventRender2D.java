package cn.SuperSkidder.PVPStage.events;


import cn.SuperSkidder.PVPStage.manager.event.events.Event;

public class EventRender2D
        implements Event {
    private float partialTicks;

    public EventRender2D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}

