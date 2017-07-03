package snakeeyes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class RandomNumberService {

    private final RestTemplate restTemplate;

    public RandomNumberService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<List<Integer>> randomNumbers() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://www.random.org/integers/?num=2&min=1&max=6&base=10&format=plain&col=2", String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String[] numbers = responseEntity.getBody().split("\t");
            return Optional.of(convertToIntegers(numbers));
        } else {
            return Optional.empty();
        }

    }

    List<Integer> convertToIntegers(String[] numbers) {
        return Arrays.stream(numbers)
                .map(n -> n.replaceAll("\\s", ""))
                .map(Integer::parseInt)
                .collect(toList());
    }


}
