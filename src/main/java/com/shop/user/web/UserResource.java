package com.shop.user.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.user.UserStatus;
import com.shop.user.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PRIVATE)
public class UserResource {

	@Email
	@NotEmpty
	private String username;

	private String governmentId;

	private String name;

	private String password;

	@Enumerated(EnumType.STRING)
	private UserType type;

	private String phone;

	private String cellPhone;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.INACTIVE;

}
