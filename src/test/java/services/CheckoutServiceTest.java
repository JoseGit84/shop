package services;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

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
    public void testScanItemList_WithMoreThanOneItems() {
        assertEquals(new BigDecimal("1.45"),
                checkoutService.checkoutItems(Arrays.asList("Apple", "Orange", "Apple")));
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