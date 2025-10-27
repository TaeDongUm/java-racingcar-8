package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarGameState;

import static org.assertj.core.api.Assertions.assertThat;

class RoundLineFormatterTest {

    @DisplayName("위치 3이면 '이름 : ---' 형식으로 출력된다")
    @Test
    void formatLine_position3() {
        CarGameState state = new CarGameState("pobi", 3);
        String line = RoundLineFormatter.formatLine(state);
        assertThat(line).isEqualTo("pobi : ---");
    }

    @DisplayName("위치 0이면 '이름 : ' 뒤에 대시가 없다")
    @Test
    void formatLine_position0() {
        CarGameState state = new CarGameState("woni", 0);
        String line = RoundLineFormatter.formatLine(state);
        assertThat(line).isEqualTo("woni : ");
    }
}
