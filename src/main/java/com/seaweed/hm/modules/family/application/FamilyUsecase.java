package com.seaweed.hm.modules.family.application;


import com.seaweed.hm.modules.family.domain.model.dto.FamilyDTO;
import com.seaweed.hm.modules.family.domain.model.dto.FamilyJoinReqDTO;
import com.seaweed.hm.modules.family.domain.model.entity.Family;
import com.seaweed.hm.modules.family.domain.model.entity.FamilyJoin;
import com.seaweed.hm.modules.family.domain.model.enums.FamilyJoinStatus;
import com.seaweed.hm.modules.family.presentation.exception.FamilyContainsUserException;
import com.seaweed.hm.modules.family.presentation.exception.UserHasFamilyException;
import com.seaweed.hm.modules.family.domain.service.FamilyService;
import com.seaweed.hm.modules.user.domain.model.entity.User;
import com.seaweed.hm.modules.user.domain.service.SimpleUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param userId
     * @param inviteCode
     * @throws FamilyContainsUserException
     * @throws UserHasFamilyException
     */
    public void joinRequest(long userId, String inviteCode) throws FamilyContainsUserException, UserHasFamilyException {
        User user = simpleUserService.getUserById(userId);
        if(user.hasFamily()){
            throw new UserHasFamilyException("이미 가입된 가족이 존재합니다.");
        }
        Family family = familyService.getFamilyByInviteCode(inviteCode);
        FamilyJoin req = FamilyJoin.NewRequest().familyId(family.getId()).userId(userId).build();
        familyService.requestJoin(req);
    }


    /**
     * 사용자의 가족 가입 요청 목록을 가져오는 usecase
     * @param loginId
     * @param status
     * @return
     */
    public List<FamilyJoinReqDTO> getMyJoinRequest(long loginId, FamilyJoinStatus status){
        return familyService.getFamilyJoinReqByUser(loginId, status).stream().map(FamilyJoinReqDTO::new).toList();
    }

    /**
     * 특정 가족의 가입 요청 목록을 가져오는 usecase
     * @param loginId
     * @param status
     * @return
     */
    public List<FamilyJoinReqDTO> getMyFamilyJoinRequest(long loginId, FamilyJoinStatus status){
        User user = simpleUserService.getUserById(loginId);
        return familyService.getFamilyJoinReqByFamily(user.getFamilyId(), status).stream().map(FamilyJoinReqDTO::new).toList();
    }


    /**
     * 사용자의 가족 정보를 가져오는 usecase
     * @param loginId
     * @return
     */
    public FamilyDTO findMyFamily(long loginId) {
        User user = simpleUserService.getUserById(loginId);
        Family myFamily = familyService.getFamily(user.getFamilyId());
        if(!user.hasFamily()) return null;
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
        Family family = familyService.getFamily(user.getFamilyId());
        if(family == null) throw new NotFoundException("소속된 가족이 존재하지 않습니다.");
        family.refreshInviteCode();
        familyService.updateInviteCode(family);
        return family.getInviteCode();
    }
}
