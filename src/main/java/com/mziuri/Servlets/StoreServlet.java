package com.mziuri.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Responses.GetProductsResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
//        resp.setContentType("application/json");

        String[] productNames=new GetProductsResponse().getProductNames();
        String response= Arrays.toString(productNames);
        response=response.substring(1,response.length()-1);
        resp.getWriter().write(response);
    }
}
