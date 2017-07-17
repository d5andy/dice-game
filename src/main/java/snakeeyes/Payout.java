package snakeeyes;

public class Payout {
    private final String name;
    private final Integer multiplier;

    public Payout(String name, Integer multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }


    public String getName() {
        return name;
    }

    public Integer getMultiplier() {
        return multiplier;
    }
}
