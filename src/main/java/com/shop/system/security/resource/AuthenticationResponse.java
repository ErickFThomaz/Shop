package com.shop.system.security.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

	private String accessToken;

	private Instant expiresIn;

	public static AuthenticationResponse of(String accessToken, Instant expiresIn) {
		return AuthenticationResponse.builder().accessToken(accessToken).expiresIn(expiresIn).build();
	}

}
