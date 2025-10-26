package racingcar.service;

import java.util.List;
import racingcar.domain.Car;

public class RacingGameTest {

    static class AlwaysTrueMoveStrategy implements MoveStrategy {

        @Override
        public boolean move() {
            return true;
        }
    }

    int attemptsRound = 3;

    List<Car> cars = List.of(new Car("pobi"), new Car("won"), new Car("jun"));

    RacingGame racingGame = new RacingGame(new AlwaysTrueMoveStrategy());
    List<Car> result = racingGame.raceStart(cars, attemptsRound);

    assertThat(result)
        .extracting(Car::name, Car::position)
        .containsExactlyInAnyOrder(
            tuple("pobi", 3),
            tuple("won", 3),
            tuple("jun", 3)
    );
}
