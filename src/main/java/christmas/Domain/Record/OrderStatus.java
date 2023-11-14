package christmas.Domain.Record;

import christmas.Domain.GenerateWootecoMenu;
import java.util.List;

public record OrderStatus(int day, List<WootecoMenu> foods, int totalCost) {
    private static final int GIFT_THRESHOLD = 120_000;
    public boolean canGift(){
        return totalCost >= GIFT_THRESHOLD;
    }
}
