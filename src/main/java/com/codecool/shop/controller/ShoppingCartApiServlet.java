package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingCartApiServlet", urlPatterns = "/api/cart", loadOnStartup = 4)
public class ShoppingCartApiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("application/json");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ShoppingCartDaoMem cartData = ShoppingCartDaoMem.getInstance();
        int itemId = Integer.parseInt(request.getParameter("product_id"));
        ProductDaoMem productData = ProductDaoMem.getInstance();
        Product newCartItem = productData.find(itemId);
        cartData.addToCart(newCartItem);
        out.println("Added following item to cart: \n \n " +  newCartItem );

    }
}
