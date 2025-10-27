package racingcar.controller;

import racingcar.service.RacingGameService;
import racingcar.view.InputView;

public class RaceGameController {

    private final InputView inputView;
    private final RacingGameService racingGameService;

    public RaceGameController(InputView inputView, RacingGameService racingGameService) {
        this.inputView = inputView;
        this.racingGameService = racingGameService;
    }

    public void run() {
        String rawCarNames = inputView.inputCarName();
        String rawAttempts = inputView.inputAttempts();

        racingGameService.runGame(rawCarNames, rawAttempts);
    }
}
