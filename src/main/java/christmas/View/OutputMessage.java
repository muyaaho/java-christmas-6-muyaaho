package christmas.View;

public enum OutputMessage {
    PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("주문 메뉴"),
    BEFORE_DISCOUNT("할인 전 총주문 금액"),
    GIFT("증정 메뉴"),
    BENEFIT_LIST("혜택 내역"),
    TOTAL_BENEFIT("총혜택 금액"),
    FINAL_COST("할인 후 예상 결제 금액"),
    BADGE("12월 이벤트 배지");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
