package com.example.springwork.dao.mapper;

import java.util.List;

import com.example.springwork.domain.Product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    Product selectProductById(Long id);
    List<Product> selectAllProducts();
    void insertProduct(Product product);
}