package art.gatoartstudio.moneyscreen.client.screen;

import art.gatoartstudio.moneyscreen.client.state.MoneyViewStatus;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class MoneyScreen extends Screen {
    private static final int COLOR_BACKGROUND = 0xFFFFFFFF;
    private static final int COLOR_TEXT = 0xFF000000;
    private static final int MARGIN_SIZE = 15;

    public MoneyScreen() {
        super(Text.literal(""));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        // Draw background
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), COLOR_BACKGROUND);
        drawBorder(context, MARGIN_SIZE, MARGIN_SIZE, context.getScaledWindowWidth() - MARGIN_SIZE * 2, context.getScaledWindowHeight() - MARGIN_SIZE * 2, 2, COLOR_TEXT);

        // Get data
        int moneyAmount = MoneyViewStatus.getInstance().getMoneyAmount();
        int playersCount = MoneyViewStatus.getInstance().getPlayersCount();

        // Prepare text
        String playersText = playersCount + " JUGADORES RESTANTES";
        String moneyText = moneyAmount + " PESOS COLOMBIANOS";
        String subText = "Acumulados hasta ahora";

        float scalePlayers = 1.5f;
        float scaleMoney = 2.0f;
        float scaleSub = 1.0f;

        int centerX = context.getScaledWindowWidth() / 2;
        int centerY = context.getScaledWindowHeight() / 2;

        drawCenteredScaledText(context, textRenderer, playersText, centerX, centerY - 20, scalePlayers, COLOR_TEXT, true);
        drawCenteredScaledText(context, textRenderer, moneyText, centerX, centerY + 5, scaleMoney, COLOR_TEXT, true);
        drawCenteredScaledText(context, textRenderer, subText, centerX, centerY + 25, scaleSub, COLOR_TEXT, true);
    }

    @Override
    public void tick() {
        if (!MoneyViewStatus.getInstance().isVisible()) {
            this.close();
        }
    }

    private static void drawCenteredScaledText(DrawContext context, TextRenderer
             renderer, String text, int centerX, int centerY, float scale, int color, boolean isBold) {
        Text textRender = Text.literal(text).styled(s -> s.withBold(isBold));

        int textWidth = renderer.getWidth(textRender);
        int textHeight = renderer.fontHeight;

        context.getMatrices().push();

        context.getMatrices().translate(centerX, centerY, 0);
        context.getMatrices().scale(scale, scale, 1.0f);
        context.getMatrices().translate(-textWidth / 2.0f, -textHeight / 2.0f, 0);

        context.drawText(renderer, textRender, 0, 0, color, false);

        context.getMatrices().pop();
    }

    private static void drawBorder(DrawContext context, int x, int y, int width, int height, int thickness, int color) {
        // Top
        context.fill(x, y, x + width, y + thickness, color);
        // Bottom
        context.fill(x, y + height - thickness, x + width, y + height, color);
        // Left
        context.fill(x, y, x + thickness, y + height, color);
        // Right
        context.fill(x + width - thickness, y, x + width, y + height, color);
    }
}
