package dev.codezey.projectjujutsu.networking;



import java.util.function.Supplier;


import dev.codezey.projectjujutsu.util.util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.network.FriendlyByteBuf;
import dev.codezey.projectjujutsu.util.raycast;

import dev.codezey.projectjujutsu.server.teleport;
import dev.codezey.projectjujutsu.Main;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public class SwapMessage {
        public static boolean twoentitys = false;
        public static Entity firstentity = null;
        public static void FirstEntity(Entity entity) {firstentity = entity;}
        public static void setTwoentitys(boolean t) {twoentitys = t;}
        public static Entity target = null;
        int type, pressedms;

        public SwapMessage(int type, int pressedms) {
            this.type = type;
            this.pressedms = pressedms;
        }

        public SwapMessage(FriendlyByteBuf buffer) {
            this.type = buffer.readInt();
            this.pressedms = buffer.readInt();
        }

        public static void buffer(SwapMessage message, FriendlyByteBuf buffer) {
            buffer.writeInt(message.type);
            buffer.writeInt(message.pressedms);
        }

        public static void handler(SwapMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                pressAction(context.getSender(), 0);
            });
            context.setPacketHandled(true);
        }

        public static void pressAction(Player player, int type) {
            Level world = player.level();



            // security measure to prevent arbitrary chunk generation
            if (!world.hasChunkAt(player.blockPosition()))

                return;
            if (type == 0) {
                if(!twoentitys) {
                    Entity target = raycast.rayTraceEyes(player, 50);
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();
                    // Teleport the player to the target
                    teleport.TeleportTo(target.getX(), target.getY(), target.getZ(), player);
                    // Teleport the Target to the Player pos BEFORE the teleport
                    teleport.TeleportTo(x, y, z, target);
                }
            }

            if (type == 1) {
            if(twoentitys) {
                Entity target = raycast.rayTraceEyes(player,50);

                double x2 = target.getX();
                double y2 = target.getY();
                double z2 = target.getZ();
                
                teleport.TeleportTo(firstentity.getX(),firstentity.getY(),firstentity.getZ(),target);
                teleport.TeleportTo(x2,y2,z2,firstentity);

            }
            }
        }

        @SubscribeEvent
        public static void registerMessage(FMLCommonSetupEvent event) {
            Main.addNetworkMessage(SwapMessage.class, SwapMessage::buffer, SwapMessage::new, SwapMessage::handler);
        }
    }


