package com.seaweed.hm.modules.family.usecase;


import com.seaweed.hm.modules.family.dto.FamilyDTO;
import com.seaweed.hm.modules.family.entity.Family;
import com.seaweed.hm.modules.family.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.service.FamilyService;
import com.seaweed.hm.modules.user.entity.User;
import com.seaweed.hm.modules.user.service.SimpleUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class FamilyUsecase {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private SimpleUserService simpleUserService;

    /**
     * 새로운 가족을 만드는 usecase
     * @param loginId
     * @param name
     * @return
     * @throws UserHasFamilyException 이미 가입된 가족이 존재하는 경우
     */
    public FamilyDTO createFamily(long loginId, String name) throws UserHasFamilyException {
        User user = simpleUserService.getUserById(loginId);
        if(user.hasFamily()){
            throw new UserHasFamilyException("이미 가입된 가족이 존재합니다.");
        }
        Family newFamily = Family.builder().name(name).createUserId(loginId).build();
        FamilyDTO result = new FamilyDTO(familyService.registFamily(newFamily));
        user.joinFamily(result.getId());
        simpleUserService.updateUserFamily(user);

        return result;
    }

    /**
     * 사용자의 가족 가입 요청을 처리하는 usecase
     * @param familyId
     * @param userId
     */
    @Transactional
    public void joinRequest(long familyId, long userId) throws FamilyContainsUserException, UserHasFamilyException {
        User user = simpleUserService.getUserById(userId);
        if(user.hasFamily()){
            throw new UserHasFamilyException("이미 가입된 가족이 존재합니다.");
        }
        Family family = familyService.getFamily(familyId);
        if(family.containsUser(userId)){
            throw new FamilyContainsUserException("이미 가입된 가족이 존재합니다.");
        }
    }

    /**
     * 사용자의 가족 정보를 가져오는 usecase
     * @param loginId
     * @return
     */
    public FamilyDTO findMyFamily(long loginId) {
        User user = simpleUserService.getUserById(loginId);
        Family myFamily = user.getFamily();
        if(myFamily == null) return null;
        return new FamilyDTO(myFamily);
    }

    /**
     * 가족 초대코드를 갱신하는 usecase
     * @param id
     * @return
     * @throws NotFoundException 소속된 가족이 존재하지 않는 경우
     */
    public String refreshInviteCode(long id) throws NotFoundException {
        User user = simpleUserService.getUserById(id);
        Family family = user.getFamily();
        if(family == null) throw new NotFoundException("소속된 가족이 존재하지 않습니다.");
        family.refreshInviteCode();
        familyService.updateInviteCode(family);
        return family.getInviteCode();
    }
}
