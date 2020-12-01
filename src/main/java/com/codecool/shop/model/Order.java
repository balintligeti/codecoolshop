package com.codecool.shop.model;

import java.util.Currency;
import java.util.HashMap;

public class Order {
    private int itemCount;
    private transient float totalPrice;
    private String totalPriceWithCurrency;
    private transient Currency currency;
    private boolean paid;
    private final HashMap<String, Integer> cartItems = new HashMap<>();
    private final HashMap<String, Float> cartPrices = new HashMap<>();
    private final HashMap<String, Integer> itemIds = new HashMap<>();

    public Order() {
        this.itemCount = 0;
        this.totalPrice = (float) 0.0;
        this.totalPriceWithCurrency = "0.0 USD";
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<String, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(String name, int count) {
        this.cartItems.put(name, count);
    }

    public void setItemIds(String name, int id) {
        this.itemIds.put(name, id);
    }

    public void setCartPrices(String name, float price) {
        this.cartPrices.put(name, price);
    }

    public void increaseTotalPrice(float price) {
        this.totalPrice += price;
        setTotalPriceWithCurrency();
    }

    public void setTotalPriceWithCurrency() {
        this.totalPriceWithCurrency = totalPrice + " " + currency.toString();
    }

    public String getTotalPriceWithCurrency() {
        return totalPriceWithCurrency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void increaseItemCount() {
        this.itemCount += 1;
    }

    public void decreaseItemCount(int count) {
        if (itemCount == 0) {
            this.itemCount = 0;
        }
        this.itemCount -= count;
    }

    public void decreaseTotalPrice(float price) {
        if (cartItems.size() == 0) {
            this.totalPrice = 0;
        }  else {
            this.totalPrice -= price;
        }

        setTotalPriceWithCurrency();
    }

    public void decreaseCartItemsCount(String name, int count) {
        this.cartItems.put(name,count);
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void delete(String name) {
        cartItems.remove(name);
        cartPrices.remove(name);
        itemIds.remove(name);
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemCount=" + itemCount +
                ", totalPrice=" + totalPrice +
                ", paid=" + paid +
                ", cartItems=" + cartItems +
                '}';
    }
}
