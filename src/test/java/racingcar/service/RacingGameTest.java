package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.MoveStrategy;
import racingcar.dto.CarGameState;

public class RacingGameTest {

    static class AlwaysTrueMoveStrategy implements MoveStrategy {

        @Override
        public boolean shouldMove() {
            return true;
        }
    }

    @DisplayName("라운드 결과는 입력된 이름 순서로 누적된다")
    @Test
    void snapshots_Preserve_InputOrder_And_AccumulatePerRound() {

        List<Car> cars = List.of(new Car("pobi"), new Car("won"), new Car("jun"));
        RacingGame game = new RacingGame(cars, new AlwaysTrueMoveStrategy());

        // 3라운드 실행
        game.raceStart(3);

        // 스냅샷은 3개
        List<List<CarGameState>> result = game.snapshots();
        assertThat(result).hasSize(3);

        // 스냅샷 #1: 각 자동차 1칸, 입력 순서 유지
        assertThat(result.get(0))
                .extracting(CarGameState::getName, CarGameState::getPosition)
                .containsExactly(
                        tuple("pobi", 1),
                        tuple("won", 1),
                        tuple("jun", 1)
                );

        // 스냅샷 #2: 각 자동차 2칸, 순서 유지
        assertThat(result.get(1))
                .extracting(CarGameState::getName, CarGameState::getPosition)
                .containsExactly(
                        tuple("pobi", 2),
                        tuple("won", 2),
                        tuple("jun", 2)
                );

        // 스냅샷 #3: 각 자동차 3칸, 순서 유지
        assertThat(result.get(2))
                .extracting(CarGameState::getName, CarGameState::getPosition)
                .containsExactly(
                        tuple("pobi", 3),
                        tuple("won", 3),
                        tuple("jun", 3)
                );
    }

}
