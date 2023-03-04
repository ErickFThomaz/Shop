package com.shop.payment;

import com.shop.core.AuditableEntity;
import com.shop.payment.method.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Payment extends AuditableEntity {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private BigDecimal value;

    private BigDecimal paidValue;

    private LocalDateTime dueDate;

    private BigDecimal discount;

}
