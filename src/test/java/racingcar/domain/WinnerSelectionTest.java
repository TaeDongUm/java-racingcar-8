package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import racingcar.dto.CarGameState;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerSelectionTest {
    List<List<CarGameState>> carGameStates;

    @DisplayName("단독 우승자: 최대 위치의 참가자 한 명을 반환한다(입력 순서 유지)")
    @Test
    void singleWinner() {
        List<CarGameState> carGameState = List.of(
                new CarGameState("pobi", 3),
                new CarGameState("woni", 2),
                new CarGameState("jun", 1)
        );
        carGameStates = List.of(carGameState);
        List<String> winners = WinnerSelection.selectByMaxPosition(carGameStates);
        assertThat(winners).containsExactly("pobi");
    }

    @DisplayName("공동 우승자: 최대 위치가 동일하면 입력 순서대로 모두 반환한다")
    @Test
    void jointWinners_keepInputOrder() {
        List<CarGameState> carGameState = List.of(
                new CarGameState("pobi", 3),
                new CarGameState("woni", 3),
                new CarGameState("jun",  1)
        );
        carGameStates = List.of(carGameState);
        List<String> winners = WinnerSelection.selectByMaxPosition(carGameStates);
        assertThat(winners).containsExactly("pobi", "woni");
    }
}
