package art.gatoartstudio.moneyscreen.server.command;

import art.gatoartstudio.moneyscreen.Moneyscreen;
import art.gatoartstudio.moneyscreen.common.payload.MoneyUpdatePayload;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ShowViewCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("moneyscreen")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .then(CommandManager.argument("money", IntegerArgumentType.integer())
                        .then(CommandManager.argument("players", IntegerArgumentType.integer())
                                .executes(ShowViewCommand::execute)
                        )
                        .executes(ShowViewCommand::executeHelp)
                )
                .executes(ShowViewCommand::executeHelp)
        );
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        int money = IntegerArgumentType.getInteger(context, "money");
        int players = IntegerArgumentType.getInteger(context, "players");

        // Here you would implement the logic to show the money screen to players
        context.getSource().sendFeedback(() -> Text.literal("MoneyScreen command executed, datos recibidos: money=" + money + ", players=" + players), false);

        MoneyUpdatePayload moneyUpdatePayload = new MoneyUpdatePayload(money, players);
        context.getSource().getServer().getPlayerManager().getPlayerList()
                .forEach(player -> {
                    ServerPlayNetworking.send(player, moneyUpdatePayload);
                    Moneyscreen.LOGGER.info("Sent MoneyUpdatePayload to player {}: money={}, players={}", player.getName().getString(), money, players);
                });

        return Command.SINGLE_SUCCESS;
    }

    public static int executeHelp(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(() -> Text.literal("Please user: /moneyscreen money players, example: /moneyscreen 120 520"), false);
        return Command.SINGLE_SUCCESS;
    }
}
