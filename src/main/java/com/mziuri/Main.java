package com.mziuri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Requests.AddProductRequest;
import com.mziuri.Requests.PurchaseRequest;
import com.mziuri.Responses.AddProductResponse;
import com.mziuri.Responses.GetProductsResponse;
import com.mziuri.Responses.PurchaseResponse;
import com.mziuri.Service.DatabaseManager;
import com.mziuri.Service.StorageReader;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        DatabaseManager manager=new DatabaseManager();
//        manager.setup();
//
//        StorageReader reader=StorageReader.getReader();
//        System.out.println(Arrays.toString(new GetProductsResponse().getProductNames()));
//        AddProductResponse request=new AddProductResponse(new AddProductRequest("asd","Java",10));
//        System.out.println(request.addProduct());
//        PurchaseResponse request=new PurchaseResponse(new PurchaseRequest("Java",10));
//        System.out.println(request.purchase());


//        String[] productNames=new GetProductsResponse().getProductNames();
//        ObjectMapper mapper=new ObjectMapper();
//        String response=mapper.writeValueAsString(productNames);
//        System.out.println(response);
//        String resp=mapper.writeValueAsString(StorageReader.getReader().getConfig());
//        System.out.println(resp);

//        String[] productNames=new GetProductsResponse().getProductNames();
//        String response= Arrays.toString(productNames);
//        response=response.substring(1,response.length()-1);
//        System.out.println(response);
    }
}
