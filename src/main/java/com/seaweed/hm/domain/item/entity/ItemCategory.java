package com.seaweed.hm.domain.item.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "itemCategory")
@Table(name = "ITEM_CATEGORY")
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
public class ItemCategory extends DefaultEntity {
    private String name;
    private long parentId;
    private long familyId;
    private boolean isRoot;

    @Builder(builderMethodName = "rootBuilder")
    public ItemCategory(User creator, String name) throws UnAuthorizationException {
        if(!creator.hasFamily()) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.familyId = creator.getFamilyId();
        this.name = name;
        this.createUserId = creator.getId();
        this.isRoot = true;
    }

    @Builder(builderMethodName = "childBuilder")
    public ItemCategory(User creator, String name, long parentId) throws UnAuthorizationException {
        if(!creator.hasFamily()) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.familyId = creator.getFamilyId();
        this.name = name;
        this.createUserId = creator.getId();
        this.parentId = parentId;
        this.isRoot = false;
    }
}
