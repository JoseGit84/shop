package domain;

/**
 * Represents a Buy x, Take y offer
 */
public class Offer {

    private String item;
    private long buy;
    private long take;

    public Offer(String item, long buy, long take) {
        this.item = item;
        this.buy = buy;
        this.take = take;
    }

    public String getItem() {
        return item;
    }

    public long getBuy() {
        return buy;
    }

    public long getTake() {
        return take;
    }
}
