package snakeeyes;

import java.util.Arrays;

public enum Stake {

    one(1.0), two(2.0), ten(10.0);

    double value = 0.0;

    Stake(Double value) {
        this.value = value;
    }

    public static Stake toStake(double value) {
        return Arrays.stream(values())
                .filter(n -> n.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
