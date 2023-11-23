package christmas.domain.Enum;

public enum Badge {

    MORE_20000(20_000, "산타"),
    MORE_10000(10_000, "트리"),
    MORE_5000(5_000, "별"),
    NONE(0, "없음");

    private final int price;
    private final String badge;

    Badge(int price, String badge) {
        this.price = price;
        this.badge = badge;
    }

    public int getPay() {
        return price;
    }

    public String getBadge() {
        return badge;
    }
}
