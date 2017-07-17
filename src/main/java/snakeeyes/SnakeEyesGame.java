package snakeeyes;

import java.util.List;

public class SnakeEyesGame {

    public Payout calculateWinnings(List<Integer> dice) {
        int multiplier = 0;
        if (dice != null && dice.size() == 2) {
            if (dice.get(0).equals(dice.get(1))) {
                if (dice.get(0).equals(1)) {
                    multiplier = 30;
                    return new Payout("snake eyes", multiplier);
                } else {
                    multiplier = 7;
                    return new Payout("pair", multiplier);
                }
            }
            return new Payout(null, 0);
        } else {
            throw new IllegalArgumentException("SnakeEyes requires two dice found " + (dice != null ? dice.size() : "none"));
        }

    }
}
