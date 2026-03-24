package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

}
