package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import christmas.InputController;
import org.junit.jupiter.api.BeforeEach;

public class DividedInputTest {
    InputController inputController;

    @BeforeEach
    void setUp() {
        inputController = new InputController();
    }

    private void assertOneInput(String inputString) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                        () -> inputController.inputToWootechMenu(inputString))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}

