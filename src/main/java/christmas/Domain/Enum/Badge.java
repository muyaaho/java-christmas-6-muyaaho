package christmas.Domain.Enum;

public enum Badge {

    MORE_20000(20_000, "산타"),
    MORE_10000(10_000, "트리"),
    MORE_5000(5_000, "별"),
    NONE(0, "없음");

    private int pay;
    private String badge;

    Badge(int pay, String badge) {
        this.pay = pay;
        this.badge = badge;
    }

    public int getPay() {
        return pay;
    }

    public String getBadge() {
        return badge;
    }
}
