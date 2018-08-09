package org.popova.lesson.sweater.servise;

import org.popova.lesson.sweater.domain.Product;
import org.popova.lesson.sweater.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public boolean addProduct(Product product) {
        productRepo.save(product);
        return true;
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }
}
