package com.example.webpet.service;

import com.example.webpet.entity.Pdkeep;
import com.example.webpet.entity.PdkeepExample;
import com.example.webpet.mapper.PdkeepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdkeepService {
    @Autowired
    PdkeepMapper pdkeepMapper;
    public List<Pdkeep> getPdkeeps(PdkeepExample pdkeepExample){
        return pdkeepMapper.selectByExample(pdkeepExample);
    }

    public long countPdkeeps(PdkeepExample pdkeepExample) {
        return pdkeepMapper.countByExample(pdkeepExample);
    }

    public int newPdkeep(Pdkeep pdkeep) {
        return pdkeepMapper.insertSelective(pdkeep);
    }

    public int deletePdkeep(PdkeepExample pdkeepExample) {
        return pdkeepMapper.deleteByExample(pdkeepExample);
    }

    public int updatePdkeep(Pdkeep pdkeep) {
        return pdkeepMapper.updateByPrimaryKeySelective(pdkeep);
    }
}
