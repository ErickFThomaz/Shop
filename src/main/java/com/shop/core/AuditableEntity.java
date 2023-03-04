package com.shop.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
@JsonIgnoreProperties(allowGetters = true)
@EntityListeners({ AuditingEntityListener.class })
public abstract class AuditableEntity implements Serializable {

	@CreatedDate
	private Instant createdAt;

	@CreatedBy
	@Column(updatable = false)
	private String createUserId;

	@LastModifiedDate
	private Instant updatedAt;

	@LastModifiedBy
	private String lastUserId;

}