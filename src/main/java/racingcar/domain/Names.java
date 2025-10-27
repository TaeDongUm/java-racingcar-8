package racingcar.domain;

import racingcar.error.ErrorMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Names {
    private final List<Name> values;

    public Names(List<Name> values) {
        validateDuplicates(values);
        this.values = values;
    }

    private void validateDuplicates(List<Name> names) {
        Set<String> uniqueNames = new HashSet<>();
        for (Name name : names) {
            uniqueNames.add(name.value());
        }

        if (uniqueNames.size() < names.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CAR_NAMES.toString());
        }
    }

    public List<Name> getValues() {
        return Collections.unmodifiableList(values);
    }
}
