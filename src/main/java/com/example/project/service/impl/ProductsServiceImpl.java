package com.example.project.service.impl;

import com.example.project.mapper.ProductsMapper;
import com.example.project.model.Products;
import com.example.project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public List<Products> getAll() {
        return productsMapper.getAll();
    }

    @Override
    public Products getProductsById(String id) {
        return productsMapper.getProductsById(id);
    }

    @Override
    public Products getProductsMax() {
        return productsMapper.getProductsMax();
    }

    @Override
    public Integer save(Products products) {
        return productsMapper.save(products);
    }

    @Override
    public Integer update(Products products) {
        return productsMapper.update(products);
    }

    @Override
    public Integer delete(String id) {
        return productsMapper.delete(id);
    }

}
