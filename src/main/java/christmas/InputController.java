package christmas;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class InputController {
    // 메뉴판 객체가 있다면 입력에 음식이 있는지 리턴하는 함수, 음식 이름에 카테고리가 뭔지 알려주는 함수를 만들 수 있겠죠옹?
    // 아니면 enum으로 만들어서 관리해도되고 이름, 가격, 카테고리
    List<String> menuboard = new ArrayList<>(Arrays.asList("양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"));

    public List<String> seperateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(",", -1));
        validateBlank(dividedCommaList);
        return dividedCommaList;
    }

    public List<WootecoMenu> setOrderedMenu(List<String> dividedCommaList){
        return dividedCommaList.stream().map(this::inputToWootechMenu).toList();
    }


    public WootecoMenu inputToWootechMenu(String inputDividedComma){
        validateNotHypen(inputDividedComma);
        validateNumber(inputDividedComma);
        validateElementBlank(inputDividedComma);
        validateInMenu(inputDividedComma);
        String[] dividedHyphen = inputDividedComma.split("-");
        return new WootecoMenu(dividedHyphen[0], parseInt(dividedHyphen[1]));
    }

    private void validateElementBlank(String inputDividedComma){
//        System.out.println("validateElementBlank: "+ inputDividedComma);
        String[] dividedHyphen = inputDividedComma.split("-");
        System.out.println(Arrays.toString(dividedHyphen));
        if (dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank()){
            new CustomException("주문");
        }
    }

    private void validateNotHypen(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        System.out.println(Arrays.toString(dividedHyphen));
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



}
