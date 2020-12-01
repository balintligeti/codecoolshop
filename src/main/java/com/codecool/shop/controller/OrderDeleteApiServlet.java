package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "orderDeleteApiServlet", urlPatterns = "/api/order/delete", loadOnStartup = 5)
public class OrderDeleteApiServlet extends HttpServlet {


   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response){
      ShoppingCartDaoMem cartData = ShoppingCartDaoMem.getInstance();
      cartData.deleteOrder();
   }

}