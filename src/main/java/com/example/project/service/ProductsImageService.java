package com.example.project.service;

import com.example.project.model.ProductsImage;

import java.util.List;

public interface ProductsImageService {
    public Integer save(ProductsImage productsImage);
    public List<ProductsImage> getAll();
    public List<ProductsImage> getAllByProductsid(String pid);
    public Integer deleteByProductsid(String id);
}
