package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Exception.DayException;

public class DayController {
    private static final int DEC_START = 1;
    private static final int DEC_END = 31;
    public int getDay(String day) {
        validateDay(day);
        int convertDay = parseInt(day);
        validate1stTo31st(convertDay);
        return convertDay;
    }

    private void validate1stTo31st(int day){
        if (day < DEC_START || day > DEC_END) {
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
