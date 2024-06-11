package com.seaweed.hm.application.family.command;

import com.seaweed.hm.domain.family.entity.Family;
import com.seaweed.hm.domain.family.entity.FamilyJoin;
import com.seaweed.hm.domain.family.repository.FamilyJoinRepository;
import com.seaweed.hm.domain.family.repository.FamilyRepository;
import com.seaweed.hm.application.family.exception.FamilyContainsUserException;
import com.seaweed.hm.application.family.exception.UserHasFamilyException;
import com.seaweed.hm.domain.user.entity.User;
import com.seaweed.hm.domain.user.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FamilyJoinService {

    @Autowired
    private FamilyJoinRepository familyJoinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyRepository familyRepository;

    /**
     * 사용자의 가족 가입 요청을 처리하는 usecase
     * @param userId
     * @param inviteCode
     * @throws FamilyContainsUserException
     * @throws UserHasFamilyException
     */
    @Transactional
    public void joinRequest(long userId, String inviteCode) throws FamilyContainsUserException, UserHasFamilyException, NotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) throw new NotFoundException("존재하지 않는 사용자");
        User user = userOpt.get();

        Optional<Family> familyOpt = familyRepository.findByInviteCode(inviteCode);
        if(familyOpt.isEmpty()) throw new NotFoundException("유효하지 않는 초대 코드");

        Family family = familyOpt.get();
        FamilyJoin req = FamilyJoin.NewRequest().family(family).user(user).build();
        familyJoinRepository.save(req);
    }

}
