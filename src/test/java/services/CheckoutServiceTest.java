package services;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CheckoutServiceTest {

    private CheckoutService checkoutService = new CheckoutService();

    @Test
    public void testScanItemList_WithNoItems() {
        assertEquals(BigDecimal.ZERO, checkoutService.checkoutItems(Collections.emptyList()));
    }

    @Test
    public void testScanItemList_WithOneItem() {
        assertEquals(new BigDecimal(".60"), checkoutService.checkoutItems(Collections.singletonList("Apple")));
    }

    @Test
    public void testScanItemList_WithMoreThanItem_And_NoApplicableOffer() {
        assertEquals(new BigDecimal("0.85"),
                checkoutService.checkoutItems(Arrays.asList("Apple", "Orange")));
    }

    @Test
    public void testScanItemList_WithBuyOneTakeOneApple() {
        assertEquals(new BigDecimal("0.85"),
                checkoutService.checkoutItems(Arrays.asList("Apple", "Orange", "Apple")));
    }

    @Test
    public void testScanItemList_WithBuyThreeTakeTwoOranges() {
        assertEquals(new BigDecimal("1.10"),
                checkoutService.checkoutItems(Arrays.asList("Orange", "Orange", "Apple", "Orange")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScanItemList_WithNoneExistingItem() {
        checkoutService.checkoutItems(Arrays.asList("Watermelon", "Orange", "Apple"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScanItemList_WithNullList() {
        checkoutService.checkoutItems(null);
    }

}