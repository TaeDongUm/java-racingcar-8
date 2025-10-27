package racingcar.view;

import racingcar.dto.CarGameState;

public final class RoundLineFormatter {
    private RoundLineFormatter() {}

    public static String formatLine(CarGameState state) {
        StringBuilder sb = new StringBuilder();
        sb.append(state.getName()).append(" : ");
        for (int i = 0; i < state.getPosition(); i++) {
            sb.append('-');
        }
        return sb.toString();
    }
}