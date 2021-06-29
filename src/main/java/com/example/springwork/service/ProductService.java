package com.example.springwork.service;

import java.util.List;

import com.example.springwork.dao.mapper.ProductMapper;
import com.example.springwork.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getProductById(Long id) {

        return productMapper.selectProductById(id);
    }

    public List<Product> getAllProducts() {

        return productMapper.selectAllProducts();
    }

    @Transactional
    public void addProduct(Product product) {

        productMapper.insertProduct(product);
    }
}