package com.seaweed.hm.comm.abstracts.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DefaultDomain {
    protected Long id;
    protected LocalDateTime createAt;
    protected LocalDateTime modifiedAt;
}
