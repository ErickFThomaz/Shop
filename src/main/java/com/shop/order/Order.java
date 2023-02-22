package com.shop.order;

import com.shop.core.AbstractEntity;
import com.shop.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	private String status;

	// todo Objeto de pagamentos(Metodo de pagamento, valor total, data de vencimento, se
	// foi pago, desconto)
	private String payment;

	// todo Objeto de transportadora(Nome, Cidade de origem, Cidade de destino, código de
	// rastreio)
	private String transportadora;

	// todo Criar vinculo com os produtos
	@OneToMany
	private List<Product> product;

	@Builder.Default
	private boolean physicalSale = false;

}
