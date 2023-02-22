package com.shop.system.security;

import com.shop.core.ShopMessageSource;
import com.shop.user.User;
import com.shop.user.UserQuery;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserQuery query;

	private final ShopMessageSource messageSource;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		final User client = query.findUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(messageSource.getMessage("not.found")));

		return com.shop.system.security.UserDetails.of(client);
	}

}
