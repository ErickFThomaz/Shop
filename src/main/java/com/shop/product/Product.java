package com.shop.product;

import com.shop.category.Category;
import com.shop.core.AbstractEntity;
import com.shop.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
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
@Relation(value = "product", collectionRelation = "products")
public class Product extends AbstractEntity<Product> {

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	private String name;

	private String description;

	private String image;

	private BigDecimal value;

	private BigDecimal discount;

	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;

	@ManyToOne
	private User user;

}
