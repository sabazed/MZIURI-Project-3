package com.mziuri.Classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product")
//@NamedQuery(name = "find product", query = "from product p")
public class Product {
    @Id
    @Column(name = "prod_id")
    private int ID;

    @Column(name = "prod_name", unique = true)
    private String name;

    @Column(name = "prod_price")
    private float price;

    @Column(name = "prod_amount")
    private int amount;

    @JsonCreator
    public Product(@JsonProperty("prod_id") int ID,@JsonProperty("prod_name")  String name,
                   @JsonProperty("prod_price")  float price,@JsonProperty("prod_amount")  int amount) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public Product() {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
