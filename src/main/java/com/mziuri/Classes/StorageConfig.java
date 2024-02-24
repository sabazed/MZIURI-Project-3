package com.mziuri.Classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Classes.Product;
import com.mziuri.Service.DatabaseManager;
import com.mziuri.Service.StorageReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StorageConfig {
    private Product[] products;
    private String password;

    public StorageConfig(@JsonProperty("password") String password, @JsonProperty("products") Product[] products) {
        this.password = password;
        this.products = products;
    }
    public void updateAmount(String name,int newAmount) throws IOException {
        for (int i=0;i<password.length();i++){
            if (products[i].getName().equals(name)){
                products[i].setAmount(newAmount);
            }
        }

        ObjectMapper mapper=new ObjectMapper();
        mapper.writer(new DefaultPrettyPrinter()).writeValue(new File("src/main/resources/storage.json"),this);

        StorageReader.update();
    }
    public StorageConfig(String password){
        this.password=password;
        DatabaseManager manager=new DatabaseManager();
        this.products= manager.findProducts().toArray(new Product[0]);
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
