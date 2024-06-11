package com.seaweed.hm.domain.item.entity;

import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.comm.http.exception.UnAuthorizationException;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.item.enums.ItemClassType;
import com.seaweed.hm.domain.item.enums.ItemClassTypeConverter;
import com.seaweed.hm.domain.item.enums.ItemType;
import com.seaweed.hm.domain.item.enums.ItemTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="item")
@Table(name = "ITEM")
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
public class Item extends DefaultEntity {

    private String name;
    private long familyId;
    @Column(name = "cnt")
    private int count;

    @Convert(converter = ItemClassTypeConverter.class)
    private ItemClassType classType;
    
    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;

    @Lob
    private String thumbnail;

    @Builder(builderMethodName = "registBuilder")
    public Item(User createUser, String name, long familyId, ItemType type, ItemClassType classType, int count, String thumbnail) throws UnAuthorizationException {
        if(!createUser.hasFamily()) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.name = name;
        this.familyId = familyId;
        this.createUserId = createUser.getId();
        this.type = type;
        this.count = count;
        this.classType = classType;
        this.thumbnail = thumbnail;
    }

    public Item modifyName(User user, String name) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.name = name;
        return this;
    }
    public Item modifyType(User user, ItemType type) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.type = type;
        return this;
    }

    public Item modifyFamily(User user, long familyId) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.familyId = familyId;
        return this;
    }

    public Item modifyClassType(User user, ItemClassType classType) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.classType = classType;
        return this;
    }

    public Item modifyThumbnail(User user, String thumbnail) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.thumbnail = thumbnail;
        return this;
    }

    public Item modifyCount(User user, int count) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.count = count;
        return this;
    }

    public Item plusCount(User user, int count) throws UnAuthorizationException {
        if(!isAccessible(user)) throw new UnAuthorizationException("접근 권한이 없습니다.");
        this.lastModifyUserId = user.getId();
        this.count += count;
        return this;
    }

    public boolean isAccessible(User user){
        return user.getFamilyId() == this.familyId;
    }

}
