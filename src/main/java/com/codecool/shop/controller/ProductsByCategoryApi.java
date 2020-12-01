package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.List;

@WebServlet(name = "productsByCategoryApi", urlPatterns = "/api/category", loadOnStartup = 2)
public class ProductsByCategoryApi extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      int categoryId = Integer.parseInt(request.getParameter("id"));

      ProductDao productDataStore = ProductDaoMem.getInstance();
      ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
      ProductCategory category = productCategoryDataStore.find(categoryId);
      List<Product> products = productDataStore.getBy(category);
      Gson gson = new GsonBuilder()
              .excludeFieldsWithModifiers(Modifier.TRANSIENT)
              .setPrettyPrinting()
              .create();
      String fullJson = gson.toJson(products);
      out.println(fullJson);
   }
}
