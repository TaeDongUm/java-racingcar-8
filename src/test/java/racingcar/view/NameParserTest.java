package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NameParserTest {

    @DisplayName("쉼표로 분리할 때 연속 쉼표는 빈 항목을 보존한다")
    @Test
    void split_Keeps_Empty_Tokens() {
        List<String> tokens = NameParser.parseNames("pobi,,jun");
        assertThat(tokens).containsExactly("pobi", "", "jun");
    }

    @DisplayName("원문 공백은 변형하지 않고 그대로 전달한다(trim 적용 안 함)")
    @Test
    void keep_Original_Spaces() {
        List<String> tokens = NameParser.parseNames(" pobi, jun ");
        assertThat(tokens).containsExactly(" pobi", " jun ");
    }

    @DisplayName("입력 값이 null이면 계약 위반으로 예외가 발생한다")
    @Test
    void nullIs_Contract_Violation() {
        assertThatThrownBy(() -> NameParser.parseNames(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
