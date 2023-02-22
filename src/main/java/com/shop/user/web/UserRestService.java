package com.shop.user.web;

import com.shop.core.ShopMessageSource;
import com.shop.user.User;
import com.shop.user.UserCommand;
import com.shop.user.UserQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/users")
public class UserRestService {

	private final ShopMessageSource messageSource;

	private final UserCommand command;

	private final UserQuery userQuery;

	private final PagedResourcesAssembler<User> pagedResourcesAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> create(@RequestBody @Valid UserResource user) {
		System.out.println(messageSource.getMessage("validation.error"));
		return ResponseEntity.ok(command.create(User.of(user)));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PagedModel<EntityModel<User>> findAll(
			@SortDefault.SortDefaults({ @SortDefault(sort = "createdAt", direction = DESC) }) Pageable pageable) {
		return pagedResourcesAssembler.toModel(userQuery.findAll(pageable));
	}

}
