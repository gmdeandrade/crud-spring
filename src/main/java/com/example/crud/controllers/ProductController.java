package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductPost;
import com.example.crud.domain.product.RequestProductPut;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid RequestProductPost data){
        repository.save(new Product(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateProduct(@RequestBody @Valid RequestProductPut data){
        Optional<Product> optProduct = repository.findById(data.id());
        if (optProduct.isPresent()){
            Product product = optProduct.get();
            if(data.name() != null)
                product.setName(data.name());
            if(data.price_in_cents() != null)
                product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        Optional<Product> optProduct = repository.findById(id);
        if (optProduct.isPresent()){
            Product product = optProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
