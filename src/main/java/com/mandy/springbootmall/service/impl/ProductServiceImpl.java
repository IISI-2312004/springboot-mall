package com.mandy.springbootmall.service.impl;

import com.mandy.springbootmall.dao.ProductDao;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
