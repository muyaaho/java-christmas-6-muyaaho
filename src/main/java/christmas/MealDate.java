package christmas;

import static java.lang.Integer.parseInt;

public class MealDate {
    private int day;

    public MealDate(String day) {
        this.day = parseInt(day);
    }

    public int getDay() {
        return day;
    }
}
