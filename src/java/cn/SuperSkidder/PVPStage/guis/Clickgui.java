package cn.SuperSkidder.PVPStage.guis;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.PVPStage;
import cn.SuperSkidder.PVPStage.font.FontLoaders;
import cn.SuperSkidder.PVPStage.manager.ModuleManager;
import cn.SuperSkidder.PVPStage.utils.RenderUtils;
import cn.SuperSkidder.PVPStage.values.Value;
import cn.SuperSkidder.PVPStage.values.type.Bool;
import cn.SuperSkidder.PVPStage.values.type.Mode;
import cn.SuperSkidder.PVPStage.values.type.Num;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Clickgui extends GuiScreen {

    public int AnimationLogoX;
    public int AnimationBgX;

    public int role = 0;
    public boolean showValue;
    private Mod currentMod;

    @Override
    public void initGui() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        if (showValue && currentMod != null) {
            RenderUtils.drawRect(AnimationBgX, 0, AnimationBgX - 2 + 120, sr.getScaledHeight(), new Color(50, 50, 50).getRGB());
            int valuey = 10;
            for (Value v : currentMod.getValues()) {
                if (v instanceof Bool) {
                    FontLoaders.F16.drawString(v.getName(), AnimationBgX + 5, valuey, -1);
                    if ((Boolean) v.getValue()) {
                        RenderUtils.drawRect(AnimationBgX - 2 + 108, valuey - 3, AnimationBgX - 2 + 115, valuey + 5, new Color(50, 100, 255).getRGB());
                    } else {
                        RenderUtils.drawRect(AnimationBgX - 2 + 108, valuey - 3, AnimationBgX - 2 + 115, valuey + 5, new Color(100, 100, 100).getRGB());
                    }
                }
                if (v instanceof Num) {
                    FontLoaders.F16.drawString(v.getName(), AnimationBgX + 5, valuey, -1);
                    FontLoaders.F14.drawString(v.getValue().toString(), AnimationBgX + 105, valuey - 1, -1);
                    RenderUtils.drawRect(AnimationBgX - 2 + 48, valuey + 5, AnimationBgX - 2 + 115, valuey + 6, new Color(200, 200, 200).getRGB());
                    float render = (float) (67.0F
                            * (((Number) v.getValue()).floatValue() - ((Num) v).getMin().floatValue())
                            / (((Num) v).getMax().floatValue()
                            - ((Num) v).getMin().floatValue()));

                    RenderUtils.drawRect(AnimationBgX - 2 + 48, valuey + 5, AnimationBgX - 2 + 48 + render, valuey + 6, new Color(50, 100, 255).getRGB());

                    if (isHovered(AnimationBgX - 2 + 48, valuey, AnimationBgX - 2 + 115, valuey + 9, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                        float render2 = ((Num) v).getMin().floatValue();
                        double max = ((Num) v).getMax().doubleValue();
                        double inc = 0.1;
                        double valAbs = (double) mouseX - ((double)(AnimationBgX - 2 + 48));
                        double perc = valAbs / 67.0D;
                        perc = Math.min(Math.max(0.0D, perc), 1.0D);
                        double valRel = (max - render2) * perc;
                        double val = render2 + valRel;
                        val = (double) Math.round(val * (1.0D / inc)) / (1.0D / inc);
                        ((Num) v).setValue(Double.valueOf(val));
                        //v.setValue(((mouseX - (AnimationBgX - 2.0 + 48.0)) / 67.0) * cval2);
                    }
                }

                if (v instanceof Mode) {
                    FontLoaders.F16.drawString(v.getName(), AnimationBgX + 5, valuey, -1);
                    RenderUtils.drawRect(AnimationBgX - 2 + 68, valuey - 6, AnimationBgX - 2 + 115, valuey + 8, new Color(150, 150, 150).getRGB());
                    FontLoaders.F16.drawCenteredString(v.getValue().toString(), AnimationBgX - 2 + 68 + 24, valuey - 2, -1);
                }
                valuey += 20;
            }
        }

        AnimationLogoX = AddWithUpperLimit(AnimationLogoX, 30);
        AnimationBgX = AddWithUpperLimit(AnimationBgX, 120);
        RenderUtils.drawRect(0, 0, AnimationBgX, sr.getScaledHeight(), new Color(50, 50, 50).getRGB());
        RenderUtils.drawRect(AnimationBgX - 2, 0, AnimationBgX, sr.getScaledHeight(), new Color(57, 162, 174).getRGB());
        FontLoaders.F22.drawString(PVPStage.CLIENT_NAME, AnimationLogoX, 20, new Color(255, 255, 255).getRGB());
        int m1 = Mouse.getDWheel();

        if (m1 < 0) {
            if ((50 + role) > 35) {
                role -= 2;
            }
        }
        if (m1 > 0) {
            role += 2;
        }

        float typey = 50 + role;
        for (ModCategory m : ModCategory.values()) {
            List<Mod> mods = ModuleManager.getModsByCategory(m);
            RenderUtils.drawRect(AnimationBgX - 115, typey, AnimationBgX - 5, typey + 10, new Color(107, 186, 236).getRGB());
            RenderUtils.drawRect(AnimationBgX - 115, typey + 10, AnimationBgX - 5, typey + 10 + mods.size() * 10, new Color(90, 90, 90).getRGB());
            FontLoaders.F16.drawStringWithShadow(m.name(), AnimationBgX - 110, typey + 3, -1);

            float mody = typey + 14;
            for (Mod dm : mods) {
                if (dm.getState()) {
                    RenderUtils.drawRect(AnimationBgX - 115, mody - 4, AnimationBgX - 113, mody + 6, new Color(50, 150, 255).getRGB());
                }

                if (dm.getValues().size() > 0) {
                    FontLoaders.F23.drawString("...", AnimationBgX - 18, mody - 5, -1);
                }
                FontLoaders.F14.drawString(dm.getName(), AnimationBgX - 110, mody, -1);
                mody += 9;
            }
            typey += mods.size() * 10 + 20;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        ScaledResolution sr = new ScaledResolution(mc);
        if (showValue && currentMod != null) {
            int valuey = 10;
            for (Value v : currentMod.getValues()) {
                if (v instanceof Bool) {
                    if (isHovered(AnimationBgX - 2 + 108, valuey - 3, AnimationBgX - 2 + 115, valuey + 5, mouseX, mouseY) && mouseButton == 0) {
                        v.setValue(!(boolean) v.getValue());
                    }
                }

                if (v instanceof Mode) {
                    if (isHovered(AnimationBgX - 2 + 68, valuey - 6, AnimationBgX - 2 + 115, valuey + 8, mouseX, mouseY) && mouseButton == 0) {
                        Enum current = (Enum) ((Mode) v).getValue();
                        int next = current.ordinal() + 1 >= ((Mode) v).getModes().length ? 0
                                : current.ordinal() + 1;
                        v.setValue(((Mode) v).getModes()[next]);
                    }
                }
                valuey += 20;
            }
        }

        int m1 = Mouse.getDWheel();

        if (m1 < 0) {
            if ((50 + role) > 35) {
                role -= 2;
            }
        }
        if (m1 > 0) {
            role += 2;
        }

        float typey = 50 + role;
        for (ModCategory m : ModCategory.values()) {
            List<Mod> mods = ModuleManager.getModsByCategory(m);
            float mody = typey + 14;
            for (Mod dm : mods) {
                if (mouseX > AnimationBgX - 110 && mouseX < AnimationBgX - 10 && mouseY > mody - 5 && mouseY < mody + 9 && mouseButton == 0) {
                    dm.toogle();
                }

                if (mouseX > AnimationBgX - 110 && mouseX < AnimationBgX - 10 && mouseY > mody - 5 && mouseY < mody + 9 && mouseButton == 1) {
                    if (dm.getValues().size() > 0) {
                        showValue = !showValue;
                        currentMod = dm;
                    }
                }
                mody += 9;
            }
            typey += mods.size() * 10 + 20;
        }
    }

    private int AddWithUpperLimit(int animationLogoX, int i) {
        int result = i;
        if (animationLogoX < i) {
            result = animationLogoX += 4;
        } else if (animationLogoX >= i) {
            result = i;
        }
        return result;
    }

    public boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2) {
            return true;
        }

        return false;
    }

}
