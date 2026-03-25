package com.mandy.springbootmall.dao;

import com.mandy.springbootmall.dto.ProductQueryParam;
import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParam productQueryParam);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    Integer updateProduct(ProductUpdateDto productUpdateDto);
    void deleteProduct(Integer productId);
    Integer countProduct(ProductQueryParam productQueryParam);
}
