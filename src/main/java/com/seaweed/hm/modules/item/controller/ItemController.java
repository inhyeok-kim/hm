package com.seaweed.hm.modules.item.controller;

import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.collections.PageList;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.enums.ItemClassType;
import com.seaweed.hm.modules.item.enums.ItemType;
import com.seaweed.hm.modules.item.usecase.ItemUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemUsecase itemUsecase;

    @PostMapping("")
    public APIResponse createItem(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemDTO dto) throws BadRequestException {
        if(
            !StringUtils.hasText(dto.getName())
            || dto.getType() == null
            || dto.getClassType() == null
        ){
            throw new BadRequestException("");
        }


        try {
            ItemDTO result = itemUsecase.createItem(loginId,dto);
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        }
    }

    @PutMapping("")
    public APIResponse updateItem(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemDTO dto) throws BadRequestException {
        if(
            !StringUtils.hasText(dto.getName())
            || dto.getType() == null
            || dto.getClassType() == null
            || dto.getId() == 0
        ){
            throw new BadRequestException("");
        }

        try {
            ItemDTO result = itemUsecase.updateItem(loginId,dto);
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다.").build();
        }
    }

    @DeleteMapping("{id}")
    public APIResponse deleteItem(
            @LoginId long loginId,
            @PathVariable("id") long itemId
    ){
        try {
            itemUsecase.deleteItem(loginId,itemId);
            return APIResponse.builder().build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 재고입니다..").build();
        }
    }

    @GetMapping("/list")
    public APIResponse getItemList(
            @LoginId long loginId,
            HttpServletRequest request,
            HttpServletResponse response
            , @PageableDefault(page = 0, size = 20) Pageable pageable
            , @RequestParam(name = "classType")ItemClassType itemClassType
            ){
        PageList<ItemDTO> list = null;
        try {
            list = itemUsecase.getItemListOfFamily(loginId,itemClassType,pageable);
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다.").build();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return APIResponse.builder().data(list).build();
    }

    @GetMapping("/{id}")
    public APIResponse getItem(
            @LoginId long loginId,
            @PathVariable("id") long itemId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        try {
            ItemDTO itemClassDTO = itemUsecase.getItem(loginId,itemId);
            return APIResponse.builder().data(itemClassDTO).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 재고입니다..").build();
        }
    }

    @GetMapping("/search")
    public APIResponse searchItem(
            @LoginId long loginId,
            HttpServletResponse response,
            HttpServletRequest request
    ){
        String keyword = request.getParameter("keyword");
        if(!StringUtils.hasText(keyword)){
            return APIResponse.builder().data(new ArrayList<>()).build();
        }
        List<ItemDTO> list = itemUsecase.searchItem(loginId,keyword);

        return APIResponse.builder().data(list).build();
    }

    @PostMapping("/count_plus")
    public APIResponse countPlus(
            @LoginId long loginId,
            @RequestBody Map body,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        long itemId = (int)body.get("id");
        int count = (int) body.get("count");
        ItemDTO itemDTO;
        try {
            itemDTO = itemUsecase.countPlusItem(loginId,itemId, count);
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 재고입니다..").build();
        }
        return APIResponse.builder().data(itemDTO).build();
    }
}
