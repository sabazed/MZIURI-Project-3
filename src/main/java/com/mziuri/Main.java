package com.mziuri;

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
        DatabaseManager manager=new DatabaseManager();
        manager.setup();

        StorageReader reader=StorageReader.getReader();
        System.out.println(Arrays.toString(new GetProductsResponse(reader.getConfig()).getProductNames()));
        AddProductResponse request=new AddProductResponse(new AddProductRequest("asd","Java",10));
        System.out.println(request.addProduct());
//        PurchaseResponse request=new PurchaseResponse(new PurchaseRequest("Java",10));
//        System.out.println(request.purchase());
    }
}
