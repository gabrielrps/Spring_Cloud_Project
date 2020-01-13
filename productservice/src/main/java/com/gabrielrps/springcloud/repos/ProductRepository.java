package com.gabrielrps.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielrps.springcloud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
