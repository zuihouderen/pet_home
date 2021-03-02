package com.example.webpet.service;

import com.example.webpet.entity.Petknowledge;
import com.example.webpet.entity.PetknowledgeExample;
import com.example.webpet.mapper.PetknowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetknowledgeService {
    @Autowired
    PetknowledgeMapper petknowledgeMapper;
    public List<Petknowledge> getPetknowledges(PetknowledgeExample petknowledgeExample){
        return petknowledgeMapper.selectByExample(petknowledgeExample);
    }

    public long countPetknowledges(PetknowledgeExample petknowledgeExample) {
        return petknowledgeMapper.countByExample(petknowledgeExample);
    }

    public int newPetknowledge(Petknowledge petknowledge) {
        return petknowledgeMapper.insertSelective(petknowledge);
    }

    public int deletePetknowledge(PetknowledgeExample petknowledgeExample) {
        return petknowledgeMapper.deleteByExample(petknowledgeExample);
    }

    public int updatePetknowledge(Petknowledge petknowledge) {
        return petknowledgeMapper.updateByPrimaryKeySelective(petknowledge);
    }
}
