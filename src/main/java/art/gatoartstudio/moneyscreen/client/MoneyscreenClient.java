package art.gatoartstudio.moneyscreen.client;

import art.gatoartstudio.moneyscreen.client.networking.ClientPacketHandlerMoney;
import net.fabricmc.api.ClientModInitializer;

public class MoneyscreenClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerNetworking();
    }

    private void registerNetworking() {
         ClientPacketHandlerMoney.register();
    }
}
