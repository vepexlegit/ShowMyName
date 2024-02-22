package de.vepexlegit.showmynamedebug;

import de.vepexlegit.showmynamedebug.commands.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.client.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.lwjgl.opengl.*;

@Mod(modid = "showmynamedebug", version = "1.0")
public class ShowMyNameDebug {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ShowMyNameCommand());
    }

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        if (ShowMyName.INSTANCE.isEnabled()) {
            EntityPlayer player = (EntityPlayer)event.entity;
            if (player == mc.thePlayer) {
                String str = player.getName();
                FontRenderer fontrenderer = mc.fontRendererObj;
                float f = 1.6F;
                float f1 = 0.016666668F * f;
                GlStateManager.pushMatrix();
                if (player.isSneaking()) {
                    GlStateManager.translate((float)event.x + 0.0F, (float)event.y + player.height + 0.3F, (float)event.z);
                } else {
                    GlStateManager.translate((float)event.x + 0.0F, (float)event.y + player.height + 0.5F, (float)event.z);
                }
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(mc.getRenderManager().playerViewX, mc.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F, 0.0F, 0.0F);
                GlStateManager.scale(-f1, -f1, f1);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(false);
                GlStateManager.disableDepth();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                Tessellator tessellator = Tessellator.getInstance();
                WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                int i = 0;
                int j = fontrenderer.getStringWidth(str) / 2;
                GlStateManager.disableTexture2D();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                worldrenderer.pos((double)(-j - 1), (double)(-1 + i), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                worldrenderer.pos((double)(-j - 1), (double)(8 + i), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                worldrenderer.pos((double)(j + 1), (double)(8 + i), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                worldrenderer.pos((double)(j + 1), (double)(-1 + i), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
                fontrenderer.drawString(str, -j, i, 553648127);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                fontrenderer.drawString(str, -j, i, -1);
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
            }
        }
    }
}
