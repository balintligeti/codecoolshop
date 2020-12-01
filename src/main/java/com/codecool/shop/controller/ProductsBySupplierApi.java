package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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

@WebServlet(name = "productsBySupplierApi", urlPatterns = "/api/supplier", loadOnStartup = 2)
public class ProductsBySupplierApi extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      int supplierId = Integer.parseInt(request.getParameter("id"));
      ProductDao productDataStore = ProductDaoMem.getInstance();
      SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
      Supplier supplier = supplierDaoMem.find(supplierId);
      List<Product> products = productDataStore.getBy(supplier);
      Gson gson = new GsonBuilder()
              .excludeFieldsWithModifiers(Modifier.TRANSIENT)
              .setPrettyPrinting()
              .create();
      String fullJson = gson.toJson(products);
      out.println(fullJson);
   }
}