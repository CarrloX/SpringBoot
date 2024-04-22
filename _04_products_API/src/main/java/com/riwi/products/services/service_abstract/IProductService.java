package com.riwi.products.services.service_abstract;

import java.util.List;

import com.riwi.products.entitys.Product;

public interface IProductService {

    public Product save(Product product);

    public List<Product> getAll();
    
    public Product findById(Long id);

    public boolean delete(Long id);

    public Product update(Long id,Product obProduct);

    public List<Product> search(String name);
    
}
