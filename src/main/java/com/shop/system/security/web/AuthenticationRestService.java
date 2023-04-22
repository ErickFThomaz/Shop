package com.shop.system.security.web;

import com.shop.system.security.AuthenticationCommand;
import com.shop.system.security.resource.AuthenticationRequest;
import com.shop.system.security.resource.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationRestService {

	private final AuthenticationCommand authenticationCommand;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/v1/api/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody @Valid AuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok(authenticationCommand.authenticate(authenticationRequest));
	}

}
