package dev.codezey.projectjujutsu.client.screens;

import dev.codezey.projectjujutsu.client.HotBarData;
import dev.codezey.projectjujutsu.client.ManaBarData;
import dev.codezey.projectjujutsu.moves.ID;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;


@Mod.EventBusSubscriber({Dist.CLIENT})
public class HotBarOverLay {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        int posX = w / 2;
        int posY = h / 2;
        Level world = null;
        double x = 0;
        double y = 0;
        double z = 0;
        Player entity = Minecraft.getInstance().player;
        if (entity != null) {
            world = entity.level();
            x = entity.getX();
            y = entity.getY();
            z = entity.getZ();
        }
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {        
                        // render the hotbar itself 
                        int i;
                        for (i=0; i < HotBarData.ReturnSize(); i++) {
                            int xp = i * 21 + 1;

                            // Render the hotbar Backround
                            event.getGuiGraphics().blit(new ResourceLocation("jujutsu:textures/screens/hotbar.png"), 50 + xp, h - 22, 0, 0, 21, 21, 21, 21);
                            // Render the items in the hotbar first (So its ontop)
                            int id = HotBarData.ReturnMove(i);
                            String imgName = ID.IdToMove(id);
                            if (imgName != "null") {
                                event.getGuiGraphics().blit(new ResourceLocation("jujutsu:textures/screens/icons/" + imgName + ".png"), 53 + xp, h - 19, 1, 1, 15, 15, 15, 15);
                            }
                        }
                        
                        // Renders the active slot overlay. 
                        if (HotBarData.ReturnIsActive() == true) {
                            int slot = HotBarData.ReturnSlot();
                            int xpos = slot * 21 + 1 + 50;
                            event.getGuiGraphics().blit(new ResourceLocation("jujutsu:textures/screens/active.png"), xpos, h - 22, 0, 0, 21, 21, 21, 21);
                        }


                        

        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);

    }

}
