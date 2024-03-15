package cn.SuperSkidder.PVPStage.Modules.combat;

import cn.SuperSkidder.PVPStage.Modules.Mod;
import cn.SuperSkidder.PVPStage.Modules.ModCategory;
import cn.SuperSkidder.PVPStage.events.EventRender3D;
import cn.SuperSkidder.PVPStage.manager.event.EventTarget;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

public class HackerChecker extends Mod {
    public HackerChecker() {
        super("HackerChecker", ModCategory.Combat);
    }

    @EventTarget
    public void onRender3D(EventRender3D e){
        for (Entity elb:mc.theWorld.loadedEntityList){
            if(elb instanceof EntityPlayer && elb !=mc.thePlayer){
                double x = elb.lastTickPosX
                        + (elb.posX - elb.lastTickPosX) * e.getPartialTicks()
                        - mc.getRenderManager().viewerPosX;
                double y = elb.lastTickPosY
                        + (elb.posY - elb.lastTickPosY) * e.getPartialTicks()
                        - mc.getRenderManager().viewerPosY;
                double z = elb.lastTickPosZ
                        + (elb.posZ - elb.lastTickPosZ) * e.getPartialTicks()
                        - mc.getRenderManager().viewerPosZ;
                esp(elb, x, y, z);
            }
        }
    }

    public void esp(Entity player, final double x, final double y, final double z) {
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GlStateManager.translate(x, y, z);
        if (player.getDistanceToEntity(mc.thePlayer)<=3) {
            GlStateManager.color(0.25f, 2.0f, 0.0f, 1.0f);
        } else {
            GlStateManager.color(1.35f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.rotate(180, 90.0f, 0, 2.0f);
        GlStateManager.rotate(180, 0.0f, 90, 90.0f);
        Cylinder c = new Cylinder();
        c.setDrawStyle(100011);
        c.draw(3, 3, 0f, 120, 2);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }
}
