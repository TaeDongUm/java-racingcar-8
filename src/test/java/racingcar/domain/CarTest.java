package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    @DisplayName("처음 생성된 자동차의 시작 위치는 0이어야 한다.")
    void Car_Start_Position_Is_Zero() {

        Car car = new Car("won");
        assertThat(car.getPosition()).isEqualTo(0);
    }
}
