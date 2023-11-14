package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Exception.DayException;

public class DayController {
    public int getDay(String day) {
        validateDay(day);
        int convertDay = parseInt(day);
        validate1stTo31st(convertDay);
        return convertDay;
    }

    private void validate1stTo31st(int day){
        if (day < 1 || day > 31) {
            throw new DayException();
        }
    }

    private void validateDay(String day){
        try{
            parseInt(day);
        } catch(NumberFormatException e){
            throw new DayException();
        }
    }
}
