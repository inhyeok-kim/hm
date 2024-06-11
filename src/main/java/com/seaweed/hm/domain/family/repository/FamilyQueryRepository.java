package com.seaweed.hm.domain.family.repository;

import com.seaweed.hm.domain.family.dto.FamilyDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyQueryRepository {

    @Autowired
    private EntityManager em;

    public FamilyDTO findById(long id){
        TypedQuery<FamilyDTO> tq = em.createQuery("" +
                "select new com.seaweed.hm.domain.family.dto.FamilyDTO(" +
                "   id, name, inviteCode" +
                ")" +
                " from family" +
                " where id = :id",FamilyDTO.class);
        tq.setParameter("id",id);
        return tq.getResultList().stream().findAny().orElse(null);
    }

}
