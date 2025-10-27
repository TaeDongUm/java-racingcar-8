package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 출력_형식_정확성_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "5");
                String actualOutput = output();
                String expectedOutput =
                    "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)\n" +
                    "시도할 횟수는 몇 회인가요?\n" +
                    "\n" +
                    "실행 결과\n" +
                    "pobi : -\n" +
                    "woni : \n" +
                    "jun : -\n" +
                    "\n" +
                    "pobi : --\n" +
                    "woni : -\n" +
                    "jun : --\n" +
                    "\n" +
                    "pobi : ---\n" +
                    "woni : --\n" +
                    "jun : ---\n" +
                    "\n" +
                    "pobi : ----\n" +
                    "woni : ---\n" +
                    "jun : ----\n" +
                    "\n" +
                    "pobi : -----\n" +
                    "woni : ---\n" +
                    "jun : -----\n" +
                    "\n" +
                    "최종 우승자 : pobi, jun";

                // OS에 따른 개행 문자 차이를 없애기 위해, 실제 출력 값의 개행 문자를 \n으로 통일
                String normalizedActual = actualOutput.replace("\r\n", "\n");

                // Mockito 메시지를 무시하고, 실제 출력이 기대 출력으로 끝나는지 검증
                assertThat(normalizedActual).endsWith(expectedOutput);
            },
            MOVING_FORWARD, STOP, MOVING_FORWARD,           // 1라운드
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 2라운드
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 3라운드
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 4라운드
            MOVING_FORWARD, STOP, MOVING_FORWARD            // 5라운드
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

