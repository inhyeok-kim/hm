package com.seaweed.hm.domain.item.repository.query;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemCategoryOrderQueryRepository {
    @Autowired
    private EntityManager em;

}
