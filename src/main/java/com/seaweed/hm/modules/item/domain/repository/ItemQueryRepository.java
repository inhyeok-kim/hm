package com.seaweed.hm.modules.item.domain.repository;

import com.seaweed.hm.modules.item.domain.dto.ItemDTO;
import com.seaweed.hm.modules.item.domain.model.entity.Item;
import com.seaweed.hm.modules.item.domain.model.enums.ItemClassType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemQueryRepository {

    @Autowired
    private EntityManager em;

    public ItemDTO findByIdAndFamilyId(long id, long familyId){
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.modules.item.domain.dto.ItemDTO(" +
                "   id, name, familyId, count, type, classType, thumbnail" +
                ") " +
                "from item " +
                "where id = :id and familyId = :familyId",ItemDTO.class);
        query.setParameter("id",id);
        query.setParameter("familyId",familyId);
        return query.getSingleResult();
    }

    public List<ItemDTO> findListByFamilyIdAndClassType(long familyId, ItemClassType classType, Pageable pageable){
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.modules.item.domain.dto.ItemDTO(" +
                "   id, name, familyId, count, type, classType, thumbnail" +
                ") " +
                "from item " +
                "where classType = :classType and familyId = :familyId",ItemDTO.class);
        query.setParameter("classType",classType);
        query.setParameter("familyId",familyId);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public List<ItemDTO> findByFamilyIdAndNameKeyword(long familyId, String keyword){
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.modules.item.domain.dto.ItemDTO(" +
                "   id, name, familyId, count, type, classType, thumbnail" +
                ") " +
                "from item " +
                "where name like concat('%',:keyword,'%') and familyId = :familyId",ItemDTO.class);
        query.setParameter("keyword",keyword);
        query.setParameter("familyId",familyId);
        return query.getResultList();
    }


}
