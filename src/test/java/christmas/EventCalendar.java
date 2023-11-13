package christmas;

import java.util.Arrays;
import java.util.List;

public class EventCalendar {

    // TODO: 1. weekend를 하드코딩하기
    // 2. 계산하기
    /*
    * 날짜 입력
    * 나누기 7
    * 3%7 = 3, 4, 5, 6, 7%7 == 0
    *
    * */

    List<Integer> weekend_mod = Arrays.asList(1,2);
    public int getWeekdayDiscount(int day, List<WootecoMenu> orderedItems){
        int discountAmount = 0;
        if (!weekend_mod.contains(day%7)){
            discountAmount = orderedItems.stream().map(this::getDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    private int getDiscount(WootecoMenu item){
        if (MenuBoard.getCategory(item.name()).equals("DESSERT")){
            return 2023 * item.count();
        }
        return 0;
    }
}
