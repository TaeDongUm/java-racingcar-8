package racingcar.error;

public enum ErrorMessage {
    NAME_NULL("ERROR: 자동차 이름은 NULL일 수 없습니다."),
    NAME_EMPTY("ERROR: 이름은 빈 문자열이면 안됩니다."),
    NAME_CONTAINS_WHITESPACE("ERROR: 공백이 포함되면 안됩니다."),
    NAME_TOO_LONG("ERROR: 자동차 이름은 5글자 이하여야 합니다."),
    ATTEMPTS_COUNT_INVALID_RANGE("ERROR: 이동 횟수는 1 이상이어야 합니다."),
    DUPLICATE_CAR_NAMES("ERROR: 자동차 이름은 중복될 수 없습니다."),
    ATTEMPTS_NULL("ERROR: 이동할 횟수는 NULL이면 안됩니다."),
    ATTEMPTS_NOT_A_NUMBER("ERROR: 이동할 횟수는 정수여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
