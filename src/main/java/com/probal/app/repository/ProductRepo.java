package com.probal.app.repository;

import com.probal.app.dto.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepo {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void save(Product product) {
        String sql = "INSERT INTO product(name) VALUES (?)";
        Object[] args = {product.getName()};
        jdbcTemplate.update(sql, args);
        System.out.println("Product saved . . ");
    }

    public Long findMaxId() {
        String sql = "SELECT MAX(id) FROM product";
        return jdbcTemplate.queryForObject(sql, Long.class);

    }
}
