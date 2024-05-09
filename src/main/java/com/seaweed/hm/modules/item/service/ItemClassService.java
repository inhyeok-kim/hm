package com.seaweed.hm.modules.item.service;

import com.seaweed.hm.comm.exception.UnAuthorizationException;
import com.seaweed.hm.modules.item.dto.ItemClassDTO;
import com.seaweed.hm.modules.item.entity.ItemClass;
import com.seaweed.hm.modules.item.repository.ItemClassRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemClassService {

    @Autowired
    private ItemClassRepository itemClassRepository;

    /**
     * 모든 ItemClassList를 가져오는 서비스
     * @return
     */
    public List<ItemClass> getItemClassListAll(){
        return itemClassRepository.findAllJoinItem();
    }

    /**
     * 특정 Family의 itemCLass 목록을 가져오는 서비스
     * item lazy fetch
     * @param familyId
     * @return
     */
    public List<ItemClass> getItemClassListByFamilyId(long familyId) {
        return itemClassRepository.findByFamilyId(familyId);
    }

    /**
     * itemClassId에 해당하는 itemClass를 가져오는 서비스
     * @param itemClassId
     * @return
     */
    public ItemClass getItemClass(long itemClassId){
        Optional<ItemClass> itemClass = itemClassRepository.findById(itemClassId);
        return itemClass.orElse(null);
    }

    /**
     * 새로운 ItemClass를 저장하는 서비스
     * @param itemClass
     * @return
     */
    public ItemClass regist(ItemClass itemClass) {
        return itemClassRepository.save(itemClass);
    }

    public ItemClass modify(ItemClass itemClass) throws NotFoundException {
        if(itemClassRepository.findById(itemClass.getId()).isPresent()){
            return itemClassRepository.save(itemClass);
        } else {
            throw new NotFoundException("");
        }
    }

    public void remove(ItemClass itemClass) {
        itemClassRepository.delete(itemClass);
    }
}