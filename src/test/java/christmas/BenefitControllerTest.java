package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.OrderStatus;
import christmas.Domain.WootecoMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BenefitControllerTest {
    BenefitController benefitController;
    List<WootecoMenu> noBenefitInput;
    List<WootecoMenu> yesBenefitInput;
    @BeforeEach
    void setUp(){
        benefitController = new BenefitController(new DiscountController());
        noBenefitInput = List.of(
                new WootecoMenu("타파스", 1),
                new WootecoMenu("제로콜라", 1)
        );
        yesBenefitInput = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
    }

    public Map<String, Integer> makeOutput(int christMas, int weekday, int weekend, int special, int gift){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", christMas);
        output.put("평일 할인", weekday);
        output.put("주말 할인", weekend);
        output.put("특별 할인", special);
        output.put("증정 이벤트", gift);
        return output;
    }

    public OrderStatus makeInput(int day, List<WootecoMenu> input){
        return new GenerateOrderStatus(day, input).generate();
    }
    @Test
    void 적용된_이벤트_없음(){
        assertEquals(benefitController.getBenefitList(makeInput(26, noBenefitInput)), new HashMap<String, Integer>());

        List<WootecoMenu> orderedItems2 = List.of(
                new WootecoMenu("시저샐러드", 1)
        );
        assertEquals(benefitController.getBenefitList(makeInput(1, orderedItems2)), new HashMap<String, Integer>());
    }

    @Test
    void 적용된_이벤트_크리스마스디데이할인(){
        Map<String, Integer> output = makeOutput(1300, 0, 0, 0, 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(makeInput(4, orderedItems)), output);

    }

    @Test
    void 적용된_이벤트_평일할인(){
        Map<String, Integer> output = makeOutput(0, 2023, 0, 1000, 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("초코케이크", 1)
        );
        assertEquals(benefitController.getBenefitList(makeInput(31, orderedItems)), output);
    }

    @Test
    void 적용된_이벤트_주말할인(){
        Map<String, Integer> output = makeOutput(0, 0, 2023, 0, 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(makeInput(30, orderedItems)), output);
    }

    @Test
    void 특별할인만(){
        Map<String, Integer> output = makeOutput(0, 0, 0, 1000, 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(makeInput(31, orderedItems)), output);
    }

    @Test
    void 전체_증정이벤트(){
        Map<String, Integer> output = makeOutput(1200, 4046, 0, 1000, 25000);
        assertEquals(benefitController.getBenefitList(makeInput(3, yesBenefitInput)), output);
    }

    @Test
    void 총혜택_금액(){
        Map<String, Integer> output = makeOutput(1200, 4046, 0, 1000, 25000);
        assertEquals(benefitController.getTotalBenefitAmount(makeInput(3, yesBenefitInput)), 31246);
    }

    @Test
    void 할인_후_예상_결제_금액_할인X(){
        assertEquals(benefitController.getFinalCost(makeInput(3, noBenefitInput)), 8500);
    }

    @Test
    void 할인_후_예상_결제_금액_할인O(){
        assertEquals(benefitController.getFinalCost(makeInput(3, yesBenefitInput)), 135754);
    }

    @Test
    void 이벤트_배지_없음(){
        assertEquals(benefitController.getBadge(makeInput(3, noBenefitInput)), "없음");
    }

    @Test
    void 이벤트_배지_있음(){
        assertEquals(benefitController.getBadge(makeInput(3, yesBenefitInput)), "산타");

    }
}
