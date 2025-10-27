package racingcar.view;

import java.util.List;
import racingcar.dto.CarGameState;

public class OutputView {

    public void printExecutionResultHeader() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printRoundResult(List<CarGameState> roundState) {
        for (CarGameState carState : roundState) {
            System.out.println(RoundLineFormatter.formatLine(carState));
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println(WinnersFormatter.format(winners));
    }
}