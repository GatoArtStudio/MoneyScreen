package art.gatoartstudio.moneyscreen;

import art.gatoartstudio.moneyscreen.common.payload.MoneyUpdatePayload;
import art.gatoartstudio.moneyscreen.server.command.ShowViewCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Moneyscreen implements ModInitializer {

    public static final String MOD_ID = "moneyscreen";
    public static final Identifier GAME_DATA_ID = Identifier.of(MOD_ID, "game_data");
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        registerCommands();
        registerNetworking();
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            ShowViewCommand.register(commandDispatcher);
        }));
    }

    private void registerNetworking() {
        PayloadTypeRegistry.playS2C().register(MoneyUpdatePayload.ID, MoneyUpdatePayload.CODEC);
    }
}
