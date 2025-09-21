package art.gatoartstudio.moneyscreen.client.networking;

import art.gatoartstudio.moneyscreen.Moneyscreen;
import art.gatoartstudio.moneyscreen.client.screen.MoneyScreen;
import art.gatoartstudio.moneyscreen.client.state.MoneyViewStatus;
import art.gatoartstudio.moneyscreen.common.payload.MoneyUpdatePayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ClientPacketHandlerMoney {

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(MoneyUpdatePayload.ID, (payload, context) -> {
            context.client().execute(
                    () -> {
                        MoneyViewStatus.getInstance().setPlayersCount(payload.players());
                        MoneyViewStatus.getInstance().setMoneyAmount(payload.money());
                        MoneyViewStatus.getInstance().setEndTime();
                        MoneyViewStatus.getInstance().setVisible(true);

                        MinecraftClient.getInstance().setScreen(new MoneyScreen());
                        Moneyscreen.LOGGER.info("Received MoneyUpdatePayload: money={}, players={}", payload.money(), payload.players());
                    }
            );
        });
    }
}
