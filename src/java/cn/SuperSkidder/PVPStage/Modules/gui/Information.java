package cn.SuperSkidder.PVPStage.Modules.gui;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventRender2D;
import cn.SuperSkidder.PVPStage.font.FontLoaders;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import cn.SuperSkidder.PVPStage.utils.RenderUtils;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;

public class Information extends Mod {
    public Information(){
        super("Information", ModCategory.Gui);
    }

    @EventTarget
    public void onRender2D(EventRender2D e){
        RenderUtils.drawRect(0,0,60,50,new Color(0,0,0,100).getRGB());
        FontLoaders.F18.drawStringWithShadow("<FPS>"+ EnumChatFormatting.WHITE+mc.getDebugFPS(),4,4,new Color(255,120,120).getRGB());
        FontLoaders.F18.drawStringWithShadow("<X>"+ EnumChatFormatting.WHITE+(int)mc.thePlayer.posX,4,14,new Color(255,120,120).getRGB());
        FontLoaders.F18.drawStringWithShadow("<Y>"+ EnumChatFormatting.WHITE+(int)mc.thePlayer.posY,4,24,new Color(255,120,120).getRGB());
        FontLoaders.F18.drawStringWithShadow("<Z>"+ EnumChatFormatting.WHITE+(int)mc.thePlayer.posZ,4,34,new Color(255,120,120).getRGB());
    }
}
