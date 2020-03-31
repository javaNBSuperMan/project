package com.example.project.mapper;

import com.example.project.model.Products;

import java.util.List;

public interface ProductsMapper {

    public List<Products> getAll();
    public Products getProductsById(String id);
    public Products getProductsMax();
    public Integer save(Products products);
    public Integer update(Products products);
    public Integer delete(String id);
}
