package snakeeyes;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@Ignore
@RunWith(SpringRunner.class)
@JsonTest(excludeFilters = { @Filter(RandomNumberService.class), @Filter(Play.class), @Filter(SnakeEyesApplication.class)})
public class RollOutcomeTest {

    @Autowired
    private JacksonTester<RollOutcome> json;

    @Test
    public void verifyJsonMatchesBrief() throws IOException {
        RollOutcome outcome = new RollOutcome(4, 6, 1.3, 30, "snake eyes");
        JsonContent<RollOutcome> jsonContent = json.write(outcome);

        String expected = "{\"dice1\":4,\"dice2\":6,\"stake\":1.3,\"winnings\":30.0,\"payout_name\":\"snake eyes\"}";
        Assert.assertEquals(expected, jsonContent.getJson());
    }
}