package racingcar.domain;

public class Car {

    private static final int STARTING_POSITION = 0;
    private static final int MOVE_INCREMENT = 1;

    private final Name name;
    private int position;

    public Car(String name) {
        this.name = Name.of(name);
        this.position = STARTING_POSITION;
    }

    public String getName() {
        return name.value();
    }

    public int getPosition() {
        return position;
    }

    public void moveIf(boolean moveCondition) {
        if (moveCondition) {
            position += MOVE_INCREMENT;
        }
    }
}
