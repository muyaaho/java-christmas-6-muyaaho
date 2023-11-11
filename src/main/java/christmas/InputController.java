package christmas;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class InputController {
    List<String> menuboard = new ArrayList<>(Arrays.asList("양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"));


    public List<String> seperateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(",", -1));
//        System.out.println(dividedCommaList);
        dividedCommaList.stream().forEach(s -> {
            validateNotHypen(s);
            validateNumber(s);
            validateElementBlank(s);
            validateInMenu(s);
        });
        validateBlank(dividedCommaList);
        validateDuplicated(dividedCommaList);
        validateTotalCount(dividedCommaList);
        return dividedCommaList;
    }

    public List<WootecoMenu> setOrderedMenu(List<String> dividedCommaList){
        return dividedCommaList.stream().map(this::inputToWootechMenu).toList();
    }


    // TODO: 생성자로 변경 만들어도 좋을 듯. 지금 메뉴를 갖고 있는 객체 이제 여기서 계산하는거지
    public WootecoMenu inputToWootechMenu(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        return new WootecoMenu(dividedHyphen[0], parseInt(dividedHyphen[1]));
    }

    private void validateElementBlank(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        if (dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank()){
            new CustomException("주문");
        }
    }

    private void validateNotHypen(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
//        System.out.println(Arrays.toString(dividedHyphen));
        if (dividedHyphen.length != 2){
            new CustomException("주문");
        }
    }

    private void validateNumber(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        try{
            parseInt(dividedHyphen[1]);
        } catch (NumberFormatException e){
            new CustomException("주문");
        }
    }

    private void validateInMenu(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        if (!menuboard.contains(dividedHyphen[0])){
            new CustomException("주문");
        }
    }

    private void validateBlank(List<String> divitedCommaList) {
        int blankCount = (int)divitedCommaList.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            new CustomException("주문");
        }
    }

    private void validateDuplicated(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(s -> s.split("-")[0]).collect(Collectors.toSet()).size();
        if (cnt != dividedCommaList.size()){
            new CustomException("주문");
        }
    }

    private void validateTotalCount(List<String> dividedCommaList){
        int cnt = dividedCommaList.stream().map(s -> s.split("-")[1]).mapToInt(Integer::parseInt).sum();
        if (cnt > 20){
            new CustomException("주문");
        }
    }

}
