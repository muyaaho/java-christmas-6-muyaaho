package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Domain.Record.BenefitStatus;
import christmas.Domain.GenerateBenefitStatus;
import christmas.Controller.DiscountController;
import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.GenerateWootecoMenu;
import christmas.Domain.Record.WootecoMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenerateBenefitStatusTest {
    List<WootecoMenu> noBenefitInput;
    List<WootecoMenu> yesBenefitInput;
    @BeforeEach
    void setUp(){

        noBenefitInput = List.of(
                makeMenu("타파스", 1),
                makeMenu("제로콜라", 1)
        );
        yesBenefitInput = List.of(
                makeMenu("티본스테이크", 1),
                makeMenu("바비큐립", 1),
                makeMenu("초코케이크", 2),
                makeMenu("제로콜라", 1)
        );
    }

    private WootecoMenu makeMenu(String name, int count){
        return new GenerateWootecoMenu(name, count).generateor();
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

    public OrderStatus makeOrder(int day, List<WootecoMenu> input){
        return new GenerateOrderStatus(day, input).generate();
    }

    // TODO BenefitStatus 만드는 함수 부터 시작!!!
    public BenefitStatus makeBenefit(OrderStatus orderStatus){
        return new GenerateBenefitStatus(orderStatus, new DiscountController()).generate();
    }

    @Test
    void 적용된_이벤트_없음(){
        BenefitStatus benefitStatus = new GenerateBenefitStatus(makeOrder(26, noBenefitInput), new DiscountController()).generate();
        assertEquals(benefitStatus.benefitList(), new HashMap<String, Integer>());

        List<WootecoMenu> orderedItems2 = List.of(
                makeMenu("시저샐러드", 1)
        );
        assertEquals(makeBenefit(makeOrder(1, orderedItems2)).benefitList(), new HashMap<String, Integer>());
    }

    @Test
    void 적용된_이벤트_크리스마스디데이할인(){
        Map<String, Integer> output = makeOutput(1300, 0, 0, 0, 0);
        List<WootecoMenu> orderedItems = List.of(
                makeMenu("티본스테이크", 1)
        );
        assertEquals(makeBenefit(makeOrder(4, orderedItems)).benefitList(), output);

    }

    @Test
    void 적용된_이벤트_평일할인(){
        Map<String, Integer> output = makeOutput(0, 2023, 0, 1000, 0);
        List<WootecoMenu> orderedItems = List.of(
                makeMenu("초코케이크", 1)
        );
        assertEquals(makeBenefit(makeOrder(31, orderedItems)).benefitList(), output);
    }

    @Test
    void 적용된_이벤트_주말할인(){
        Map<String, Integer> output = makeOutput(0, 0, 2023, 0, 0);
        List<WootecoMenu> orderedItems = List.of(
                makeMenu("티본스테이크", 1)
        );
        assertEquals(makeBenefit(makeOrder(30, orderedItems)).benefitList(), output);
    }

    @Test
    void 특별할인만(){
        Map<String, Integer> output = makeOutput(0, 0, 0, 1000, 0);
        List<WootecoMenu> orderedItems = List.of(
                makeMenu("티본스테이크", 1)
        );
        assertEquals(makeBenefit(makeOrder(31, orderedItems)).benefitList(), output);
    }

    @Test
    void 전체_증정이벤트(){
        Map<String, Integer> output = makeOutput(1200, 4046, 0, 1000, 25000);
        assertEquals(makeBenefit(makeOrder(3, yesBenefitInput)).benefitList(), output);
    }

    @Test
    void 총혜택_금액(){
        assertEquals(makeBenefit(makeOrder(3, yesBenefitInput)).totalBenefitCost(), 31246);
    }

    @Test
    void 할인_후_예상_결제_금액_할인X(){
        assertEquals(makeBenefit(makeOrder(3, noBenefitInput)).finalCost(), 8500);
    }

    @Test
    void 할인_후_예상_결제_금액_할인O(){
        assertEquals(makeBenefit(makeOrder(3, yesBenefitInput)).finalCost(), 135754);
    }

    @Test
    void 이벤트_배지_없음(){
        assertEquals(makeBenefit(makeOrder(3, noBenefitInput)).badge(), "없음");
    }

    @Test
    void 이벤트_배지_있음(){
        assertEquals(makeBenefit(makeOrder(3, yesBenefitInput)).badge(), "산타");

    }
}
