package christmas.controller;

import static java.lang.Integer.parseInt;

import christmas.domain.WootecoMenuFactory;
import christmas.domain.WootecoMenu;
import java.util.Arrays;
import java.util.List;


public class FoodsController {
    private final String FIRST_SEP = ",";
    private final String SECOND_SEP = "-";
    private final Validator validate;

    public FoodsController(Validator validate) {
        this.validate = validate;
    }

    public List<WootecoMenu> setOrderedMenu(String line){
        List<String> dividedCommaList = separateComma(line);
        return dividedCommaList.stream().map(this::inputToWootechMenu).toList();
    }

    private WootecoMenu inputToWootechMenu(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split(SECOND_SEP);
        return new WootecoMenuFactory(dividedHyphen[0], parseInt(dividedHyphen[1])).generateor();
    }

    private List<String> separateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(FIRST_SEP, -1));
        return allValidate(dividedCommaList);
    }

    private List<String> allValidate(List<String> dividedCommaList){
        dividedCommaList.forEach(foodNameAndCount -> {
            // TODO: 나누는 것도 validate 안에서 하는 건 어떨까
            String[] dividedHyphen = foodNameAndCount.split(SECOND_SEP);
            validate.hyphen(dividedHyphen);
            validate.number(dividedHyphen);
            validate.elementBlank(dividedHyphen);
            validate.inMenu(dividedHyphen);
            validate.zero(dividedHyphen);
        });
        validate.blank(dividedCommaList);
        validate.duplicate(dividedCommaList);
        validate.foodCount(dividedCommaList);
        validate.onlyDrink(dividedCommaList);
        return dividedCommaList;
    }

}
