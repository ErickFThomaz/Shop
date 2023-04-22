package com.shop.product;

import com.shop.core.exception.ProductNotFoundException;
import com.shop.core.exception.UserConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductCommand {

	private final ProductQuery query;

	private final ProductRepository repository;

	public Product create(Product product) {

		if(query.findByName(product.getName()).isPresent()){
			throw new UserConflictException("Já existe um produto com esse nome");
		}

		return repository.save(product);
	}

	public void delete(int id) {
		Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("O produto não existe"));
		repository.delete(product);
	}

}
