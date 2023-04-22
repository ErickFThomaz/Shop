package com.shop.system.security;

import com.shop.core.ShopMessageSource;
import com.shop.system.security.resource.AuthenticationRequest;
import com.shop.system.security.resource.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationCommand {

	private final ShopMessageSource messageSource;

	private final JwtTokenService jwtTokenService;

	private final JwtUserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationCommand.class);

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),
					authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException ex) {
			logger.error("Erro ao tentar fazer autenticação", ex);
			throw new com.shop.core.exception.BadCredentialsException(messageSource.getMessage("authentication.error"));
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

		if (!userDetails.isEnabled()) {
			throw new com.shop.core.exception.BadCredentialsException(messageSource.getMessage("user.not.active"));
		}

		return jwtTokenService.generateToken(userDetails);
	}

}
