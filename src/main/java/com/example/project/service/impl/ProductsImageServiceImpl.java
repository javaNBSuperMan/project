package com.example.project.service.impl;

import com.example.project.mapper.ProductsImageMapper;
import com.example.project.model.ProductsImage;
import com.example.project.service.ProductsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsImageServiceImpl implements ProductsImageService {
    @Autowired
    private ProductsImageMapper productsImageMapper;

    @Override
    public Integer save(ProductsImage productsImage) {
        return productsImageMapper.save(productsImage);
    }

    @Override
    public List<ProductsImage> getAll() {
        return productsImageMapper.getAll();
    }

    @Override
    public List<ProductsImage> getAllByProductsid(String pid) {
        return productsImageMapper.getAllByProductsid(pid);
    }

    @Override
    public Integer deleteByProductsid(String id) {
        return productsImageMapper.deleteByProductsid(id);
    }
}
