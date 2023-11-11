package christmas;

import static java.lang.Integer.parseInt;

public class ReservationDate {
    private int day;

    public ReservationDate(String day) {
        validateDay(day);
        int convertDay = parseInt(day);
        validateOneToThirtyOne(convertDay);
        this.day = convertDay;
    }

    public int getDay() {
        return day;
    }

    private void validateOneToThirtyOne(int day){
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDay(String day){
        try{
            parseInt(day);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
