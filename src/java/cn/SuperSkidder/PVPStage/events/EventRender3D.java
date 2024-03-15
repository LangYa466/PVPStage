package cn.SuperSkidder.PVPStage.events;

import cn.SuperSkidder.PVPStage.manager.event.events.Event;
import net.optifine.shaders.Shaders;

public class EventRender3D
        implements Event {
    public float ticks;
    private boolean isUsingShaders;

    public EventRender3D() {
        this.isUsingShaders = Shaders.getShaderPackName() != null;
    }

    public EventRender3D(float ticks) {
        this.ticks = ticks;
        this.isUsingShaders = Shaders.getShaderPackName() != null;
    }

    public float getPartialTicks() {
        return this.ticks;
    }

    public boolean isUsingShaders() {
        return this.isUsingShaders;
    }
}

