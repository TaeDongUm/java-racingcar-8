package racingcar.domain;

import racingcar.error.ErrorMessage;

public final class AttemptsCount {
    private static final int MIN_ATTEMPTS = 1;

    private final int value;

    private AttemptsCount(int value) {
        this.value = value;
    }

    public static AttemptsCount of(int value) {
        if (value < MIN_ATTEMPTS) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPTS_COUNT_INVALID_RANGE.toString());
        }
        return new AttemptsCount(value);
    }

    public int value() {
        return value;
    }
}
