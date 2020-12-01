package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

@WebServlet(name = "supplierApiServlet", urlPatterns = "/api/suppliers", loadOnStartup = 2)
public class SupplierApi extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
      List<Supplier> suppliers = supplierDaoMem.getAll();
      Gson gson = new GsonBuilder()
              .excludeFieldsWithModifiers(Modifier.TRANSIENT)
              .setPrettyPrinting()
              .create();
      String fullJson = gson.toJson(suppliers);
      out.println(fullJson);
   }
}