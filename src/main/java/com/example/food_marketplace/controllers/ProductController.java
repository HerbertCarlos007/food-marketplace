package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.dto.product.ProductRequestDTO;
import com.example.food_marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Product> create(@RequestParam("name") String name,
                                          @RequestParam(value = "imageUrl", required = false) MultipartFile imageUrl,
                                          @RequestParam("price") double price,
                                          @RequestParam("storeId") UUID storeId,
                                          @RequestParam("status") String status,
                                          @RequestParam("productType") String productType) {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, imageUrl, price, storeId, status, productType);
        Product newProduct = this.productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(newProduct);
    }
}
