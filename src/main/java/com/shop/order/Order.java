package com.shop.order;

import com.shop.carrier.Carrier;
import com.shop.core.AbstractEntity;
import com.shop.payment.Payment;
import com.shop.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
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
@Relation(value = "order", collectionRelation = "orders")
public class Order extends AbstractEntity<Order> {

	@Id
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	//todo: Criar enum para status da ordem de pedido
	private String status;

	@ManyToOne
	private Payment payment;

	private String trackingCode;

	@ManyToOne
	@JoinColumn(name = "carrierId", referencedColumnName = "id")
	private Carrier carrier;

	@OneToMany
	@JoinColumn(name = "productId", referencedColumnName = "id")
	private List<Product> product;

	@Builder.Default
	private boolean physicalSale = false;

}
