package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.bytebuddy.build.ToStringPlugin.Enhance;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    void 날짜_객체에_저장(){
        MealDate mealDate = new MealDate("4");
        assertEquals(4, mealDate.getDay());

        MealDate mealDate2 = new MealDate("100");
        assertEquals(100, mealDate2.getDay());
    }

}
