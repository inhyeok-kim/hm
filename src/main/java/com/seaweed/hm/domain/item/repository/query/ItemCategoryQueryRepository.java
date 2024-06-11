package com.seaweed.hm.domain.item.repository.query;

import com.seaweed.hm.domain.item.dto.ItemCategoryDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemCategoryQueryRepository {
    @Autowired
    private EntityManager em;

    public List<ItemCategoryDTO> findByFamilyIdAndOrder(long familyId, long userId){
        TypedQuery<ItemCategoryDTO> tq = em.createQuery("" +
                "select new com.seaweed.hm.domain.item.dto.ItemCategoryDTO(" +
                "   a.id, a.name, a.familyId, a.parentId, a.isRoot" +
                ")" +
                " from itemCategory as a" +
                "   left outer join itemCategoryOrder as b on new com.seaweed.hm.domain.item.vo.ItemCategoryOrderID(:userId,a.id) = b.id" +
                " where b.userId = :userId" +
                "   and a.familyId = :familyId" +
                " order by b.orderNum",ItemCategoryDTO.class);
        tq.setParameter("familyId",familyId);
        tq.setParameter("userId",userId);

        return tq.getResultList();
    }
}
