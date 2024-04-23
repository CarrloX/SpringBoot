package com.riwi.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.products.entitys.Product;
import com.riwi.products.services.service_abstract.IProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>>getAll(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product>get(@PathVariable Long id){
        return ResponseEntity.ok(this.productService.findById(id)); 
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product objProduct){
        return ResponseEntity.ok(this.productService.save(objProduct));
    }

    @PutMapping(path = "{/id}")
    public ResponseEntity<Product> update(
        @PathVariable Long id,//path variable porque el id viene por URL
        @RequestBody Product product)//request body porque los datos vienen por el cuerpo de la peticion
        {return ResponseEntity.ok(this.productService.update(id, product));}
}
