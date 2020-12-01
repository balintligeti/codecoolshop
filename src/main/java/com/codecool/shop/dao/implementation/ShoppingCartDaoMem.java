package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Order;

import java.util.HashMap;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private HashMap<Product, Integer> data = new HashMap<Product, Integer>();
    private static ShoppingCartDaoMem instance = null;
    private Order order = new Order();

    private ShoppingCartDaoMem() {

    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void addToCart(Product product) {
        if (data.containsKey(product)) {
            int currentValue = data.get(product);
            data.put(product, currentValue + 1);
        } else {
            data.put(product, 1);
        }
        int currentValue = data.get(product);
        order.setCurrency(product.getDefaultCurrency());
        order.setCartPrices(product.getName(), product.getDefaultPrice());
        order.setItemIds(product.getName(), product.getId());
        order.setCartItems(product.getName(), currentValue);
        order.increaseTotalPrice(product.getDefaultPrice());
        order.increaseItemCount();
    }

    @Override
    public void deleteOrder() {
        order = new Order();
        data = new HashMap<>();
    }

    @Override
    public void decrementItem(Product product) {
        int count = data.get(product);
        if (count == 1) {
            deleteItem(product);
        } else {
            data.put(product,count-1);
            order.decreaseCartItemsCount(product.getName(),count-1);
            order.decreaseItemCount(1);
            order.decreaseTotalPrice(product.getDefaultPrice());
        }

    }

    @Override
    public void deleteItem(Product product) {
        int count = data.get(product);
        data.remove(product);
        order.delete(product.getName());
        order.decreaseItemCount(count);
        order.decreaseTotalPrice(product.getDefaultPrice()*count);
    }


}
