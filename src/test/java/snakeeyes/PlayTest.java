package snakeeyes;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RandomNumberService randomNumberService;

    @Test
    public void verifyOutcome() {

        given(randomNumberService.randomNumbers()).willReturn(Optional.of(ImmutableList.of(1, 1)));

        String rolloutcome = restTemplate.getForObject("/snakeeyes/play?stake=1.0", String.class);

        String expected = "{\"dice1\":1,\"dice2\":1,\"stake\":1.0,\"winnings\":30.0,\"payout_name\":\"snake eyes\"}";

        Assert.assertEquals(expected, rolloutcome);
    }


    @Test
    public void badStake() {

        given(randomNumberService.randomNumbers()).willReturn(Optional.of(ImmutableList.of(1, 1)));

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/snakeeyes/play?stake=1.1", String.class);

        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }

    @Test
    public void missingExternalService() {

        given(randomNumberService.randomNumbers()).willReturn(Optional.empty());

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/snakeeyes/play?stake=1.0", String.class);

        Assert.assertEquals(responseEntity.getStatusCodeValue(), 424);
    }
}