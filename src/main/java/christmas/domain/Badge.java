package christmas.domain;

public enum Badge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final int price;
    private final String badge;

    Badge(String badge, int price) {
        this.price = price;
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }

    public boolean canGetBadge(int benefit) {
        return benefit >= price;
    }
}
