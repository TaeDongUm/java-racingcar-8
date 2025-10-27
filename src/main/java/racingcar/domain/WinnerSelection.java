package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.CarGameState;

public class WinnerSelection {

    public static List<String> selectByMaxPosition(List<List<CarGameState>> carGameStates) {
        List<CarGameState> carGameStateList = carGameStates.get(carGameStates.size() - 1);
        int maxPosition = getMaxPosition(carGameStateList);

        List<String> finalResult = new ArrayList<>();
        for (CarGameState carGameState : carGameStateList) {
            if (carGameState.getPosition() == maxPosition) {
                finalResult.add(carGameState.getName());
            }
        }
        return finalResult;
    }

    public static int getMaxPosition(List<CarGameState> carGameStates) {
        int maxPosition = 0;
        for (CarGameState carGameState : carGameStates) {
            if (carGameState.getPosition() > maxPosition) {
                maxPosition = carGameState.getPosition();
            }
        }
        return maxPosition;
    }
}
