package com.mandy.springbootmall.service;

import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);
    List<Product> getProducts();
    Integer createProduct(ProductRequest productRequest);
    Integer updateProduct(ProductUpdateDto productUpdateDto);
    void deleteProduct(Integer productId);

}
