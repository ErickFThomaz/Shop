package com.shop.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.core.AuditableEntity;
import com.shop.user.web.UserResource;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.shop.core.BeanUtil.getBean;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PRIVATE)
@Relation(value = "user", collectionRelation = "users")
public class User extends AuditableEntity {

	@Id
	@Email
	@NotEmpty
	private String username;

	private String governmentId;

	@NotEmpty
	@NotNull
	private String name;

	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private UserType type;

	private String phone;

	private String cellPhone;

	@Version
	@JsonIgnore
	private Long version;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.INACTIVE;

	public static User of(UserResource resource) {
		BCryptPasswordEncoder encoder = getBean(BCryptPasswordEncoder.class);

		return User.builder().username(resource.getUsername()).name(resource.getName())
				.password(encoder.encode(resource.getPassword())).type(resource.getType()).phone(resource.getPhone())
				.cellPhone(resource.getCellPhone()).build();
	}

	public boolean isActive() {
		return status == UserStatus.ACTIVE;
	}

}
