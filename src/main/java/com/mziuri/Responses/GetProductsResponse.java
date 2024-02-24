package com.mziuri.Responses;

import com.mziuri.Classes.Product;
import com.mziuri.Classes.StorageConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.el.stream.Stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class GetProductsResponse {
    private String[] productNames;

    public String[] getProductNames() {
        return productNames;
    }

    public GetProductsResponse(StorageConfig products) {
        this.productNames = Arrays.stream(products.getProducts()).map(Product::getName).toArray(String[]::new);
    }
}
