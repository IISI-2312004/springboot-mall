package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
