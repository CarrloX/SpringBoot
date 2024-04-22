package com.riwi.products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.products.entitys.Product;
import com.riwi.products.repositories.ProductRepository;
import com.riwi.products.services.service_abstract.IProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository objProductRepository;

    private final ProductRepository productRepository;

    @Override
    public Product save(Product objProduct) {
        return this.productRepository.save(objProduct);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.objProductRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
    if (this.objProductRepository.existsById(id)) {
        this.objProductRepository.deleteById(id);
        return true;
    } else {
        return false;
    }
}

    @Override
    public Product update(Long id,Product objProduct) {
        Product objProductDB = this.findById(id);

        if (objProductDB == null) {
            return null;
        }
        objProductDB = objProduct;

        return this.objProductRepository.save(objProduct);
    }

    @Override
    public List<Product> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}
