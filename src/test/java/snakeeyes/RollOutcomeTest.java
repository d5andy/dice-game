package snakeeyes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RollOutcomeTest {

    @Autowired
    private JacksonTester<RollOutcome> json;

    @Before
    public void before() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void verifyJsonMatchesBrief() throws IOException {
        RollOutcome outcome = new RollOutcome(4, 6, 1.3, 30, "snake eyes");
        JsonContent<RollOutcome> jsonContent = json.write(outcome);

        String expected = "{\"dice1\":4,\"dice2\":6,\"stake\":1.3,\"winnings\":30.0,\"payout_name\":\"snake eyes\"}";
        assertEquals(expected, jsonContent.getJson());
    }
}