package com.seaweed.hm.domain.item.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemCategoryOrderID implements Serializable {
    @Column(name="userId")
    private long userId;
    @Column(name="itemCategoryId")
    private long itemCategoryId;
}
