package racingcar.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.MoveStrategy;
import racingcar.domain.RandomNumberPicker;
import racingcar.domain.ThresholdMoveStrategy;
import racingcar.domain.WinnerSelection;
import racingcar.dto.CarGameState;
import racingcar.service.RacingGame;
import racingcar.view.AttemptsParser;
import racingcar.view.InputView;
import racingcar.view.NameParser;
import racingcar.view.OutputView;

public class RaceGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RaceGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> carNames = NameParser.parseNames(inputView.inputCarName());
        validateNoDuplicates(carNames);
        int attempts = AttemptsParser.parseAttempts(inputView.inputAttempts());

        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        MoveStrategy moveStrategy = new ThresholdMoveStrategy(new RandomNumberPicker());
        RacingGame racingGame = new RacingGame(cars, moveStrategy);

        outputView.printExecutionResultHeader();
        racingGame.raceStart(attempts);

        List<List<CarGameState>> gameHistory = racingGame.snapshots();
        gameHistory.forEach(outputView::printRoundResult);

        List<String> winners = WinnerSelection.selectByMaxPosition(gameHistory);
        outputView.printWinners(winners);
    }

    private void validateNoDuplicates(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>(carNames);
        if (uniqueNames.size() < carNames.size()) {
            throw new IllegalArgumentException("ERROR: 자동차 이름은 중복될 수 없습니다.");
        }
    }
}
