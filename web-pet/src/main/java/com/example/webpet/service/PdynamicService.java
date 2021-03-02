package com.example.webpet.service;

import com.example.webpet.entity.Pdynamic;
import com.example.webpet.entity.PdynamicExample;
import com.example.webpet.mapper.PdynamicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdynamicService {
    @Autowired
    PdynamicMapper pdynamicMapper;
    public List<Pdynamic> getPdynamics(PdynamicExample pdynamicExample){
        return pdynamicMapper.selectByExample(pdynamicExample);
    }

    public long countPdynamics(PdynamicExample pdynamicExample) {
        return pdynamicMapper.countByExample(pdynamicExample);
    }

    public int newPdynamic(Pdynamic pdynamic) {
        return pdynamicMapper.insertSelective(pdynamic);
    }

    public int deletePdynamic(PdynamicExample pdynamicExample) {
        return pdynamicMapper.deleteByExample(pdynamicExample);
    }

    public int updatePdynamic(Pdynamic pdynamic) {
        return pdynamicMapper.updateByPrimaryKeySelective(pdynamic);
    }
}
