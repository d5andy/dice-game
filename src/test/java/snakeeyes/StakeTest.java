package snakeeyes;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StakeTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void expectStakeToBeAccepted() {
        Assert.assertEquals(Stake.toStake(1.0), Stake.one);
        Assert.assertEquals(Stake.toStake(2.0), Stake.two);
        Assert.assertEquals(Stake.toStake(10.0), Stake.ten);
    }

    @Test()
    public void expectStakeToBeRejected() {
        thrown.expect(IllegalArgumentException.class);
        Stake.toStake(1.1);
    }

}