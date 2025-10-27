package racingcar.view;

public enum OutputMessage {
    CAR_NAME_PROMPT("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    ATTEMPTS_PROMPT("시도할 횟수는 몇 회인가요?"),
    RESULT_HEADER("\n실행 결과"),
    WINNERS_PREFIX("최종 우승자 : ");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
