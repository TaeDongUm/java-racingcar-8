package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.MoveStrategy;
import racingcar.domain.RandomNumberPicker;
import racingcar.domain.ThresholdMoveStrategy;
import racingcar.domain.WinnerSelection;
import racingcar.dto.CarGameState;
import racingcar.error.ErrorMessage;
import racingcar.view.AttemptsParser;
import racingcar.view.NameParser;
import racingcar.view.OutputView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RacingGameService {

    private final OutputView outputView;

    public RacingGameService(OutputView outputView) {
        this.outputView = outputView;
    }

    public void runGame(String rawCarNames, String rawAttempts) {
        List<String> carNames = NameParser.parseNames(rawCarNames);
        validateNoDuplicates(carNames);
        int attempts = AttemptsParser.parseAttempts(rawAttempts);

        List<Car> cars = createCars(carNames);
        RacingGame racingGame = setupGame(cars);

        runAndDisplayGame(racingGame, attempts);
    }

    private void validateNoDuplicates(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>(carNames);
        if (uniqueNames.size() < carNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CAR_NAMES.toString());
        }
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private RacingGame setupGame(List<Car> cars) {
        MoveStrategy moveStrategy = new ThresholdMoveStrategy(new RandomNumberPicker());
        return new RacingGame(cars, moveStrategy);
    }

    private void runAndDisplayGame(RacingGame racingGame, int attempts) {
        outputView.printExecutionResultHeader();
        racingGame.raceStart(attempts);

        List<List<CarGameState>> gameHistory = racingGame.snapshots();
        gameHistory.forEach(outputView::printRoundResult);

        List<String> winners = WinnerSelection.selectByMaxPosition(gameHistory);
        outputView.printWinners(winners);
    }
}
