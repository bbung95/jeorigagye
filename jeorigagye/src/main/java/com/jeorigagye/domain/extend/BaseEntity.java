package com.jeorigagye.domain.extend;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;
}
