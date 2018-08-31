package services;

import domain.Offer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CheckoutService {

    private static final Map<String, BigDecimal> prices = initPriceMap();

    private static final List<Offer> offers = initOfferList();

    private static Map<String, BigDecimal> initPriceMap() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("Apple", new BigDecimal(".60"));
        map.put("Orange", new BigDecimal(".25"));
        return Collections.unmodifiableMap(map);
    }

    private static List<Offer> initOfferList() {
        List<Offer> list = new LinkedList<>();
        list.add(new Offer("Apple", 1, 1));
        list.add(new Offer("Orange", 2, 1));
        return Collections.unmodifiableList(list);
    }

    /**
     * @param items list of items
     * @return total price of the items list
     */
    public BigDecimal checkoutItems(List<String> items) {
        if (isValidItems(items)) {
            List<Offer> applicableOffers = retrieveApplicableOffers(items);
            List<String> payingItems = applyOffers(items, applicableOffers);
            return scanItemList(payingItems);
        } else {
            throw new IllegalArgumentException("Please provide a valid list of items");
        }
    }

    /**
     * @param items list of items
     * @return list of offers that can be applied to the incoming items list
     */
    private List<Offer> retrieveApplicableOffers(List<String> items) {
        List<Offer> applicableOffers = new LinkedList<>();
        Map<String, Long> itemOccurrences = items.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()));
        for (Offer offer : offers) {
            long itemAmount = itemOccurrences.getOrDefault(offer.getItem(), 0L);
            if (itemAmount - offer.getTake() >= offer.getBuy()) {
                applicableOffers.add(offer);
                //update item counter
                itemOccurrences.put(offer.getItem(), itemAmount - offer.getTake());
            }
        }
        return applicableOffers;
    }

    /**
     * @param items  list of items
     * @param offers list of applicable offers
     * @return list of items that has to be paid
     */
    private List<String> applyOffers(List<String> items, List<Offer> offers) {
        List<String> itemsCopy = new LinkedList<>(items);
        for (Offer offer : offers) {
            long takeAmount = offer.getTake();
            for (int i = 0; i < takeAmount; i++) {
                itemsCopy.remove(offer.getItem());
            }
        }
        return itemsCopy;
    }

    private BigDecimal scanItemList(List<String> items) {
        return items.stream().map(prices::get).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isValidItems(List<String> items) {
        return items != null && items.stream().allMatch(prices::containsKey);
    }
}
