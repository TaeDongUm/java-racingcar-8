package racingcar.controller;

import racingcar.error.ErrorMessage;

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
        List<String> carNames = readCarNames();
        int attempts = readAttemptsCount();

        List<Car> cars = createCars(carNames);
        RacingGame racingGame = setupGame(cars);

        runAndDisplayGame(racingGame, attempts);
    }

    private void runAndDisplayGame(RacingGame racingGame, int attempts) {
        outputView.printExecutionResultHeader();
        racingGame.raceStart(attempts);

        List<List<CarGameState>> gameHistory = racingGame.snapshots();
        gameHistory.forEach(outputView::printRoundResult);

        List<String> winners = WinnerSelection.selectByMaxPosition(gameHistory);
        outputView.printWinners(winners);
    }

    private RacingGame setupGame(List<Car> cars) {
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(new RandomNumberPicker());
        return new RacingGame(cars, moveStrategy);
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private int readAttemptsCount() {
        return AttemptsParser.parseAttempts(inputView.inputAttempts());
    }

    private List<String> readCarNames() {
        List<String> carNames = NameParser.parseNames(inputView.inputCarName());
        validateNoDuplicates(carNames);
        return carNames;
    }

    private void validateNoDuplicates(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>(carNames);
        if (uniqueNames.size() < carNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CAR_NAMES.toString());
        }
    }
}
