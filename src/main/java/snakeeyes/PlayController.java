package snakeeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayController {

    @Autowired
    RandomDiceService randomDiceService;

    private SnakeEyesGame snakeEyesGame = new SnakeEyesGame();

    @RequestMapping(value = "/snakeeyes/play", method = RequestMethod.GET)
    public ResponseEntity play(@RequestParam(value="stake") Double stake) {
        return Stake.toStake(stake)
                .map(this::playForStakes)
                .orElse( badStakeQueryParameterResponse() );
    }

    private ResponseEntity playForStakes(Stake stake) {
        return randomDiceService.randomDice(2)
                .map( dice -> calculateTheWinnings(stake, dice))
                .orElse( randomNumberServiceFailedResponse() );
    }

    private ResponseEntity<RollOutcome> calculateTheWinnings(Stake stake, List<Integer> dice) {
        double winnings = snakeEyesGame.calculateWinnings(dice, stake);
        return ResponseEntity.ok(new RollOutcome(dice.get(0), dice.get(1), stake.value, winnings, "snake eyes"));
    }

    private ResponseEntity badStakeQueryParameterResponse() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RollOutcome> randomNumberServiceFailedResponse() {
        return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
    }

}
