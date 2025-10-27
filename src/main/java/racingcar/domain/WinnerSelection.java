package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.CarGameState;

public class WinnerSelection {

    private WinnerSelection() {
    }

    public static List<String> selectByMaxPosition(List<List<CarGameState>> carGameStates) {

        List<CarGameState> finalRoundState = carGameStates.get(carGameStates.size() - 1);

        int maxPosition = finalRoundState.stream()
                .mapToInt(CarGameState::getPosition)
                .max()
                .orElse(0); // 자동차가 없는 경우 기본값 0

        return finalRoundState.stream()
                .filter(carGameState -> carGameState.getPosition() == maxPosition)
                .map(CarGameState::getName)
                .collect(Collectors.toList());
    }
}
