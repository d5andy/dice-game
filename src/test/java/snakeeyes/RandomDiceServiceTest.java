package snakeeyes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RandomDiceService.class)
public class RandomDiceServiceTest {

    @Autowired
    private RandomDiceService client;

    @Autowired
    private MockRestServiceServer server;

    static final Integer[] numbers = {1, 2};

    @Test
    public void parseRandomIntegersCorrectly() {
        serverRespondsWithIntegers();

        List<Integer> randomNumbers = client.randomDice(2).get();

        Assert.assertArrayEquals(randomNumbers.toArray(), numbers);
    }

    @Test
    public void handleServiceError() {
        serverRespondsWithError(400);

        Assert.assertEquals(Optional.empty(), client.randomDice(2));
    }

    @Test
    public void handleServiceFailure() {
        serverRespondsWithError(500);

        Assert.assertEquals(Optional.empty(), client.randomDice(2));
    }

    private void serverRespondsWithError(int status) {
        this.server.expect(requestTo("https://www.random.org/integers/?num=2&min=1&max=6&base=10&format=plain&col=2"))
                .andRespond(withStatus(HttpStatus.valueOf(status)));
    }

    private void serverRespondsWithIntegers() {
        String response = Arrays.stream(numbers).map(n -> n + "\t").reduce(String::concat).get();

        this.server.expect(requestTo("https://www.random.org/integers/?num=2&min=1&max=6&base=10&format=plain&col=2"))
                .andRespond(withSuccess(response, MediaType.TEXT_PLAIN));
    }

}