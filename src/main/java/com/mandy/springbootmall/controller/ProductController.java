package com.mandy.springbootmall.controller;

import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId =  productService.createProduct(productRequest);
        Product product =  productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto){

        Integer productId = productUpdateDto.getProductId();
        Product product =  productService.getProductById(productId);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            productService.updateProduct(productUpdateDto);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }
}
