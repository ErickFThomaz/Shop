package com.shop.carrier;

import com.shop.core.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PRIVATE)
@Relation(value = "carrier", collectionRelation = "carriers")
public class Carrier extends AuditableEntity {

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	private String externalId;

	@NotEmpty
	private String name;

	private String street;

	private String city;

	private String state;

	private String site;

}
