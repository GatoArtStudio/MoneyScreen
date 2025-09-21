package art.gatoartstudio.moneyscreen.client.state;

public class MoneyViewStatus {
    private static volatile MoneyViewStatus instance;

    private long endTime = 0;
    private boolean isVisible = false;
    private int moneyAmount = 0;
    private int playersCount = 0;
    private final int DISPLAY_DURATION_SG = 5;

    private MoneyViewStatus() {}

    public static MoneyViewStatus getInstance() {
        MoneyViewStatus result = instance;
        if (result != null)
            return result;

        synchronized (MoneyViewStatus.class) {
            if (instance == null)
                instance = new MoneyViewStatus();

            return instance;
        }
    }

    public long getEndTime() {
        return endTime;
    }

    /**
     * Set the end time to the current time plus the display duration.
     */
    public void setEndTime() {
        this.endTime = System.currentTimeMillis() + (DISPLAY_DURATION_SG * 1000L);
    }

    /**
     * Check if the money view is visible. If the current time is past the end time, set isVisible to false.
     * @return true if the money view is visible, false otherwise.
     */
    public boolean isVisible() {
        if (isVisible && System.currentTimeMillis() > endTime) {
            isVisible = false;
        }
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }
}
