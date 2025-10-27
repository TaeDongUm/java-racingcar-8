package racingcar.view;

import java.util.Arrays;
import java.util.List;

public final class NameParser {
    private NameParser() {}

    public static List<String> parseNames(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("ERROR: 입력값은 NULL일 수 없습니다.");
        }

        // 빈 항목 보존, 공백 변형 금지
        String[] parts = raw.split(",", -1);
        return Arrays.asList(parts);
    }
}
