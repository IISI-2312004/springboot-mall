package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    Integer updateProduct(ProductUpdateDto productUpdateDto);
    void deleteProduct(Integer productId);
}
