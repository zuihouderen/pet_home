package com.example.webpet.service;

import com.example.webpet.entity.Pdcomment;
import com.example.webpet.entity.PdcommentExample;
import com.example.webpet.mapper.PdcommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdcommentService {
    @Autowired
    PdcommentMapper pdcommentMapper;
    public List<Pdcomment> getPdcomments(PdcommentExample pdcommentExample){
        return pdcommentMapper.selectByExample(pdcommentExample);
    }

    public long countPdcomments(PdcommentExample pdcommentExample) {
        return pdcommentMapper.countByExample(pdcommentExample);
    }

    public int newPdcomment(Pdcomment pdcomment) {
        return pdcommentMapper.insertSelective(pdcomment);
    }

    public int deletePdcomment(PdcommentExample pdcommentExample) {
        return pdcommentMapper.deleteByExample(pdcommentExample);
    }

    public int updatePdcomment(Pdcomment pdcomment) {
        return pdcommentMapper.updateByPrimaryKeySelective(pdcomment);
    }
}
