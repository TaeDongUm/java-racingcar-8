package racingcar.view;

import racingcar.error.ErrorMessage;

public final class AttemptsParser {
    private AttemptsParser() {}

    public static int parseAttempts(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPTS_NULL.toString());
        }
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPTS_NOT_A_NUMBER.toString());
        }
    }
}
