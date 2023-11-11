package christmas.InputTest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.WootecoMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidateMenuTest {

    @Test
    void 메뉴에_있는_음식이입력되면_통과(){
        assertDoesNotThrow(() -> new WootecoMenu("티본스테이크", 1));
    }


    @ParameterizedTest
    @ValueSource(strings = {"마라탕", "닭볶음탕", "삼겹살구이", "초밥", "맥주"})
    void 메뉴판에_없는_메뉴_주문(String foodName){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new WootecoMenu(foodName,1)).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}
