package com.mziuri.Responses;

import com.mziuri.Classes.Product;
import com.mziuri.Service.StorageReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor
public class GetProductInfoResponse {
    private int id;
    private String name;
    private Float price;
    private Integer amount;

    public GetProductInfoResponse(String name) throws IOException {
        this.name = name;
        StorageReader reader=StorageReader.getReader();
        for (Product product:reader.getConfig().getProducts()){
            if (Objects.equals(product.getName(), name)){
                this.amount=product.getAmount();
                this.price=product.getPrice();
                this.id= product.getID();
                break;
            }
        }
    }
    public Product getProduct(){
        return new Product(id,name,price,amount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public boolean exists(){
        return !(price==null);
    }

    public Float getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }
}
