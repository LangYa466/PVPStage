package cn.SuperSkidder.PVPStage.Modules.gui;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventRender2D;
import cn.SuperSkidder.PVPStage.font.FontLoaders;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import cn.SuperSkidder.PVPStage.utils.RenderUtils;
import cn.SuperSkidder.PVPStage.values.type.Num;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class KeyStrokes extends Mod {
    public Num<Float> x = new Num<Float>("X", 1F, 0F, 100F);
    public Num<Float> y = new Num<Float>("Y", 1F, 0F, 100F);

    public KeyStrokes() {
        super("KeyStrokes", ModCategory.Gui);
        this.addValues(x, y);
    }

    @EventTarget
    public void onRender2D(EventRender2D e) {
        ScaledResolution sr = new ScaledResolution(mc);
        float realx = ((Number) x.getValue()).floatValue() / 100 * sr.getScaledWidth();
        float realy = ((Number) y.getValue()).floatValue() / 100 * sr.getScaledHeight();
        //W
        RenderUtils.drawRect(realx, realy, realx + 20, realy + 20, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindForward.isKeyDown()) {
            RenderUtils.drawRect(realx, realy, realx + 20, realy + 20, new Color(255, 255, 255, 50).getRGB());
        }
        //A
        RenderUtils.drawRect(realx - 21, realy + 22, realx - 1, realy + 42, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindLeft.isKeyDown()) {
            RenderUtils.drawRect(realx - 21, realy + 22, realx - 1, realy + 42, new Color(255, 255, 255, 50).getRGB());
        }
        //S
        RenderUtils.drawRect(realx, realy + 22, realx + 20, realy + 42, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindBack.isKeyDown()) {
            RenderUtils.drawRect(realx, realy + 22, realx + 20, realy + 42, new Color(255, 255, 255, 50).getRGB());
        }
        //D
        RenderUtils.drawRect(realx + 21, realy + 22, realx + 41, realy + 42, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindRight.isKeyDown()) {
            RenderUtils.drawRect(realx + 21, realy + 22, realx + 41, realy + 42, new Color(255, 255, 255, 50).getRGB());
        }

        FontLoaders.F22.drawStringWithShadow("W", realx + 5, realy + 5, -1);
        FontLoaders.F22.drawStringWithShadow("A", realx - 16, realy + 27, -1);
        FontLoaders.F22.drawStringWithShadow("S", realx + 5, realy + 27, -1);
        FontLoaders.F22.drawStringWithShadow("D", realx + 26, realy + 27, -1);

        RenderUtils.drawRect(realx - 21, realy + 43, realx + 9, realy + 63, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindAttack.isKeyDown()) {
            RenderUtils.drawRect(realx - 21, realy + 43, realx + 9, realy + 63, new Color(255, 255, 255, 50).getRGB());
        }
        RenderUtils.drawRect(realx + 10, realy + 43, realx + 41, realy + 63, new Color(0, 0, 0, 50).getRGB());
        if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
            RenderUtils.drawRect(realx + 10, realy + 43, realx + 41, realy + 63, new Color(255, 255, 255, 50).getRGB());
        }

        FontLoaders.F18.drawStringWithShadow("LMB", realx - 16, realy + 48, -1);
        FontLoaders.F18.drawStringWithShadow("RMB", realx + 14, realy + 48, -1);
    }
}
