package com.seaweed.hm.modules.item.controller;

import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.usecase.ItemUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemUsecase itemUsecase;

    @PostMapping("/item/class")
    public APIResponse registItemClass(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemClassDTO dto){
        try {
            long result = itemUsecase.createItemClass(loginId,dto.getName(),dto.getType());
            dto.setId(result);
            return APIResponse.builder().data(dto).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("소속된 가족이 없습니다.").build();
        }

    }
}
