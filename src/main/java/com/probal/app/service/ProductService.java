package com.probal.app.service;

import com.probal.app.dto.Product;
import com.probal.app.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveProductInfo() {

        for (int i = 0; i < 10; i++) {
            long value = productRepo.findMaxId();
            Product product  = new Product("Product " + (value + 1));
            productRepo.save(product);
        }
    }
}
