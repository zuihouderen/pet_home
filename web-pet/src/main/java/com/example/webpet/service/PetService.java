package com.example.webpet.service;

import com.example.webpet.entity.Pet;
import com.example.webpet.entity.PetExample;
import com.example.webpet.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetMapper petMapper;
    public List<Pet> getPets(PetExample petExample){
        return petMapper.selectByExample(petExample);
    }

    public long countPets(PetExample petExample) {
        return petMapper.countByExample(petExample);
    }

    public int newPet(Pet pet) {
        return petMapper.insertSelective(pet);
    }

    public int deletePet(PetExample petExample) {
        return petMapper.deleteByExample(petExample);
    }

    public int updatePet(Pet pet) {
        return petMapper.updateByPrimaryKeySelective(pet);
    }
}
