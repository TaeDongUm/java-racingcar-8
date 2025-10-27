package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AttemptsCountTest {

    @DisplayName("이동 횟수가 1 미만이면 예외가 발생한다 (0, 음수)")
    @Test
    void less_Than_One_isInvalid() {
        assertThatThrownBy(() -> AttemptsCount.of(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AttemptsCount.of(-3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 횟수가 1 이상이면 정상이다 (경계값 포함)")
    @Test
    void greater_Or_Equal_One_isValid() {
        assertThat(AttemptsCount.of(1).value()).isEqualTo(1);
        assertThat(AttemptsCount.of(5).value()).isEqualTo(5);
    }
}
