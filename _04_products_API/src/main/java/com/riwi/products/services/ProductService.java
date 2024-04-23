package com.riwi.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riwi.products.entitys.Product;
import com.riwi.products.repositories.ProductRepository;
import com.riwi.products.services.service_abstract.IProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    @Override
    public Product save(Product Product) {
        return this.productRepository.save(Product);
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean delete(Long id) {
    if (this.productRepository.existsById(id)) {
        this.productRepository.deleteById(id);
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

        return this.productRepository.save(objProduct);
    }

    @Override
    public List<Product> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}
