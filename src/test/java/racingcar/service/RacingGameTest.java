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

    @DisplayName("각 라운드에서 모든 자동차에 이동 판단이 정확히 1회 적용된다 (라운드 간 위치 증가는 항상 +1)")
    @Test
    void everyRound_Applies_Exactly_OncePerCar() {

        List<Car> cars = List.of(new Car("pobi"), new Car("won"), new Car("jun"));
        RacingGame game = new RacingGame(cars, new AlwaysTrueMoveStrategy());
        int attempts = 3;

        game.raceStart(attempts);

        List<List<CarGameState>> snaps = game.snapshots();
        assertThat(snaps).hasSize(attempts); // 라운드 수 = 스냅샷 수

        // 라운드 간 델타가 각 자동차마다 항상 +1인지 확인
        for (int r = 1; r < snaps.size(); r++) {
            List<CarGameState> prev = snaps.get(r - 1);
            List<CarGameState> curr = snaps.get(r);

            // 입력 순서 유지 가정 하에 같은 인덱스끼리 비교
            assertThat(curr).hasSameSizeAs(prev);
            for (int i = 0; i < curr.size(); i++) {
                CarGameState p = prev.get(i);
                CarGameState c = curr.get(i);

                // 동일한 자동차(이름) 이어야 한다
                assertThat(c.getName()).isEqualTo(p.getName());

                // Always-true 전략이므로 한 라운드에서 정확히 1칸만 증가해야 함
                assertThat(c.getPosition() - p.getPosition()).isEqualTo(1);
            }
        }
    }

}
