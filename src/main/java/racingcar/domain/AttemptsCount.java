package racingcar.domain;

public final class AttemptsCount {
    private static final int MIN_ATTEMPTS = 1;

    private final int value;

    private AttemptsCount(int value) {
        this.value = value;
    }

    public static AttemptsCount of(int value) {
        if (value < MIN_ATTEMPTS) {
            throw new IllegalArgumentException("ERROR: 이동 횟수는 1 이상이어야 합니다.");
        }
        return new AttemptsCount(value);
    }

    public int value() {
        return value;
    }
}
