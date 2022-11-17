package com.vroong.msabootcamp.domain;

import java.time.Instant;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private Instant createdAt = Instant.now();

  @LastModifiedBy
  private String updatedBy;

  @LastModifiedDate
  private Instant updatedAt = Instant.now();
}
