package com.shop.core.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventPublisher implements ApplicationEventPublisher {

	private final ApplicationEventPublisher eventPublisher;

	@Override
	public void publishEvent(ApplicationEvent event) {
		eventPublisher.publishEvent(event);
	}

	@Override
	public void publishEvent(Object event) {
		eventPublisher.publishEvent(event);
	}

}
