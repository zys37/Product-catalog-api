package com.assessment.product_catalog_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.product_catalog_api.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}

