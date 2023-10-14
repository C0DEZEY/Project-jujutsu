package dev.codezey.projectjujutsu.networking;



import java.util.function.Supplier;

import dev.codezey.projectjujutsu.client.ManaBarData;
import dev.codezey.projectjujutsu.moves.BoogieWoogie;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
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

                Entity target = raycast.rayTraceEyes(player,50);
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                teleport.TeleportTo(target.getX(), target.getY() , target.getZ(), player);
                teleport.TeleportTo(x,y,z,target);

            }
        }

        @SubscribeEvent
        public static void registerMessage(FMLCommonSetupEvent event) {
            Main.addNetworkMessage(SwapMessage.class, SwapMessage::buffer, SwapMessage::new, SwapMessage::handler);
        }
    }


