package main;

import services.CheckoutService;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        CheckoutService checkoutService = new CheckoutService();
        BigDecimal totalPrice = checkoutService.checkoutItems(Arrays.asList("Apple", "Apple", "Orange", "Apple"));
        System.out.println("Total price: £" + totalPrice);
    }
}
