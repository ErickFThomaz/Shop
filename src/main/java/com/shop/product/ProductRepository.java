package com.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	Optional<Product> findByName(String name);

}