package christmas;

public class Application {
    public static void main(String[] args) {
        Process process = new Process(new InputView(), new OutputView());
        process.run();

    }
}
