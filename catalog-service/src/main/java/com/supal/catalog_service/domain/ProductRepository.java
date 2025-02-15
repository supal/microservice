package com.supal.catalog_service.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByCode(String code);
}
