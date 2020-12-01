package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/order/delete/item", loadOnStartup = 8)
public class CartItemDeleteApi extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShoppingCartDaoMem cartDaoMem = ShoppingCartDaoMem.getInstance();
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDaoMem.find(id);
        cartDaoMem.deleteItem(product);
    }
}