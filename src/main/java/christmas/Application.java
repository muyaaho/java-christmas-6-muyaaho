package christmas;

import christmas.Controller.Input.Validate;
import christmas.View.InputView;
import christmas.View.OutputView;

public class Application {
    public static void main(String[] args) {
        Process process = new Process(new InputView(), new OutputView(), new Validate());
        process.run();

    }
}
