package com.shop.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserQuery {

	private final UserRepository repository;

	public Optional<User> findUserByEmail(String email) {
		return repository.findById(email);
	}

	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
