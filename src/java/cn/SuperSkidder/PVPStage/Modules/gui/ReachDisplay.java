package cn.SuperSkidder.PVPStage.Modules.gui;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventRender2D;
import cn.SuperSkidder.PVPStage.font.FontLoaders;
import cn.SuperSkidder.PVPStage.manager.event.EventAttack;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import cn.SuperSkidder.PVPStage.utils.RenderUtils;
import cn.SuperSkidder.PVPStage.values.type.Num;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.text.DecimalFormat;

public class ReachDisplay extends Mod {
    public Num<Float> x = new Num<Float>("X", 1F, 0F, 100F);
    public Num<Float> y = new Num<Float>("Y", 1F, 0F, 100F);

    String distance = "0.00";
    public ReachDisplay() {
        super("ReachDisplay", ModCategory.Gui);
        this.addValues(x, y);
    }
    @EventTarget
    public void onRender2D(EventRender2D e){
        ScaledResolution sr = new ScaledResolution(mc);
        float realx = ((Number) x.getValue()).floatValue() / 100 * sr.getScaledWidth();
        float realy = ((Number) y.getValue()).floatValue() / 100 * sr.getScaledHeight();

        RenderUtils.drawRect(realx,realy,realx+25,realy+15,new Color(0,0,0,150).getRGB());
        FontLoaders.F22.drawStringWithShadow(distance,realx+1,realy+2,-1);
    }

    @EventTarget
    public void onAttack(EventAttack e){
        if(e.getTarget() != null){
            DecimalFormat df = new DecimalFormat("0.00");
            distance =df.format(mc.thePlayer.getDistanceToEntity(e.getTarget()));
        }
    }
}
