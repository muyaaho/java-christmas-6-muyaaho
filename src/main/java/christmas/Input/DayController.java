package christmas.Input;

import static java.lang.Integer.parseInt;

import christmas.CustomException;

public class DayController {
    private int day;

    public DayController(String day) {
        validateDay(day);
        int convertDay = parseInt(day);
        validate1stTo31st(convertDay);
        this.day = convertDay;
    }

    public int getDay() {
        return day;
    }

    private void validate1stTo31st(int day){
        if (day < 1 || day > 31) {
            new CustomException("날짜");
        }
    }

    private void validateDay(String day){
        try{
            parseInt(day);
        } catch(NumberFormatException e){
            new CustomException("날짜");
        }
    }
}
