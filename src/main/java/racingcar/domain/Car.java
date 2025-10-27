package racingcar.domain;

public class Car {

    private final Name name;
    private int position;

    public Car(String name) {
        this.name = Name.of(name);
        this.position = 0;
    }

    public String getName() {
        return name.value();
    }

    public int getPosition() {
        return position;
    }

    public void moveIf(boolean moveCondition) {
        if (moveCondition) {
            position += 1;
        }
    }
}
