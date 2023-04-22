package com.shop.user;

import com.shop.core.ShopMessageSource;
import com.shop.core.exception.UserConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserCommand {

	private final ShopMessageSource source;

	private final UserRepository repository;

	public User create(User user) {
		repository.findById(user.getUsername()).ifPresent(user1 -> {
			throw new UserConflictException(source.getMessage("user.conflict"));
		});

		// todo:disparo de email para ativação.
		return repository.save(user);
	}

}
