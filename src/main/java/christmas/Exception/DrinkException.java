package christmas.Exception;

public class DrinkException extends IllegalArgumentException{
    public DrinkException() {
        super("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요");
    }
}
