package cn.SuperSkidder.PVPStage.Modules.gui;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.guis.Clickgui;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Mod {
    public ClickGui(){
        super("ClickGui", ModCategory.Gui);
        this.setKey(Keyboard.KEY_RSHIFT);
    }
    @Override
    public void onEnabled(){
        mc.displayGuiScreen(new Clickgui());
        this.setStage(false);
    }
}
