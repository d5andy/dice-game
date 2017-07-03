package snakeeyes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RandomNumberService.class)
public class RandomNumberServiceTest {

    @Autowired
    private RandomNumberService client;

    @Autowired
    private MockRestServiceServer server;

    static final Integer[] numbers = {1, 2};

    @Before
    public void setUp() {
        String response = Arrays.stream(numbers).map(n -> n + "\t").reduce(String::concat).get();

        this.server.expect(requestTo("https://www.random.org/integers/?num=2&min=1&max=6&base=10&format=plain&col=2"))
                    .andRespond(withSuccess(response, MediaType.TEXT_PLAIN));
    }

    @Test
    public void parseRandomServerResponseCorrectly() {

        List<Integer> randomNumbers = client.randomNumbers().get();

        Assert.assertArrayEquals(randomNumbers.toArray(), numbers);
    }

}