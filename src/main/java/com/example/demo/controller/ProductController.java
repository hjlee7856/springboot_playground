package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductSerivce productSerivce;

    public ProductController(ProductSerivce productSerivce) {
        this.productSerivce = productSerivce;
    }

    @GetMapping("/read/{id}") // Read
    public ResponseEntity<?> readUser(@PathVariable String id) {
        Product findProduct = productSerivce.getProductById(Long.parseLong(id));
        if (findProduct == null) {
            return new ResponseEntity<>("message: 일치하는 물건이 없습니다.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findProduct, HttpStatus.OK);
    }
}
