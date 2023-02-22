package com.shop.core.auditing;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class AuditingDateTimeProvider implements DateTimeProvider {

	private final Clock systemClock;

	public AuditingDateTimeProvider(Clock systemClock) {
		this.systemClock = systemClock;
	}

	@Override
	public Optional<TemporalAccessor> getNow() {
		return Optional.of(ZonedDateTime.now(systemClock));
	}

}