package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.MoveStrategy;
import racingcar.dto.CarGameState;

public class RacingGame {

    private final List<Car> cars;
    private final MoveStrategy strategy;
    private final List<List<CarGameState>> history = new ArrayList<>();

    public RacingGame(List<Car> cars, MoveStrategy strategy) {
        this.cars = cars;
        this.strategy = strategy;
    }

    public void raceStart(int attempts) {
        for (int i = 0; i < attempts; i++) {
            playOneRound();
            snapshot();
        }
    }

    private void playOneRound() {
        for (Car car : cars) {
            car.moveIf(strategy.shouldMove());
        }
    }

    private void snapshot() {
        List<CarGameState> round = new ArrayList<>(cars.size());
        for (Car c : cars) {
            round.add(new CarGameState(c.getName(), c.getPosition()));
        }
        history.add(round);
    }

    public List<List<CarGameState>> snapshots() {
        return history; // 필요 시 불변 래핑 고려
    }

}
