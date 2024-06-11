package com.seaweed.hm.domain.item.dto;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDTO {
    private long id;
    private String name;
    private long parentId;
    private long familyId;
    private boolean isRoot;

}
