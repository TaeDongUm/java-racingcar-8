package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputCarName() {
        System.out.println(OutputMessage.CAR_NAME_PROMPT);
        return Console.readLine();
    }

    public String inputAttempts() {
        System.out.println(OutputMessage.ATTEMPTS_PROMPT);
        return Console.readLine();
    }
}
