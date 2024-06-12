package com.seaweed.hm.domain.item.repository.query;

import com.seaweed.hm.domain.item.enums.ItemClassType;
import com.seaweed.hm.domain.item.dto.ItemDTO;
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
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.domain.item.dto.ItemDTO(" +
                "   a.id, a.name, a.familyId, a.count, a.type, a.classType, a.thumbnail, COALESCE(b.id,0), b.name" +
                ") " +
                " from item as a" +
                "   left outer join itemCategory as b on a.categoryId = b.id" +
                " where a.id = :id and a.familyId = :familyId",ItemDTO.class);
        query.setParameter("id",id);
        query.setParameter("familyId",familyId);
        return query.getResultList().stream().findAny().orElse(null);
    }

    public List<ItemDTO> findListByFamilyIdAndClassType(long familyId, ItemClassType classType, Pageable pageable){
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.domain.item.dto.ItemDTO(" +
                "   a.id, a.name, a.familyId, a.count, a.type, a.classType, a.thumbnail, COALESCE(b.id,0), b.name" +
                ") " +
                " from item as a" +
                "   left outer join itemCategory as b on a.categoryId = b.id" +
                " where a.classType = :classType and a.familyId = :familyId",ItemDTO.class);
        query.setParameter("classType",classType);
        query.setParameter("familyId",familyId);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public List<ItemDTO> findByFamilyIdAndNameKeyword(long familyId, String keyword){
        TypedQuery<ItemDTO> query = em.createQuery("select new com.seaweed.hm.domain.item.dto.ItemDTO(" +
                "   a.id, a.name, a.familyId, a.count, a.type, a.classType, a.thumbnail, COALESCE(b.id,0), b.name" +
                ") " +
                " from item as a" +
                "   left outer join itemCategory as b on a.categoryId = b.id" +
                " where a.name like concat('%',:keyword,'%') and a.familyId = :familyId",ItemDTO.class);
        query.setParameter("keyword",keyword);
        query.setParameter("familyId",familyId);
        return query.getResultList();
    }


}
