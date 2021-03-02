package com.example.webpet.service;

import com.example.webpet.entity.Pdlove;
import com.example.webpet.entity.PdloveExample;
import com.example.webpet.mapper.PdloveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdloveService {
    @Autowired
    PdloveMapper pdloveMapper;
    public List<Pdlove> getPdloves(PdloveExample pdloveExample){
        return pdloveMapper.selectByExample(pdloveExample);
    }

    public long countPdloves(PdloveExample pdloveExample) {
        return pdloveMapper.countByExample(pdloveExample);
    }

    public int newPdlove(Pdlove pdlove) {
        return pdloveMapper.insertSelective(pdlove);
    }

    public int deletePdlove(PdloveExample pdloveExample) {
        return pdloveMapper.deleteByExample(pdloveExample);
    }

    public int updatePdlove(Pdlove pdlove) {
        return pdloveMapper.updateByPrimaryKeySelective(pdlove);
    }
}
