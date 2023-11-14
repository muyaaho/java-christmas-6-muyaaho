package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Domain.MenuBoard;
import christmas.Exception.MenuException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OrderedItemsController {
    private final Set<String> foodList = Stream.of(MenuBoard.values()).map(MenuBoard::getName).collect(Collectors.toSet());

    // TODO[네이밍]: seperateComma말고 다른 이름..?
    public List<String> seperateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(",", -1));
        return validate(dividedCommaList);
    }

    private List<String> validate(List<String> dividedCommaList){
        dividedCommaList.stream().forEach(s -> {
            String[] dividedHyphen = s.split("-");
            validateNotHypen(dividedHyphen);
            validateNumber(dividedHyphen);
            validateElementBlank(dividedHyphen);
            validateInMenu(dividedHyphen);
            validateZero(dividedHyphen);
        });
        validateBlank(dividedCommaList);
        validateDuplicated(dividedCommaList);
        validateTotalCount(dividedCommaList);
        return dividedCommaList;
    }


    private void validateElementBlank(String[] dividedHyphen){
        if (dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank()){
            throw new MenuException();
        }
    }

    private void validateNotHypen(String[] dividedHyphen){
        if (dividedHyphen.length != 2){
            throw new MenuException();
        }
    }

    private void validateNumber(String[] dividedHyphen){
        try{
            parseInt(dividedHyphen[1]);
        } catch (NumberFormatException e){
            throw new MenuException();
        }
    }

    private void validateInMenu(String[] dividedHyphen){
        if (!foodList.contains(dividedHyphen[0])){
            throw new MenuException();
        }
    }

    public void validateZero(String[] dividedHyphen){
        if (dividedHyphen[1].equals("0")){
            throw new MenuException();
        }
    }

    private void validateBlank(List<String> divitedCommaList) {
        int blankCount = (int)divitedCommaList.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            throw new MenuException();
        }
    }

    private void validateDuplicated(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(e -> e.split("-")[0]).collect(Collectors.toSet()).size();
        if (cnt != dividedCommaList.size()){
            throw new MenuException();
        }
    }

    private void validateTotalCount(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(e -> e.split("-")[1]).mapToInt(Integer::parseInt).sum();
        if (cnt > 20){
            throw new MenuException();
        }
    }
}
