package cn.SuperSkidder.PVPStage.Modules.player;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventUpdate;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import org.lwjgl.input.Keyboard;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", ModCategory.Player);
        this.setKey(Keyboard.KEY_R);
    }
    enum test3m{
        Test1,Test2,Test3
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.movementInput.moveForward>0||mc.thePlayer.movementInput.moveStrafe>0){
            mc.thePlayer.setSprinting(true);
        }
    }

    @Override
    public void onDisable(){
        mc.thePlayer.setSprinting(false);
    }
}
