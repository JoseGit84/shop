package services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutService {

    private static final Map<String, BigDecimal> prices = initMap();

    private static Map<String, BigDecimal> initMap() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("Apple", new BigDecimal(".60"));
        map.put("Orange", new BigDecimal(".25"));
        return Collections.unmodifiableMap(map);
    }

    public BigDecimal checkoutItems(List<String> items) {
        if (isValidItems(items)) {
            return items.stream().map(prices::get).reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            throw new IllegalArgumentException("Please provide a valid list of items");
        }
    }

    private boolean isValidItems(List<String> items) {
        return items != null && items.stream().allMatch(prices::containsKey);
    }
}
