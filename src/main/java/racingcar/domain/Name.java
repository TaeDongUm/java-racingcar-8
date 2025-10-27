package racingcar.domain;

import java.util.Objects;

public final class Name {
    private static final int MAX_LEN = 5;

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String raw) {
        Objects.requireNonNull(raw, "ERROR: 자동차 이름은 NULL일 수 없습니다.");

        if (raw.isEmpty()) {
            throw new IllegalArgumentException("ERROR: 이름은 빈 문자열이면 안됩니다.");
        }

        for (int i = 0; i < raw.length(); i++) {
            if (Character.isWhitespace(raw.charAt(i))) {
                throw new IllegalArgumentException("ERROR: 공백이 포함되면 안됩니다.");
            }
        }

        if (raw.length() > MAX_LEN) {
            throw new IllegalArgumentException("ERROR: 자동차 이름은 5글자 이하여야 합니다.");
        }

        return new Name(raw);
    }

    public String value() {
        return value;
    }
}
