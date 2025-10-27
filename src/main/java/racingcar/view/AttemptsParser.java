package racingcar.view;

public final class AttemptsParser {
    private AttemptsParser() {}

    public static int parseAttempts(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("ERROR: 이동할 횟수는 NULL이면 안됩니다.");
        }
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: 이동할 횟수는 정수여야 합니다.");
        }
    }
}
