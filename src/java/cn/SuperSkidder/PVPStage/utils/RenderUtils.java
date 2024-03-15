package cn.SuperSkidder.PVPStage.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static void drawRect(float x1, float y1, float x2, float y2, int color) {
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtils.color(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y1);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glVertex2d((double)x1, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }

    public static void color(int color) {
        float f = (float)(color >> 24 & 255) / 255.0f;
        float f1 = (float)(color >> 16 & 255) / 255.0f;
        float f2 = (float)(color >> 8 & 255) / 255.0f;
        float f3 = (float)(color & 255) / 255.0f;
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
    }

    public static void doGlScissor(int x, int y, int width, int height) {
        Minecraft mc = Minecraft.getMinecraft();
        int scaleFactor = 1;
        int k = mc.gameSettings.guiScale;
        if (k == 0) {
            k = 1000;
        }
        while (scaleFactor < k && mc.displayWidth / (scaleFactor + 1) >= 320
                && mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glScissor((int) (x * scaleFactor), (int) (mc.displayHeight - (y + height) * scaleFactor),
                (int) (width * scaleFactor), (int) (height * scaleFactor));
    }
}
