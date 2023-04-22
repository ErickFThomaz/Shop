package com.shop.product;

import com.shop.category.Category;
import com.shop.core.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

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
public class Product extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private String image;

	private BigDecimal value;

	private BigDecimal discount;

	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;

	private String shopId;

}
