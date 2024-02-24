package com.mziuri.Responses;


import com.mziuri.Classes.StorageConfig;
import com.mziuri.Requests.AddProductRequest;
import com.mziuri.Responses.GetProductInfoResponse;
import com.mziuri.Service.DatabaseManager;
import com.mziuri.Service.StorageReader;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class AddProductResponse {
    private String name;
    private Integer remainingAmount;
    private boolean passwordIsCorrect;
    private boolean productExists;

    public AddProductResponse(AddProductRequest request) throws IOException {
        StorageConfig reader= StorageReader.getReader().getConfig();
        this.name = request.getName();
        this.passwordIsCorrect=reader.getPassword().equals(request.getPassword());
        GetProductInfoResponse productInfo=new GetProductInfoResponse(name);
        this.productExists=productInfo.exists();
        if (productExists) {
            this.remainingAmount = productInfo.getAmount() + request.getAmount();
        }
    }
    public int addProduct() throws IOException {
        if (!productExists){
            return 1;
        } else if (!passwordIsCorrect) {
            return 2;
        }else {
            DatabaseManager manager=new DatabaseManager();
            manager.updateAmount(name,remainingAmount);
            return 3;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public boolean isPasswordIsCorrect() {
        return passwordIsCorrect;
    }

    public boolean isProductExists() {
        return productExists;
    }
}
