package christmas.Domain.Record;

import java.util.List;

public record OrderStatus(int day, List<WootecoMenu> foods, int totalPrice) {
    private static final int GIFT_THRESHOLD = 120_000;
    public boolean canGift(){
        return totalPrice >= GIFT_THRESHOLD;
    }
}
