package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Domain.MenuBoard;
import christmas.Exception.MenuException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validate {
    private final Set<String> foodList = Stream.of(MenuBoard.values()).map(MenuBoard::getName).collect(Collectors.toSet());
    public void elementBlank(String[] dividedHyphen){
        if (dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank()){
            throw new MenuException();
        }
    }

    public void hypen(String[] dividedHyphen){
        if (dividedHyphen.length != 2){
            throw new MenuException();
        }
    }

    public void number(String[] dividedHyphen){
        try{
            parseInt(dividedHyphen[1]);
        } catch (NumberFormatException e){
            throw new MenuException();
        }
    }

    public void inMenu(String[] dividedHyphen){
        if (!foodList.contains(dividedHyphen[0])){
            throw new MenuException();
        }
    }

    public void zero(String[] dividedHyphen){
        if (dividedHyphen[1].equals("0")){
            throw new MenuException();
        }
    }

    public void blank(List<String> divitedCommaList) {
        int blankCount = (int)divitedCommaList.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            throw new MenuException();
        }
    }

    public void duplicated(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(e -> e.split("-")[0]).collect(Collectors.toSet()).size();
        if (cnt != dividedCommaList.size()){
            throw new MenuException();
        }
    }

    public void foodCount(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(e -> e.split("-")[1]).mapToInt(Integer::parseInt).sum();
        if (cnt > 20){
            throw new MenuException();
        }
    }
}
