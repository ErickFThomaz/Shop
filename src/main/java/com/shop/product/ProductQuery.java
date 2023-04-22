package com.shop.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductQuery {

	private final ProductRepository repository;

	public Page<Product> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}

	public Optional<Product> findByName(String name) {
		return repository.findByName(name);
	}

}
