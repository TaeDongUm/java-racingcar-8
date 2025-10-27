package racingcar.domain;

public class ThresholdMoveStrategy implements MoveStrategy {

    private static final int MOVE_THRESHOLD = 4;

    private final NumberPicker picker;

    public ThresholdMoveStrategy(NumberPicker picker) {
        this.picker = picker;
    }

    @Override
    public boolean shouldMove() {
        return picker.pick() >= MOVE_THRESHOLD;
    }
}
