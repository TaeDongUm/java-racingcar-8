package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThresholdMoveStrategyTest {

    private static final class StubPicker implements NumberPicker {

        private final int v;
        private StubPicker(int v) {
            this.v = v;
        }

        @Override
        public int pick() {
            return v;
        }
    }

    @DisplayName("전진 판단에서 값이 4 이상이면 전진(true)하고, 자동차 위치는 1 증가한다")
    @Test
    void moveWhenValueIsAtLeastFour() {

        MoveStrategy strategy = new ThresholdMoveStrategy(new StubPicker(4)); // 임계값=4
        Car car = new Car("pobi");

        car.moveIf(strategy.shouldMove());

        assertThat(strategy.shouldMove()).isTrue(); // 규칙 자체 확인(선택)
        assertThat(car.getPosition()).isEqualTo(1);    // 위치 +1
    }

    @DisplayName("전진 판단에서 값이 3 이하면 정지(false)하고, 자동차 위치는 증가하지 않는다")
    @Test
    void stayWhenValueIsThreeOrLess() {

        MoveStrategy strategy = new ThresholdMoveStrategy(new StubPicker(3)); // 3
        Car car = new Car("pobi");

        car.moveIf(strategy.shouldMove());

        assertThat(strategy.shouldMove()).isFalse();
        assertThat(car.getPosition()).isEqualTo(0);    // 위치 변화 없음
    }
}
