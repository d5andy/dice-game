package snakeeyes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static snakeeyes.Stake.toStake;

public class StakeTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void expectStakeToBeAccepted() {
        assertEquals(toStake(1.0).get(), Stake.one);
        assertEquals(toStake(2.0).get(), Stake.two);
        assertEquals(toStake(10.0).get(), Stake.ten);
    }

    @Test()
    public void expectStakeToBeRejected() {
        assertEquals(Optional.empty(), toStake(1.1));
    }

}