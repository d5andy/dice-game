package snakeeyes;

import java.util.List;

public class SnakeEyesGame {

    public double stakeOutcome(List<Integer> dice, Stake stake) {
        int multiplier = 0;
        if (dice.get(0).equals(dice.get(1))) {
            if (dice.get(0).equals(1)) {
                multiplier = 30;
            } else {
                multiplier = 7;
            }
        }
        return stake.value * multiplier;
    }
}
