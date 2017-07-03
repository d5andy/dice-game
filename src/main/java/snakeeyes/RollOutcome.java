package snakeeyes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RollOutcome {
    private final int dice1;
    private final int dice2;
    private final double stake;
    private final double winnings;
    @JsonProperty("payout_name")
    private final String payoutName;

    public RollOutcome(int dice1, int dice2, double stake, double winnings, String payoutName) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.stake = stake;
        this.winnings = winnings;
        this.payoutName = payoutName;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public double getStake() {
        return stake;
    }

    public double getWinnings() {
        return winnings;
    }

    public String getPayoutName() {
        return payoutName;
    }
}
