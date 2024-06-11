package com.seaweed.hm.domain.family.repository;

import com.seaweed.hm.domain.family.enums.FamilyJoinStatus;
import com.seaweed.hm.domain.family.dto.FamilyJoinDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyJoinQueryRepository {
    @Autowired
    private EntityManager em;

    public List<FamilyJoinDTO> findAllByUserAndStatus(long userId, FamilyJoinStatus status){
        TypedQuery<FamilyJoinDTO> tq = em.createQuery("select new com.seaweed.hm.domain.family.dto.FamilyJoinDTO(" +
                "   a.id," +
                "   new com.seaweed.hm.domain.family.vo.JoinUserVO(b.id, b.name)," +
                "   new com.seaweed.hm.domain.family.vo.JoinFamilyVO(c.id, c.name)" +
                ") " +
                "from familyJoin a " +
                "   left outer join user b on a.userId = b.id " +
                "   left outer join family c on a.familyId = c.id " +
                "where a.userId = :userId and a.status = :status", FamilyJoinDTO.class);

        tq.setParameter("userId",userId);
        tq.setParameter("status",status);
        return tq.getResultList();
    }

    public List<FamilyJoinDTO> findAllByFamilyAndStatus(long familyId, FamilyJoinStatus status) {
        TypedQuery<FamilyJoinDTO> tq = em.createQuery("select new com.seaweed.hm.domain.family.dto.FamilyJoinDTO(" +
                "   a.id," +
                "   new com.seaweed.hm.domain.family.vo.JoinUserVO(b.id, b.name)," +
                "   new com.seaweed.hm.domain.family.vo.JoinFamilyVO(c.id, c.name)" +
                ") " +
                "from familyJoin a " +
                "   left outer join user b on a.userId = b.id " +
                "   left outer join family c on a.familyId = c.id " +
                "where c.id = :familyId and a.status = :status", FamilyJoinDTO.class);

        tq.setParameter("familyId",familyId);
        tq.setParameter("status",status);
        return tq.getResultList();
    }
}
