package com.shop.system.security.web;

import com.shop.core.ShopMessageSource;
import com.shop.system.security.JwtTokenService;
import com.shop.system.security.JwtUserDetailsService;
import com.shop.system.security.resource.AuthenticationRequest;
import com.shop.system.security.resource.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationRestService {

	private final ShopMessageSource messageSource;

	private final JwtTokenService jwtTokenService;

	private final JwtUserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private final static Logger logger = LoggerFactory.getLogger(AuthenticationRestService.class);

	@PostMapping("/v1/api/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),
					authenticationRequest.getPassword()));
		}
		catch (final BadCredentialsException ex) {
			logger.error("Erro ao tentar fazer autenticação", ex);
			throw new com.shop.core.exception.BadCredentialsException(messageSource.getMessage("authentication.error"));
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

		if (!userDetails.isEnabled()) {
			throw new com.shop.core.exception.BadCredentialsException(messageSource.getMessage("user.not.active"));
		}

		return ResponseEntity.ok(jwtTokenService.generateToken(userDetails));
	}

}
