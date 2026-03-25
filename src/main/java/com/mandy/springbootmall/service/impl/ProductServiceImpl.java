package com.mandy.springbootmall.service.impl;

import com.mandy.springbootmall.dao.ProductDao;
import com.mandy.springbootmall.dto.ProductQueryParam;
import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
    public void updateProduct(ProductUpdateDto productUpdateDto) {
         productDao.updateProduct(productUpdateDto);
    }
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }
    public List<Product> getProducts(ProductQueryParam productQueryParam) {
       return productDao.getProducts(productQueryParam);
    }
    public Integer countProduct(ProductQueryParam productQueryParam) {
        return productDao.countProduct(productQueryParam);
    }
}
