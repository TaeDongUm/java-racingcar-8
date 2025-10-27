package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameTest {

    @DisplayName("분리된 각 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void name_Longer_Than_Five_Is_Invalid() {
        assertThatThrownBy(() -> Name.of("abcdef")) // 6자
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 5자 이하면 정상 처리된다(경계값 포함)")
    @Test
    void name_Length_Up_To_Five_Is_Valid() {
        assertThat(Name.of("a")).isNotNull();        // 1자
        assertThat(Name.of("abcde")).isNotNull();    // 5자 경계
    }

    @DisplayName("이름이 빈 문자열이면 예외가 발생한다")
    @Test
    void empty_Is_Invalid() {
        assertThatThrownBy(() -> Name.of(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 공백만으로 이루어지면 예외가 발생한다")
    @Test
    void blank_Only_Is_Invalid() {
        assertThatThrownBy(() -> Name.of("   "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름에 공백 문자가 하나라도 포함되면 예외가 발생한다(내부/앞/뒤 포함)")
    @Test
    void any_Whitespace_Is_Invalid() {
        assertThatThrownBy(() -> Name.of("po bi"))   // 내부 공백
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.of(" pobi"))   // 앞 공백
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.of("pobi "))   // 뒤 공백
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.of("po\tbi"))  // 탭
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Name.of("po\nbi"))  // 개행
                .isInstanceOf(IllegalArgumentException.class);
    }


}
