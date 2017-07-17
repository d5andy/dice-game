package snakeeyes;

import com.google.common.collect.ImmutableList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class SnakeEyesGameTest {

    @Rule
    public ExpectedException noexception = ExpectedException.none();

    @Test
    public void twoOnesBigWinThirtyTimesStake() {
        List<Integer> snakeeyes = ImmutableList.of(1, 1);

        Payout payout = new SnakeEyesGame().calculateWinnings(snakeeyes);

        assertEquals(30, payout.getMultiplier().intValue());
        assertEquals("snake eyes", payout.getName());
    }

    @Test
    public void doubleWinsSevenTimesStake() {
        List<Integer> snakeeyes = ImmutableList.of(2, 2);

        Payout payout = new SnakeEyesGame().calculateWinnings(snakeeyes);

        assertEquals(7, payout.getMultiplier().intValue());
        assertEquals("pair", payout.getName());
    }

    @Test
    public void mixedLosesStake() {
        List<Integer> snakeeyes = ImmutableList.of(2, 3);

        Payout payout = new SnakeEyesGame().calculateWinnings(snakeeyes);

        assertEquals(0, payout.getMultiplier().intValue());
        assertEquals(null, payout.getName());
    }

    @Test
    public void oneOnlyDice() {
        noexception.expect(IllegalArgumentException.class);

        List<Integer> snakeeyes = ImmutableList.of(2);
        new SnakeEyesGame().calculateWinnings(snakeeyes);
    }

}