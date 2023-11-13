package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Domain.WootecoMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BenefitControllerTest {
    BenefitController benefitController;
    @BeforeEach
    void setUp(){
        benefitController = new BenefitController(new DiscountController());
    }
    @Test
    void 적용된_이벤트_없음(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("타파스", 1),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getBenefitList(26, orderedItems), new HashMap<String, Integer>());

        List<WootecoMenu> orderedItems2 = List.of(
                new WootecoMenu("시저샐러드", 1)
        );
        assertEquals(benefitController.getBenefitList(1, orderedItems), new HashMap<String, Integer>());
    }

    @Test
    void 적용된_이벤트_크리스마스디데이할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 1300);
        output.put("평일 할인", 0);
        output.put("주말 할인", 0);
        output.put("특별 할인", 0);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(4, orderedItems), output);

    }

    @Test
    void 적용된_이벤트_평일할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 2023);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("초코케이크", 1)
        );
        assertEquals(benefitController.getBenefitList(31, orderedItems), output);
    }

    @Test
    void 적용된_이벤트_주말할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 0);
        output.put("주말 할인", 2023);
        output.put("특별 할인", 0);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(30, orderedItems), output);
    }

    @Test
    void 특별할인만(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 0);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(benefitController.getBenefitList(31, orderedItems), output);
    }

    @Test
    void 전체_증정이벤트(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 1200);
        output.put("평일 할인", 4046);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 25000);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getBenefitList(3, orderedItems), output);
    }

    @Test
    void 총혜택_금액(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 1200);
        output.put("평일 할인", 4046);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 25000);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getTotalBenefitAmount(3, orderedItems), 31246);
    }

    @Test
    void 할인_후_예상_결제_금액_할인X(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("타파스", 1),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getFinalCost(26, orderedItems), 8500);
    }

    @Test
    void 할인_후_예상_결제_금액_할인O(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getFinalCost(3, orderedItems), 135754);
    }

    @Test
    void 이벤트_배지_없음(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("타파스", 1),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getBadge(26, orderedItems), "없음");
    }

    @Test
    void 이벤트_배지_있음(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(benefitController.getBadge(3, orderedItems), "산타");

    }
}
