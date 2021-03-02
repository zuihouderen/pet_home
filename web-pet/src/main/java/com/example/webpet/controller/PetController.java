package com.example.webpet.controller;

import com.alibaba.fastjson.JSON;
import com.example.webpet.entity.*;
import com.example.webpet.response.PetResponse;
import com.example.webpet.service.PetService;
import com.example.webpet.service.PlogService;
import com.example.webpet.service.UserService;
import com.example.webplog.response.PlogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    PlogService plogService;


    private Pet getPet(String pName){
        PetExample petExample=new PetExample();
        PetExample.Criteria criteria=petExample.createCriteria();
        criteria.andPnameEqualTo(pName);
        List<Pet>pets=petService.getPets(petExample);
        if(pets.isEmpty()){
            return new Pet();
        }
        return pets.get(0);
    }

    private Plog getPlog(String Pltitle){
        PlogExample plogExample=new PlogExample();
        PlogExample.Criteria criteria=plogExample.createCriteria();
        criteria.andPltitleEqualTo(Pltitle);
        List<Plog>plogs=plogService.getPlogs(plogExample);
        if (plogs.isEmpty()){
            return  new Plog();
        }
        else {
            return plogs.get(0);
        }
    }
    /**
     * 宠物名和id不可以更改
     * */
    @PostMapping("/updatePet")
    public String updatePet(@RequestBody Pet reqPet){
        Pet pet=getPet(reqPet.getPname());
        if(pet!=null){
            reqPet.setPid(pet.getPid());
            petService.updatePet(reqPet);
            return JSON.toJSONString(new PetResponse(reqPet,"更新宠物信息成功",1));
        }
        else {
            return JSON.toJSONString(new PetResponse(new Pet(),"更新宠物信息失败",0));
        }
    }

    /**
     * 级联删除注意
     * @param reqPet
     * @return
     */
    @PostMapping("/deletePet")
    public String deletePet(@RequestBody Pet reqPet){
        Pet pet=getPet(reqPet.getPname());
        if(pet!=null){
            //1.删除所有宠物日志
            PlogExample plogExample=new PlogExample();
            PlogExample.Criteria criteria=plogExample.createCriteria();
            criteria.andPlownerEqualTo(reqPet.getPname());
            plogService.deletePlog(plogExample);
            //2.删除宠物
            PetExample petExample=new PetExample();
            PetExample.Criteria criteria1=petExample.createCriteria();
            criteria1.andPidEqualTo(pet.getPid());
            petService.deletePet(petExample);
            return JSON.toJSONString(new PetResponse(reqPet,"删除宠物成功",1));
        }
        else {
            return JSON.toJSONString(new PetResponse(new Pet(),"删除宠物失败",0));
        }
    }

    @PostMapping("/getPets")
    public String getPets(@RequestBody User reqUser){
        PetExample petExample=new PetExample();
        PetExample.Criteria criteria=petExample.createCriteria();
        criteria.andPhostEqualTo(reqUser.getUname());
        List<Pet>pets=petService.getPets(petExample);
        return JSON.toJSONString(new PetResponse(pets,"查询宠物成功",1));
    }
    @PostMapping("/getPet")
    public String getPet(@RequestBody Pet reqPet){
        PetExample petExample=new PetExample();
        PetExample.Criteria criteria=petExample.createCriteria();
        criteria.andPnameEqualTo(reqPet.getPname());
        List<Pet>pets=petService.getPets(petExample);
        return JSON.toJSONString(new PetResponse(pets,"查询宠物成功",1));
    }


    /**
     * 宠物日志模块
     */
    @PostMapping("/getPlogs")
    public String getPlogs(@RequestBody Pet reqPet){
        PlogExample plogExample=new PlogExample();
        PlogExample.Criteria criteria=plogExample.createCriteria();
        criteria.andPlownerEqualTo(reqPet.getPname());
        List<Plog>plogs=plogService.getPlogs(plogExample);
        if(plogs.size()>1){
        plogs.sort(new Comparator<Plog>() {
            @Override
            public int compare(Plog o1, Plog o2) {
                return o2.getPldate().compareTo(o1.getPldate());
            }
        });}
        return JSON.toJSONString(new PlogResponse(plogs,"查询宠物日志成功",1));
    }
    @PostMapping("/getPlog")
    public String getPlog(@RequestBody Plog reqPlog){
        PlogExample plogExample=new PlogExample();
        PlogExample.Criteria criteria=plogExample.createCriteria();
        criteria.andPltitleEqualTo(reqPlog.getPltitle());
        List<Plog>plogs=plogService.getPlogs(plogExample);
        return JSON.toJSONString(new PlogResponse(plogs,"获取宠物日志成功",1));
    }

    @PostMapping("/addPetLog")
    public String addPetLog(@RequestBody Plog reqPlog){
        Pet pet=getPet(reqPlog.getPlowner());
        if(pet!=null){
            PlogExample plogExample=new PlogExample();
            PlogExample.Criteria criteria=plogExample.createCriteria();
            criteria.andPltitleEqualTo(reqPlog.getPltitle());
            List<Plog>plogs=plogService.getPlogs(plogExample);
            if(plogs.isEmpty()) {
                plogService.newPlog(reqPlog);
                return JSON.toJSONString(new PlogResponse(reqPlog,"添加宠物日志成功",1));
            }
            else{
                return JSON.toJSONString(new PlogResponse(reqPlog,"标题重复，添加宠物日志失败",0));
            }
        }
        else {
            return JSON.toJSONString(new PlogResponse(new Plog(),"宠物不存在，添加宠物日志失败",0));
        }
    }
    //标题不能修改
    @PostMapping("/updatePetLog")
    public String updatePetLog(@RequestBody  Plog reqPlog){
        Plog plog=getPlog(reqPlog.getPltitle());
        if(plog!=null){
            reqPlog.setPlid(plog.getPlid());
            plogService.updatePlog(reqPlog);
            return JSON.toJSONString(new PlogResponse(reqPlog,"修改宠物日志成功",1));
        }
        else {
            return JSON.toJSONString(new PlogResponse(new Plog(),"修改宠物日志失败",0));
        }
    }


    @PostMapping("/deletePlog")
    public String deletePlog(@RequestBody  Plog reqPlog){
        Plog plog=getPlog(reqPlog.getPltitle());
        if(plog!=null){
            PlogExample plogExample=new PlogExample();
            PlogExample.Criteria criteria=plogExample.createCriteria();
            criteria.andPlidEqualTo(plog.getPlid());
            plogService.deletePlog(plogExample);
            return JSON.toJSONString(new PlogResponse(reqPlog,"删除宠物日志成功",1));
        }
        else {
            return JSON.toJSONString(new PlogResponse(new Plog(),"删除宠物日志失败",0));
        }
    }
}
