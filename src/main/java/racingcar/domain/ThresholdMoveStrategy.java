package racingcar.domain;

public class ThresholdMoveStrategy implements MoveStrategy {

    private final NumberPicker picker;

    public ThresholdMoveStrategy(NumberPicker picker) {
        this.picker = picker;
    }

    @Override
    public boolean shouldMove() {
        return picker.pick() >= 4;
    }
}
