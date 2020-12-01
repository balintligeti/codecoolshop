package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public interface ShoppingCartDao {
    void addToCart(Product product);
    Order getOrder();
    void deleteOrder();
    void decrementItem(Product product);
    void deleteItem(Product product);
}
