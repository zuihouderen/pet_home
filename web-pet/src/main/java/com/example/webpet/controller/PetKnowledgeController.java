package com.example.webpet.controller;

import com.alibaba.fastjson.JSON;
import com.example.webpet.entity.Petknowledge;
import com.example.webpet.entity.PetknowledgeExample;
import com.example.webpet.entity.Question;
import com.example.webpet.entity.QuestionExample;
import com.example.webpet.response.PetknowledgeResponse;
import com.example.webpet.response.QuestionResponse;
import com.example.webpet.service.PetknowledgeService;
import com.example.webpet.service.QuestionService;
import com.example.webpet.service.UserService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.Comparator;
import java.util.List;
import java.util.jar.JarEntry;

@RestController
@CrossOrigin
@RequestMapping("/petknowledge")
public class PetKnowledgeController {
    @Autowired
    PetknowledgeService petknowledgeService;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    private List<Petknowledge> deSort(List<Petknowledge> petknowledges){
        if(petknowledges.size()>1){
            petknowledges.sort(new Comparator<Petknowledge>() {
                @Override
                public int compare(Petknowledge o1, Petknowledge o2) {
                    return 0;
                }
            });
        }
        return petknowledges;
    }
    private List<Question> deSortQues(List<Question>questions){
        if(questions.size()>1){
            questions.sort(new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    return o2.getQtime().compareTo(o1.getQtime());
                }
            });
        }
        return questions;
    }

    @PostMapping("/add")
    public String add(@RequestBody Petknowledge petknowledge){
        try{
            petknowledgeService.newPetknowledge(petknowledge);
            return JSON.toJSONString(new PetknowledgeResponse(petknowledge,"插入成功",1));
        }catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new PetknowledgeResponse(petknowledge,"插入失败",0));
        }
    }

    @PostMapping("/getAll")
    public String getAll(){
        PetknowledgeExample petknowledgeExample=new PetknowledgeExample();
        PetknowledgeExample.Criteria criteria=petknowledgeExample.createCriteria();
        criteria.andPkidGreaterThan(0);
        List<Petknowledge>petknowledges=petknowledgeService.getPetknowledges(petknowledgeExample);
        deSort(petknowledges);
        return JSON.toJSONString(new PetknowledgeResponse(petknowledges,"宠物常识列表展现",1));
    }

    @PostMapping("/getAllByType")
    public String getAllByType(@RequestBody Petknowledge petknowledge){
        PetknowledgeExample petknowledgeExample=new PetknowledgeExample();
        PetknowledgeExample.Criteria criteria=petknowledgeExample.createCriteria();
        criteria.andPettypeEqualTo(petknowledge.getPettype());
        List<Petknowledge>petknowledges=petknowledgeService.getPetknowledges(petknowledgeExample);
        deSort(petknowledges);
        return JSON.toJSONString(new PetknowledgeResponse(petknowledges,"宠物常识列表已找到",1));
    }

    @PostMapping("/getAllByName")
    public String getAllByName(@RequestBody Petknowledge petknowledge){
        PetknowledgeExample petknowledgeExample=new PetknowledgeExample();
        PetknowledgeExample.Criteria criteria=petknowledgeExample.createCriteria();
        criteria.andPetnameLike("%"+petknowledge.getPetname()+"%");
        List<Petknowledge>petknowledges=petknowledgeService.getPetknowledges(petknowledgeExample);
        deSort(petknowledges);
        return JSON.toJSONString(new PetknowledgeResponse(petknowledges,"宠物知识已找到",1));
    }
    @PostMapping("/getById")
    public String getById(@RequestBody Petknowledge petknowledge){
        PetknowledgeExample petknowledgeExample=new PetknowledgeExample();
        PetknowledgeExample.Criteria criteria=petknowledgeExample.createCriteria();
        criteria.andPkidEqualTo(petknowledge.getPkid());
        List<Petknowledge>petknowledges=petknowledgeService.getPetknowledges(petknowledgeExample);
        deSort(petknowledges);
        return JSON.toJSONString(new PetknowledgeResponse(petknowledges,"宠物详情已找到",1));
    }


    //疑问模块
    @PostMapping("/getQues")
    public String getQues(@RequestBody Petknowledge petknowledge){
        QuestionExample questionExample=new QuestionExample();
        QuestionExample.Criteria criteria=questionExample.createCriteria();
        criteria.andPkidEqualTo(petknowledge.getPkid());
        List<Question>questions=questionService.getQuestions(questionExample);
        deSortQues(questions);
        return JSON.toJSONString(new QuestionResponse(questions,"查询详情成功",1));
    }
    @PostMapping("/addques")
    public String addques(@RequestBody Question question){
        try{
            questionService.newQuestion(question);
            QuestionExample questionExample=new QuestionExample();
            QuestionExample.Criteria criteria=questionExample.createCriteria();
            criteria.andPkidEqualTo(question.getPkid());
            criteria.andQtimeEqualTo(question.getQtime());
            criteria.andDesEqualTo(question.getDes());
            List<Question>questions=questionService.getQuestions(questionExample);
            Question question1=questions.get(0);
        return JSON.toJSONString(new QuestionResponse(question1,"提出疑问成功",1));
        }catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new QuestionResponse(question,"提出疑问失败",0));
        }
    }
    //实际上，可以前端判断
    @PostMapping("/isowner")
    public String isowner(@RequestBody Question question){
        QuestionExample questionExample=new QuestionExample();
        QuestionExample.Criteria criteria=questionExample.createCriteria();
        criteria.andQidEqualTo(question.getQid());
        questionService.countQuestions(questionExample);
        return JSON.toJSONString(questionService.countQuestions(questionExample));
    }

    @PostMapping("/deleteques")
    public String deleteques(@RequestBody Question question){
        try{
            QuestionExample questionExample=new QuestionExample();
            QuestionExample.Criteria criteria=questionExample.createCriteria();
            criteria.andQidEqualTo(question.getQid());
            questionService.deleteQuestion(questionExample);
            return JSON.toJSONString(new QuestionResponse(question,"删除疑问成功",1));
        }catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new QuestionResponse(question,"删除疑问失败",0));
        }
    }
}
