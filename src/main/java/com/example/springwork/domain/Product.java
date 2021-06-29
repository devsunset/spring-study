package com.example.springwork.domain;

// import lombok.*;
// import org.apache.ibatis.type.Alias;

// @NoArgsConstructor @RequiredArgsConstructor @Getter @Setter @ToString
public class Product {

    private Long prodId;
    private String prodName;
    private int prodPrice;

    public Product(String prodName, int prodPrice) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public Long getProdId() {
        return this.prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdPrice() {
        return this.prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public String toString() {
      return "Product{" + "prodId=" + this.prodId + ", prodName='" + this.prodName + '\'' + ", prodPrice=" + this.prodPrice + '}';
    }
}