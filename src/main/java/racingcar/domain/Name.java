package racingcar.domain;

import racingcar.error.ErrorMessage;

import java.util.Objects;

public final class Name {
    private static final int MAX_LEN = 5;

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String raw) {
        Objects.requireNonNull(raw, ErrorMessage.NAME_NULL.toString());
        raw = raw.trim();

        if (raw.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_EMPTY.toString());
        }

        for (int i = 0; i < raw.length(); i++) {
            if (Character.isWhitespace(raw.charAt(i))) {
                throw new IllegalArgumentException(ErrorMessage.NAME_CONTAINS_WHITESPACE.toString());
            }
        }

        if (raw.length() > MAX_LEN) {
            throw new IllegalArgumentException(ErrorMessage.NAME_TOO_LONG.toString());
        }

        return new Name(raw);
    }

    public String value() {
        return value;
    }
}
