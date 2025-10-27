package racingcar.view;

import java.util.List;

public final class WinnersFormatter {
    private WinnersFormatter() {}

    public static String format(List<String> winners) {
        return "최종 우승자 : " + String.join(", ", winners);
    }
}
