package christmas.controller;

import static java.lang.Integer.parseInt;

import christmas.exception.DayException;

public class DayController {
    private static final int DEC_START = 1;
    private static final int DEC_END = 31;

    public int getDay(String day) {
        validateNumber(day);
        int convertDay = parseInt(day);
        validateRange(convertDay);
        return convertDay;
    }

    private void validateRange(int day) {
        if (isEventDay(day)) {
            throw new DayException();
        }
    }

    private boolean isEventDay(int day) {
        return day < DEC_START || day > DEC_END;
    }

    private void validateNumber(String day) {
        try {
            parseInt(day);
        } catch (NumberFormatException numberFormatException) {
            throw new DayException();
        }
    }
}
