package org.rash.micro.repository;

import org.rash.micro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByProductId(Integer pid);


    int removeByProductId(Integer pid);


    List<Product> findByProductType(String ptype);

    List<Product> findByProductNameIgnoreCase(String name);

    @Query("FROM Product p WHERE p.productPrice BETWEEN :lowPrice AND :highPrice")
    List<Product> findByProductPrice(@Param("lowPrice") Double lowPrice, @Param("highPrice") Double highPrice);


}
