package dev.codezey.projectjujutsu.commands;



import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraft.commands.Commands;
import dev.codezey.projectjujutsu.client.ManaBarData;

@Mod.EventBusSubscriber
public class SetMana {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("SetMana").requires(s -> s.hasPermission(2)).then(Commands.argument("set", DoubleArgumentType.doubleArg()).executes(arguments -> {
            int mana = (int) DoubleArgumentType.getDouble(arguments, "set");
            ManaBarData.set(mana);
            return 0;
        })));
    }
}
