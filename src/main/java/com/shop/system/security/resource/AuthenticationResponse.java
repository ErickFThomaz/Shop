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

	private Instant expriesIn;

	public static AuthenticationResponse of(String accessToken, Instant expriesIn) {
		return AuthenticationResponse.builder().accessToken(accessToken).expriesIn(expriesIn).build();
	}

}
