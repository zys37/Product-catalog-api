package com.assessment.product_catalog_api.repository;

import com.assessment.product_catalog_api.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"producer", "attributes"})
    @Query("SELECT p FROM Product p") // To rozwiązuje problem - mówimy wprost: pobierz wszystkie produkty
    List<Product> findAllWithProducerAndAttributes();

    List<Product> findByNameContainingIgnoreCase(String name);
}