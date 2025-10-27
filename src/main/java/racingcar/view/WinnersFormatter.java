package racingcar.view;

import java.util.List;

public final class WinnersFormatter {
    private WinnersFormatter() {}

    public static String format(List<String> winners) {
        return OutputMessage.WINNERS_PREFIX + String.join(", ", winners);
    }
}
