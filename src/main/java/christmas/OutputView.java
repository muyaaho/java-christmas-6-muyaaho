package christmas;

import java.util.stream.DoubleStream;

public class OutputView {

    public void printOrderedMenu(){
        System.out.println();
        System.out.println("<주문 메뉴>");
    }

    public void printBeforeDiscount(int pay){
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(String.format("%,d", pay)+"원\n");
    }

    public void printGift(String gift){
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(gift);
    }

    public void printBenefitList(){
        System.out.println();
        System.out.println("<혜택 내역>");
    }

    public void printTotalBeneift(int benefit){
        System.out.println();
        System.out.println("<총혜택 금액>");
        // TODO : 함수 만들어서 리턴하도록 하셈 아우귀찬ㅇ하 outputController 만들자
        if (benefit > 0){
            System.out.println(String.format("-%,d",benefit)+"원");    
        }else{
            System.out.println("없음");
        }
        
    }

    public void printFinalCost(int cost){
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        // TODO: 중복되는 함수 합치자, output controller 쓰던가
        System.out.println(String.format("%,d",cost)+"원");
    }

    public void printBadge(String badge){
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

    public void printString(String toString){
        System.out.println(toString);
    }

}
