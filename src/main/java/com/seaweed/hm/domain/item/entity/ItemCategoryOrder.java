package com.seaweed.hm.domain.item.entity;


import com.seaweed.hm.abstracts.entity.DefaultEntity;
import com.seaweed.hm.domain.item.vo.ItemCategoryOrderID;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "itemCategoryOrder")
@Table(name = "ITEM_CATEGORY_ORDER")
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
public class ItemCategoryOrder {
    @EmbeddedId
    @AttributeOverride(name="itemCategoryId", column = @Column(name="itemCategoryId"))
    private ItemCategoryOrderID id;

    private int orderNum;

    @Builder()
    public ItemCategoryOrder(long userId, long categoryId, int orderNum){
        this.id = new ItemCategoryOrderID(userId, categoryId);
        this.orderNum = orderNum;
    }
}
