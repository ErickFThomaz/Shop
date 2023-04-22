package com.shop.category;

import com.shop.core.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Relation(value = "category", collectionRelation = "categories")
public class Category extends AuditableEntity {

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	private String name;

}
