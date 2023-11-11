package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class ValidateMenuTest {

    @Test
    void 메뉴에_있는_음식이입력되면_통과(){
        assertDoesNotThrow(() -> new WootecoMenu("티본스테이크", 1));
    }
}
