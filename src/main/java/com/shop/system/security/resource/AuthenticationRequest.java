package com.shop.system.security.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

	@Email
	@NotNull
	@Size(max = 255)
	private String login;

	@NotNull
	@Size(max = 255)
	private String password;

}
