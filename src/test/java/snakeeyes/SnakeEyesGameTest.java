package snakeeyes;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class SnakeEyesGameTest {

    @Test
    public void twoOnesBigWinThirtyTimesStake() {
        List<Integer> snakeeyes = ImmutableList.of(1, 1);

        double result = new SnakeEyesGame().calculateWinnings(snakeeyes, Stake.one);

        assertEquals(30.0, result, 0.0);
    }

    @Test
    public void doubleWinsSevenTimesStake() {
        List<Integer> snakeeyes = ImmutableList.of(2, 2);

        double result = new SnakeEyesGame().calculateWinnings(snakeeyes, Stake.one);

        assertEquals(7.0, result, 0.0);
    }

    @Test
    public void mixedLosesStake() {
        List<Integer> snakeeyes = ImmutableList.of(2, 3);

        double result = new SnakeEyesGame().calculateWinnings(snakeeyes, Stake.one);

        assertEquals(0.0, result, 0.0);
    }

}