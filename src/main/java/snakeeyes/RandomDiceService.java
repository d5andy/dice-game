package snakeeyes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

@Service
public class RandomDiceService {

    private static final String TAB = "\t";
    private static final String SPACES = "\\s";
    private static final String EMPTY_STRING = "";
    private final RestTemplate restTemplate;

    public RandomDiceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<List<Integer>> randomDice(int number) {
        ResponseEntity<String> responseEntity = requestIntegers(number);
        if (successful(responseEntity)) {
            List<Integer> integers = convertToIntegers(responseEntity.getBody());
            return of(integers);
        } else {
            return empty();
        }
    }

    private ResponseEntity<String> requestIntegers(int number) {
        String serverUrl = format("https://www.random.org/integers/?num=%1$s&min=1&max=6&base=10&format=plain&col=%1$s", number);
        return restTemplate.getForEntity(serverUrl, String.class);
    }

    private List<Integer> convertToIntegers(String body) {
        return Arrays.stream(body.split(TAB))
                .map(n -> n.replaceAll(SPACES, EMPTY_STRING))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private boolean successful(ResponseEntity<String> responseEntity) {
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

}
