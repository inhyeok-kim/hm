package com.seaweed.hm.modules.family.domain.repository;

import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinDTO;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.model.entity.FamilyJoin;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyJoinQueryRepository {
    @Autowired
    private EntityManager em;

    public List<FamilyJoinDTO> findAllByUserIdAndStatus(long userId, FamilyJoinStatus status){
        TypedQuery<FamilyJoinDTO> tq = em.createQuery("select new com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinDTO(" +
                "   a.id," +
                "   new com.seaweed.hm.modules.family.domain.model.vo.JoinUserVO(b.id, b.name)," +
                "   new com.seaweed.hm.modules.family.domain.model.vo.JoinFamilyVO(c.id, c.name)" +
                ") " +
                "from familyJoin a " +
                "   left outer join user b on a.userId = b.id " +
                "   left outer join family c on a.familyId = c.id " +
                "where a.userId = :userId and a.status = :status", FamilyJoinDTO.class);

        tq.setParameter("userId",userId);
        tq.setParameter("status",status);
        return tq.getResultList();
    }

}
