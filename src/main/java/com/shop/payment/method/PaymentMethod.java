package com.shop.payment.method;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.shop.core.BeanUtil.getBean;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CREDIT_MERCADO_LIVRE(null);

    private final Class<? extends PaymentMethodStrategy> strategy;

    public PaymentMethodStrategy getStrategy() {
        return getBean(this.strategy);
    }
}
