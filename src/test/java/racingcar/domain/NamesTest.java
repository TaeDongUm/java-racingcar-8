package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    @DisplayName("이름 목록에 중복이 있으면 예외가 발생한다.")
    void create_WithDuplicateNames_ThrowsException() {
        // given
        List<Name> duplicatedNames = Arrays.asList(Name.of("pobi"), Name.of("woni"), Name.of("pobi"));

        // when & then
        assertThatThrownBy(() -> new Names(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복될 수 없습니다.");
    }
}
