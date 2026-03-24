package com.mandy.springbootmall.controller;

import com.mandy.springbootmall.constant.ProductCategory;
import com.mandy.springbootmall.dto.ProductQueryParam;
import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
        public ResponseEntity<List<Product>> getProducts(
                @RequestParam(required = false) ProductCategory category,
                @RequestParam(required = false) String search,
                @RequestParam(required = false,defaultValue = "create_date") String orderBy,
                @RequestParam(required = false, defaultValue = "desc") String sort
    ){
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setCategory(category);
        productQueryParam.setSearch(search);
        productQueryParam.setSort(sort);
        productQueryParam.setOrderBy(orderBy);
        List<Product> productList = productService.getProducts(productQueryParam);

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
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
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteroduct(@PathVariable Integer productId){
        Product product =  productService.getProductById(productId);
        if(productId == null || product ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body("刪除成功");
        }
    }
}
