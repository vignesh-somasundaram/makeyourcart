package com.sbcomplete.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sbcomplete.application.model.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

    @Query("select p FROM Product p WHERE productName LIKE %?1%")
    List<Product> findByProductName(String name);


    List<Product> findByavailability(String availablity);
}
