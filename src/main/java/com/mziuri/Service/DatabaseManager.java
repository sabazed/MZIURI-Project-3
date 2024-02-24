package com.mziuri.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Classes.Product;
import com.mziuri.Classes.StorageConfig;
import com.mziuri.Responses.GetProductsResponse;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.io.IOException;
import java.util.List;

public class DatabaseManager {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit");

    public void save(Product[] products){
        EntityManager manager= factory.createEntityManager();
        manager.getTransaction().begin();
        for (Product product:products){
            manager.persist(product);
        }
        manager.getTransaction().commit();
        manager.close();
    }
    public void updateAmount(String productName, int amount) throws IOException {
        EntityManager manager= factory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaUpdate<Product> update=builder.createCriteriaUpdate(Product.class);
        Root<Product> root= update.from(Product.class);
        update.set(root.get("amount"),amount).where(builder.equal(root.get("name"),productName));
        manager.createQuery(update).executeUpdate();
        manager.getTransaction().commit();
        manager.close();

        StorageConfig config=StorageReader.getReader().getConfig();
        config.updateAmount(productName,amount);
    }
    public void setup() throws IOException {
        List<Product> products = findProducts();
        if (products.isEmpty()){
            StorageReader reader=StorageReader.getReader();
            save(reader.getConfig().getProducts());
        }
    }
    public List<Product> findProducts(){
        EntityManager manager= factory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder= manager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot=criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);
        CriteriaQuery<Product> select =criteriaQuery.select(productRoot);
        TypedQuery<Product> productTypedQuery=manager.createQuery(select);
        List<Product> products = productTypedQuery.getResultList();
        manager.close();
        return products;
    }
}
