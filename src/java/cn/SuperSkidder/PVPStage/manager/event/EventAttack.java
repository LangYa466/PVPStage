package cn.SuperSkidder.PVPStage.manager.event;

import cn.SuperSkidder.PVPStage.manager.event.events.Event;
import net.minecraft.entity.Entity;

public class EventAttack implements Event {
    public Entity target;
    public EventAttack(Entity t){
        target = t;
    }
    public Entity getTarget(){
        return this.target;
    }
}
