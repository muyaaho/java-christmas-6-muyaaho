package christmas.controller;

import static java.lang.Integer.parseInt;

import christmas.domain.MenuBoard;
import christmas.exception.MenuException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator {

    private static final String DELIMITER = "-";
    private static final int TOTAL_COUNT = 20;

    private final Set<String> foodList = MenuBoard.getFoodNames();

    public void elementBlank(String[] dividedHyphen) {
        if (hasValue(dividedHyphen)) {
            throw new MenuException();
        }
    }

    private boolean hasValue(String[] dividedHyphen) {
        return dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank();
    }

    public void hyphen(String[] dividedHyphen) {
        if (hasTwoElements(dividedHyphen)) {
            throw new MenuException();
        }
    }

    private boolean hasTwoElements(String[] dividedHyphen) {
        return dividedHyphen.length != 2;
    }

    public void number(String[] dividedHyphen) {
        try {
            parseInt(dividedHyphen[1]);
        } catch (NumberFormatException numberFormatException) {
            throw new MenuException();
        }
    }

    public void inMenu(String[] dividedHyphen) {
        if (!foodList.contains(dividedHyphen[0])) {
            throw new MenuException();
        }
    }


    public void zero(String[] dividedHyphen) {
        if (isZeroValue(dividedHyphen)) {
            throw new MenuException();
        }
    }

    private boolean isZeroValue(String[] dividedHyphen) {
        return dividedHyphen[1].equals("0");
    }

    public void blank(List<String> dividedComma) {
        if (isBlank(dividedComma)) {
            throw new MenuException();
        }
    }

    private boolean isBlank(List<String> dividedComma){
        int blankCount = (int) dividedComma.stream()
                .filter(String::isBlank)
                .count();
        return blankCount > 0;
    }

    public void duplicate(List<String> dividedComma) {
        if (isDifferentSize(dividedComma)) {
            throw new MenuException();
        }
    }

    private boolean isDifferentSize(List<String> dividedComma){
        long cnt = dividedComma.stream()
                .map(element -> element.split(DELIMITER)[0])
                .distinct()
                .count();
        return cnt != dividedComma.size();
    }


    public void foodCount(List<String> dividedComma) {
        if (isOverLimit(dividedComma)) {
            throw new MenuException();
        }
    }

    private boolean isOverLimit(List<String> dividedComma) {
        int cnt = dividedComma.stream()
                .map(e -> e.split(DELIMITER)[1])
                .mapToInt(Integer::parseInt)
                .sum();
        return cnt > TOTAL_COUNT;
    }

    public void onlyDrink(List<String> dividedComma) {
        if (getDrinkCount(dividedComma) == dividedComma.size()) {
            throw new MenuException();
        }
    }

    private int getDrinkCount (List<String> dividedComma) {
        return (int) getfoodName(dividedComma)
                .filter(MenuBoard::isDrink)
                .count();
    }

    private Stream<String> getfoodName(List<String> dividedComma) {
        return dividedComma.stream()
                .map(element -> element.split(DELIMITER)[0]);
    }
}
