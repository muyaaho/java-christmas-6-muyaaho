package christmas.controller;

import static java.lang.Integer.parseInt;

import christmas.domain.Enum.MenuBoard;
import christmas.exception.MenuException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator {

    private static final String DELIMITER = "-";
    private static final int TOTAL_COUNT = 20;
    private final Set<String> foodList = Stream.of(MenuBoard.values()).map(MenuBoard::getName)
            .collect(Collectors.toSet());

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

    public void blank(List<String> divitedCommaList) {
        // TODO: validator는 여기서부터 시작
        int blankCount = (int) divitedCommaList.stream().filter(String::isBlank).count();
        if (blankCount > 0) {
            throw new MenuException();
        }
    }

    public void duplicated(List<String> dividedCommaList) {
        int cnt = dividedCommaList.stream().map(e -> e.split(DELIMITER)[0]).collect(Collectors.toSet()).size();
        if (cnt != dividedCommaList.size()) {
            throw new MenuException();
        }
    }

    public void foodCount(List<String> dividedCommaList) {
        int cnt = dividedCommaList.stream().map(e -> e.split(DELIMITER)[1]).mapToInt(Integer::parseInt).sum();
        if (cnt > TOTAL_COUNT) {
            throw new MenuException();
        }
    }

    public void onlyDrink(List<String> dividedCommaList) {
        int drinkCount = (int) dividedCommaList.stream().map(s -> {
            String[] dividedHyphen = s.split(DELIMITER);
            return MenuBoard.getCategory(dividedHyphen[0]);
        }).filter(s -> s.equals("DRINK")).count();
        if (drinkCount == dividedCommaList.size()) {
            throw new MenuException();
        }
    }
}
