package com.seaweed.hm.modules.family.usecase;


import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.family.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.service.FamilyService;
import com.seaweed.hm.modules.user.entity.User;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyUsecase {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SimpleUserService simpleUserService;

    /**
     * 사용자의 가족 가입 요청을 처리하는 service
     * @param familyId
     * @param userId
     */
    public void joinRequest(long familyId, long userId) throws FamilyContainsUserException, UserHasFamilyException {
        User user = simpleUserService.getUserById(userId);
        if(user.hasFamily()){
            throw new UserHasFamilyException("가입된 가족이 존재합니다.");
        }
        Family family = familyService.getFamily(familyId);
        if(family.containsUser(userId)){
            throw new FamilyContainsUserException("가입된 가족이 존재합니다.");
        }
    }
}
