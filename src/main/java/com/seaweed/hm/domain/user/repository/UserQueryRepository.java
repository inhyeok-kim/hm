package com.seaweed.hm.domain.user.repository;

import com.seaweed.hm.domain.user.dto.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository {
    @Autowired
    private EntityManager em;

    public UserDTO findById(long id){
        TypedQuery<UserDTO> tq = em.createQuery("" +
                " select new com.seaweed.hm.domain.user.dto.UserDTO(" +
                "   a.id, a.uid, a.name, b.id as familyId, b.name as familyName " +
                " ) " +
                " from user a" +
                "   left outer join family b on a.familyId = b.id" +
                " where a.id = :id",UserDTO.class);
        tq.setParameter("id",id);
        return tq.getResultList().stream().findAny().orElse(null);
    }

    public UserDTO findByUid(String uid){
        TypedQuery<UserDTO> tq = em.createQuery("" +
                " select new com.seaweed.hm.domain.user.dto.UserDTO(" +
                "   a.id, a.uid, a.name, b.id as familyId, b.name as familyName " +
                " ) " +
                " from user a" +
                "   left outer join family b on a.familyId = b.id" +
                " where a.uid = :uid",UserDTO.class);
        tq.setParameter("uid",uid);
        return tq.getResultList().stream().findAny().orElse(null);
    }

}
