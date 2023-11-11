package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputControllerTest {
    InputController inputController;
    @BeforeEach
    void setUp(){
        inputController = new InputController();
    }
    @Test
    void 메뉴_입력을_WootecoMenu객체로(){
        assertThat(new WootecoMenu("티본스테이크", 1)).isEqualToComparingFieldByField(inputController.inputToWootechMenu("티본스테이크-1"));
    }




}
