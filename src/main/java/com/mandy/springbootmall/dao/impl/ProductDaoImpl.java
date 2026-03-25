package com.mandy.springbootmall.dao.impl;

import com.mandy.springbootmall.constant.ProductCategory;
import com.mandy.springbootmall.dao.ProductDao;
import com.mandy.springbootmall.dto.ProductQueryParam;
import com.mandy.springbootmall.dto.ProductRequest;
import com.mandy.springbootmall.dto.ProductUpdateDto;
import com.mandy.springbootmall.model.Product;
import com.mandy.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId){
        String sql = "SELECT product_id, product_name,category, image_url,price, stock, description," +
                "created_date,last_modified_date " +
                "FROM product WHERE product_id = :productId";

        Map<String, Object> map =  new HashMap();
        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        if(productList.size() >0){
            return productList.get(0);
        }else{
            return null;
        }

    }
    @Override
    public Integer createProduct(ProductRequest productRequest){
        String sql = "INSERT INTO product (" +
                "product_name, category, " +
                "image_url, " +
                "price, " +
                "stock," +
                "description," +
                "created_date, " +
                "last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map =  new HashMap();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().name());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int productId = keyHolder.getKey().intValue();

        return  productId;

    }
    public void updateProduct(ProductUpdateDto productUpdateDto){
        String sql = "UPDATE product" +
                " SET" +
                " product_name = :productName," +
                " category = :category," +
                " image_url = :imageUrl," +
                " price = :price," +
                " stock = :stock," +
                " description = :description," +
                " last_modified_date = :lastModifiedDate" +
                " WHERE product_id = :productId";

        Map<String, Object> map =  new HashMap();
        map.put("productId",productUpdateDto.getProductId());
        map.put("productName",productUpdateDto.getProductName());
        map.put("category",productUpdateDto.getCategory().name());
        map.put("imageUrl",productUpdateDto.getImageUrl());
        map.put("price",productUpdateDto.getPrice());
        map.put("stock",productUpdateDto.getStock());
        map.put("description",productUpdateDto.getDescription());
        Date now = new Date();
        map.put("lastModifiedDate",now);

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map));


    }
    public void deleteProduct(Integer productId){
        String sql ="DELETE FROM product " +
                "WHERE product_id = :productId";

        Map<String, Object> map =  new HashMap();
        map.put("productId",productId);

    }
    public List<Product> getProducts(ProductQueryParam productQueryParam){
        String sql ="SELECT product_id, product_name,category, image_url,price, stock, description," +
                "created_date,last_modified_date From product WHERE 1=1"  ;

        Map<String, Object> map =  new HashMap();
// 查詢條件
        sql = addFilteringSql(sql,map,productQueryParam);
//       排序
        sql = sql +" ORDER BY " +productQueryParam.getOrderBy() +" "+ productQueryParam.getSort();
//        分頁
        sql = sql +" LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParam.getLimit());
        map.put("offset",productQueryParam.getOffset());
        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        return productList;

    }

    @Override
    public Integer countProduct(ProductQueryParam productQueryParam) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String, Object> map =  new HashMap();
// 查詢條件
        sql = addFilteringSql(sql,map,productQueryParam);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
        return total;
    }

    private String addFilteringSql(String sql, Map<String,Object> map, ProductQueryParam productQueryParam){
        if(productQueryParam.getCategory() != null){
            sql = sql + " AND category = :category";
            map.put("category" ,productQueryParam.getCategory().name());
        }
        if(productQueryParam.getSearch() != null){
            sql = sql + " AND product_name like :productName";
            map.put("productName" ,"%"+ productQueryParam.getSearch()+"%");
        }
        return sql;
    }
}
