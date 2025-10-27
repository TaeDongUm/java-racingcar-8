package racingcar;

import racingcar.controller.RaceGameController;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RacingGameService racingGameService = new RacingGameService(outputView);
        RaceGameController raceGameController = new RaceGameController(inputView, racingGameService);
        raceGameController.run();
    }
}
