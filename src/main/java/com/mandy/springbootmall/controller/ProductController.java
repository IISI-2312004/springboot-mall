package com.mandy.springbootmall.controller;

import com.mandy.springbootmall.constant.ProductCategory;
import com.mandy.springbootmall.dto.ProductQueryParam;
import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.service.ProductService;
import com.mandy.springbootmall.utils.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
        public ResponseEntity<Page<Product>> getProducts(
                @RequestParam(required = false) ProductCategory category,
                @RequestParam(required = false) String search,
                @RequestParam(defaultValue = "created_date") String orderBy,
                @RequestParam(defaultValue = "desc") String sort,
                @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setCategory(category);
        productQueryParam.setSearch(search);
        productQueryParam.setSort(sort);
        productQueryParam.setOrderBy(orderBy);
        productQueryParam.setLimit(limit);
        productQueryParam.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParam);

        Integer total = productService.countProduct(productQueryParam);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
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
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
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
            Product updateProduct =  productService.getProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
        }
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteroduct(@PathVariable Integer productId){

            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
