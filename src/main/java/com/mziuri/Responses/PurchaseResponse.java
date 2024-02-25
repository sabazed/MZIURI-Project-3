package com.mziuri.Responses;

import com.mziuri.Classes.Product;
import com.mziuri.Requests.PurchaseRequest;
import com.mziuri.Service.DatabaseManager;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class PurchaseResponse {
    private String name;
    private Integer remainingAmount;
    Product product;
    private boolean valid;
    public PurchaseResponse(PurchaseRequest request) throws IOException {
        this.name = request.getName();
        GetProductInfoResponse info=new GetProductInfoResponse(name);
        valid = info.exists();
        if (valid) {
            this.product = info.getProduct();
            remainingAmount = product.getAmount() - request.getAmount();
        }
    }
    public boolean purchase() throws IOException {
        if (!valid()){
            return false;
        }else {
            DatabaseManager manager=new DatabaseManager();
            manager.updateAmount(name,remainingAmount);
            return true;
        }
    }
    public boolean valid(){
        return valid && remainingAmount>=0;
    }

    public String getName() {
        return name;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }
}
