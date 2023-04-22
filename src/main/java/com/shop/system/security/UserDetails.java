package com.shop.system.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.core.AuditableEntity;
import com.shop.user.User;
import com.shop.user.UserStatus;
import com.shop.user.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetails extends AuditableEntity implements org.springframework.security.core.userdetails.UserDetails {

	private String username;

	private String governmentId;

	private String name;

	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private UserType type;

	private String phone;

	private String cellPhone;

	private UserStatus status;

	private Instant createdAt;

	private String createUserId;

	private Instant updatedAt;

	private String lastUserId;

	@Builder.Default
	private List<GrantedAuthority> permissions = new ArrayList<>();

	public static UserDetails of(User user) {
		return UserDetails.builder().username(user.getUsername()).name(user.getName()).password(user.getPassword())
				.cellPhone(user.getCellPhone()).phone(user.getPhone()).status(user.getStatus())
				.createdAt(user.getCreatedAt()).createUserId(user.getCreateUserId()).updatedAt(user.getUpdatedAt())
				.lastUserId(user.getLastUserId()).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return status != UserStatus.BLOCKED;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status == UserStatus.ACTIVE;
	}

}
