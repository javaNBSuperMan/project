package com.example.project.mapper;

import com.example.project.model.ProductsImage;

import java.util.List;

public interface ProductsImageMapper {
    public Integer save(ProductsImage productsImage);
    public List<ProductsImage> getAll();
    public List<ProductsImage> getAllByProductsid(String pid);
    public Integer deleteByProductsid(String id);
}
