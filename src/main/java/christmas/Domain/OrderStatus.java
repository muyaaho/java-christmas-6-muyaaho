package christmas.Domain;

import java.util.List;

public record OrderStatus(int day, List<WootecoMenu> foods, int totalCost) {
    public boolean canGift(){
        return totalCost >= 120_000;
    }
}
