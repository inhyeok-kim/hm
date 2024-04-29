package com.seaweed.hm.modules.item.controller;

import com.seaweed.hm.comm.argument.LoginId;
import com.seaweed.hm.comm.component.http.response.APIResponse;
import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.dto.ItemDTO;
import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.usecase.ItemUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemUsecase itemUsecase;

    @PostMapping("/class")
    public APIResponse createItemClass(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemClassDTO dto){
        try {
            ItemClassDTO result = itemUsecase.createItemClass(loginId,dto.getName(),dto.getType());
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("소속된 가족이 없습니다.").build();
        }
    }

    @PutMapping("/class")
    public APIResponse updateItemClass(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemClassDTO dto){
        try {
            ItemClassDTO result = itemUsecase.updateItemClass(loginId,dto);
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("소속된 가족이 없습니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다.").build();
        }
    }

    @GetMapping("/class/list")
    public APIResponse getItemClassList(
            @LoginId long loginId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        List<ItemClassDTO> list = itemUsecase.getItemClassListOfUser(loginId);

        return APIResponse.builder().data(list).build();
    }

    @GetMapping("/class/{id}")
    public APIResponse getItemClass(
            @LoginId long loginId,
            @PathVariable("id") long itemClassId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        try {
            ItemClassDTO itemClassDTO = itemUsecase.getItemClass(loginId,itemClassId);
            return APIResponse.builder().data(itemClassDTO).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다..").build();
        }
    }

    @PostMapping("")
    public APIResponse createItem(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemDTO dto){
        try {
            ItemDTO result = itemUsecase.createItem(loginId,dto.getName(),dto.getClassId(),dto.getType(),dto.getCount());
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("소속된 가족이 없습니다.").build();
        }
    }

    @PutMapping("")
    public APIResponse updateItem(@LoginId long loginId, HttpServletResponse response, HttpServletRequest request, @RequestBody ItemDTO dto){
        try {
            ItemDTO result = itemUsecase.updateItem(loginId,dto);
            return APIResponse.builder().data(result).build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("소속된 가족이 없습니다.").build();
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다.").build();
        }
    }

    @GetMapping("/list/{classId}")
    public APIResponse getItemList(
            @LoginId long loginId,
            @PathVariable("classId") long classId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        List<ItemDTO> list = null;
        try {
            list = itemUsecase.getItemListOfClass(loginId,classId);
        } catch (NotFoundException e) {
            return APIResponse.builder().code(-1).message("존재하지 않는 분류입니다.").build();
        } catch (UnAuthorizationException e) {
            return APIResponse.builder().code(-1).message("잘못된 접근입니다.").build();
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
}
