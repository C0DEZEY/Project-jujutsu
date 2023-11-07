package dev.codezey.projectjujutsu.networking.packets;

import dev.codezey.projectjujutsu.Main;
import dev.codezey.projectjujutsu.client.HotBarData;
import dev.codezey.projectjujutsu.moves.BoogieWoogie;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyBindMessages {
    int type, pressedms;

    public KeyBindMessages(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public static void buffer(KeyBindMessages message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressedms);
    }

    public static void handler(KeyBindMessages message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.pressedms);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        if (!world.hasChunkAt(entity.blockPosition()))
            return;

        if (type == 0) {
            // Type 0 (On Pressed)

            BoogieWoogie.run();
        }
        if (type == 1) {
                // Type 1 (On Let go)

            BoogieWoogie.set();
        }
        if (type == 2) {
            HotBarData.setActive(true);
        }
        if (type == 3) {
            HotBarData.setActive(false);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        Main.addNetworkMessage(KeyBindMessages.class, KeyBindMessages::buffer, KeyBindMessages::new, KeyBindMessages::handler);
    }
}
