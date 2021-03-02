package com.example.webpet.service;

import com.example.webpet.entity.Plog;
import com.example.webpet.entity.PlogExample;
import com.example.webpet.mapper.PlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlogService {
    @Autowired
    PlogMapper plogMapper;
    public List<Plog> getPlogs(PlogExample plogExample){
        return plogMapper.selectByExample(plogExample);
    }

    public long countPlogs(PlogExample plogExample) {
        return plogMapper.countByExample(plogExample);
    }

    public int newPlog(Plog plog) {
        return plogMapper.insertSelective(plog);
    }

    public int deletePlog(PlogExample plogExample) {
        return plogMapper.deleteByExample(plogExample);
    }

    public int updatePlog(Plog plog) {
        return plogMapper.updateByPrimaryKeySelective(plog);
    }
}
