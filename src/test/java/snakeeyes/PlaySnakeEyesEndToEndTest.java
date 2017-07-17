package snakeeyes;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaySnakeEyesEndToEndTest {

    private static final String EXPECTED_OUTCOME_SNAKE_EYES = "{\"dice1\":1,\"dice2\":1,\"stake\":1.0,\"winnings\":30.0,\"payout_name\":\"snake eyes\"}";;
    private static final String EXPECTED_OUTCOME_PAIR = "{\"dice1\":2,\"dice2\":2,\"stake\":1.0,\"winnings\":7.0,\"payout_name\":\"pair\"}";;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RandomDiceService randomDiceService;

    @Test
    public void verifyOutcomeSnakeEyes() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.of(ImmutableList.of(1, 1)));

        ResponseEntity<String> responseEntity = playWithStake("1.0");

        successful(responseEntity, EXPECTED_OUTCOME_SNAKE_EYES);
    }

    @Test
    public void verifyOutcomePair() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.of(ImmutableList.of(2, 2)));

        ResponseEntity<String> responseEntity = playWithStake("1.0");

        successful(responseEntity, EXPECTED_OUTCOME_PAIR);
    }


    @Test
    public void stakeIsInteger() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.of(ImmutableList.of(1, 1)));

        ResponseEntity<String> responseEntity = playWithStake("1");

        successful(responseEntity, EXPECTED_OUTCOME_SNAKE_EYES);
    }

    @Test
    public void badStake() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.of(ImmutableList.of(1, 1)));

        ResponseEntity<String> responseEntity = playWithStake("1.1");

        assertEquals(400, responseEntity.getStatusCodeValue());
    }


    @Test
    public void noStake() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.of(ImmutableList.of(1, 1)));

        ResponseEntity<String> responseEntity = playWithNoStake();

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void missingExternalService() {

        given(randomDiceService.randomDice(2)).willReturn(Optional.empty());

        ResponseEntity<String> responseEntity = playWithStake("1.0");

        assertEquals(424, responseEntity.getStatusCodeValue());
    }

    private void successful(ResponseEntity<String> responseEntity, String outcome) {
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(outcome, responseEntity.getBody());
    }

    private ResponseEntity<String> playWithNoStake() {
        return restTemplate.getForEntity("/snakeeyes/play", String.class);
    }

    private ResponseEntity<String> playWithStake(String stake) {
        return restTemplate.getForEntity("/snakeeyes/play?stake=" + stake, String.class);
    }
}