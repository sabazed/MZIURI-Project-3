package com.mziuri.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Classes.Product;
import com.mziuri.Requests.AddProductRequest;
import com.mziuri.Requests.PurchaseRequest;
import com.mziuri.Responses.AddProductResponse;
import com.mziuri.Responses.GetProductInfoResponse;
import com.mziuri.Responses.PurchaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/store/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer amount= Integer.parseInt(req.getParameter("amount"));
        System.out.println(name);
        System.out.println(amount);
        PurchaseRequest request=new PurchaseRequest(name,amount);
        PurchaseResponse response=new PurchaseResponse(request);
        if (response.purchase()){
            resp.sendError(200);
        }else {
            resp.sendError(405);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        GetProductInfoResponse response=new GetProductInfoResponse(name);

        if (response.exists()){
            resp.setContentType("application/json");
            Product product=response.getProduct();
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(product);
            System.out.println(json);
            resp.getWriter().write(json);
        }else {
            resp.sendError(405);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password=req.getParameter("password");
        String name=req.getParameter("name");
        Integer amount=Integer.parseInt(req.getParameter("amount"));
        AddProductRequest request=new AddProductRequest(password,name,amount);
        AddProductResponse response= new AddProductResponse(request);
        int a=response.addProduct();
        if (a==1){
            resp.sendError(405);
        } else if (a==2) {
            resp.sendError(403);
        }else {
            resp.sendError(200);
        }
    }
}
