package snakeeyes;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class SnakeEyesGameTest {

    @Test
    public void twoOnesBigWin() {
        List<Integer> snakeeyes = ImmutableList.of(1, 1);

        double result = new SnakeEyesGame().stakeOutcome(snakeeyes, Stake.one);

        Assert.assertEquals(30.0, result, 0.0);
    }

    @Test
    public void doubleWins() {
        List<Integer> snakeeyes = ImmutableList.of(2, 2);

        double result = new SnakeEyesGame().stakeOutcome(snakeeyes, Stake.one);

        Assert.assertEquals(7.0, result, 0.0);
    }

    @Test
    public void mixedLoses() {
        List<Integer> snakeeyes = ImmutableList.of(2, 3);

        double result = new SnakeEyesGame().stakeOutcome(snakeeyes, Stake.one);

        Assert.assertEquals(0.0, result, 0.0);
    }


}