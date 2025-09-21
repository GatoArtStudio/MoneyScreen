package art.gatoartstudio.moneyscreen.common.payload;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MoneyUpdatePayload(int money, int players) implements CustomPayload {
    public static final Identifier MONEY_PAYLOAD_ID = Identifier.of("moneyscreen", "money_payload");
    public static final CustomPayload.Id<MoneyUpdatePayload> ID = new CustomPayload.Id<>(MONEY_PAYLOAD_ID);

    public static final PacketCodec<PacketByteBuf, MoneyUpdatePayload> CODEC =
            PacketCodec.of(
                    (p, buf) -> {
                        buf.writeInt(p.money());
                        buf.writeInt(p.players());
                    },
                    buf -> new MoneyUpdatePayload(buf.readInt(), buf.readInt())
        );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
