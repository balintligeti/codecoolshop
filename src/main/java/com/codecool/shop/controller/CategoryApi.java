package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
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
import java.util.Locale;

@WebServlet(name = "categoryApiServlet", urlPatterns = "/api/categories", loadOnStartup = 2)
public class CategoryApi extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
      List<ProductCategory> categories = productCategoryDataStore.getAll();
      Gson gson = new GsonBuilder()
              .excludeFieldsWithModifiers(Modifier.TRANSIENT)
              .setPrettyPrinting()
              .create();
      String fullJson = gson.toJson(categories);
      out.println(fullJson);
   }
}
