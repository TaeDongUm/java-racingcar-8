package racingcar.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinnersFormatterTest {

    @DisplayName("단독 우승자는 '최종 우승자 : pobi' 형식으로 출력된다")
    @Test
    void singleWinner() {
        String text = WinnersFormatter.format(List.of("pobi"));
        assertThat(text).isEqualTo("최종 우승자 : pobi");
    }

    @DisplayName("공동 우승자는 입력 순서를 유지해 쉼표로 연결한다")
    @Test
    void multipleWinners() {
        String text = WinnersFormatter.format(List.of("pobi", "jun"));
        assertThat(text).isEqualTo("최종 우승자 : pobi, jun");
    }
}
