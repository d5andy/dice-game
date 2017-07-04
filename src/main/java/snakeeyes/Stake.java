package snakeeyes;

import java.util.Arrays;
import java.util.Optional;

public enum Stake {

    one(1.0), two(2.0), ten(10.0);

    double value = 0.0;

    Stake(Double value) {
        this.value = value;
    }

    public static Optional<Stake> toStake(double value) {
        return Arrays.stream(values())
                .filter(n -> n.value == value)
                .findFirst();
    }
}
