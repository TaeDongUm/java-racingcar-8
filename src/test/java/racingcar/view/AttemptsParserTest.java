package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AttemptsParserTest {

    @DisplayName("이동 횟수 원문을 정수로 해석한다")
    @Test
    void parseAttempts_toInt() {
        int n = AttemptsParser.parseAttempts("5");
        assertThat(n).isEqualTo(5);
    }

    @DisplayName("숫자가 아니면 예외가 발생한다")
    @Test
    void parseAttempts_nonNumeric_throws() {
        assertThatThrownBy(() -> AttemptsParser.parseAttempts("abc"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("입력이 null이면 예외가 발생한다")
    @Test
    void parseAttempts_null_contractViolation() {
        assertThatThrownBy(() -> AttemptsParser.parseAttempts(null))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
