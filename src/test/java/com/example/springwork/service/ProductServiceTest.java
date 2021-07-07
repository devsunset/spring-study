package com.example.springwork.service;

import java.util.List;

import com.example.springwork.domain.Product;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    private final static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

    @Autowired
    private ProductService productService;

    @Test
    public void getProductById() {
        Product product = productService.getProductById(1L);
        log.info("product : {}", product);
    }

    @Test
    public void getAllProducts() {
        List<Product> products = productService.getAllProducts();
        log.info("products : {}", products);
    }

    @Transactional
    @Test
    public void addProduct() {
        productService.addProduct(new Product("쿤달샴푸", 7900));
        productService.addProduct(new Product("마스크팩", 1000));
        productService.addProduct(new Product("티셔츠", 5900));
    }

}