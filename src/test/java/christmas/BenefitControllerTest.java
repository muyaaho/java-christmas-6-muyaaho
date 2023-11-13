package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BenefitControllerTest {
    @Test
    // 혜택 내역을 출력할 때는 가격, 음식 목록(카테고리) 필요
    void 이벤트내역(){
        BenefitController benefitController = new BenefitController();
        List<WootecoMenu> input = Arrays.asList(new WootecoMenu("타파스", 1), new WootecoMenu("제로콜라", 1));
        assertEquals(benefitController.getEventDetails(8500, input), Arrays.asList("없음"));

        List<WootecoMenu> input2 = Arrays.asList(new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1));
        assertEquals(benefitController.getEventDetails(8500, input2), "없음");

    }
}
