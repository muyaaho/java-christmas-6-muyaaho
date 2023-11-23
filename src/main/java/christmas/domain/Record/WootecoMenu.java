package christmas.domain.Record;

public record WootecoMenu(String name, int count, String category) {
    @Override
    public String toString() {
        return name +" " + count+"개";
    }
}
