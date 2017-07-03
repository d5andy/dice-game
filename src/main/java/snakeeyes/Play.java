package snakeeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Play {

    @Autowired
    RandomNumberService randomNumberService;

    private SnakeEyesGame snakeEyesGame = new SnakeEyesGame();

    @RequestMapping(value = "/snakeeyes/play", method = RequestMethod.GET)
    public ResponseEntity play(@RequestParam(value="stake", defaultValue = "0.0") Double stake) {
        Stake stakeEnum;
        try {
            stakeEnum = Stake.toStake(stake);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        Optional<List<Integer>> diceResult = randomNumberService.randomNumbers();
        if (diceResult.isPresent()) {
            List<Integer> dice = diceResult.get();
            double winnings = snakeEyesGame.stakeOutcome(dice, stakeEnum);
            return new ResponseEntity<>(new RollOutcome(dice.get(0), dice.get(1), stake, winnings, "snake eyes"), HttpStatus.ACCEPTED);

        } else {
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }

    }

}
