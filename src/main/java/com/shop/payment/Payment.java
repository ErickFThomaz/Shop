package com.shop.payment;

import com.shop.core.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Relation(value = "payment", collectionRelation = "payments")
public class Payment extends AbstractEntity<Payment> {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    //todo: Criar status para o status do pagamento
    private String status;

    //todo: Criar enum que será responsavel pelo metodo de pagamento.
    private String method;

    private BigDecimal value;

    private BigDecimal paidValue;

    private LocalDateTime dueDate;

    private BigDecimal discount;

}
