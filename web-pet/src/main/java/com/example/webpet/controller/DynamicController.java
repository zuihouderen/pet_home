package com.example.webpet.controller;

import com.alibaba.fastjson.JSON;
import com.example.webpet.response.PdkeepResponse;
import com.example.webpet.response.PdloveResponse;
import com.example.webpet.response.PdynamicResponse;
import com.example.webpet.entity.*;
import com.example.webpet.response.PdcommentResponse;
import com.example.webpet.service.*;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dynamic")
public class DynamicController {
    @Autowired
    PdynamicService pdynamicService;
    @Autowired
    UserService userService;
    @Autowired
    PdcommentService pdcommentService;
    @Autowired
    PdloveService pdloveService;
    @Autowired
    PdkeepService pdkeepService;
    @Autowired
    MessageService messageService;

    private User getUser(String pdowner){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUnameEqualTo(pdowner);
        List<User>users=userService.getUsers(userExample);
        if(users.isEmpty()){
            return new User();
        }else {
            return users.get(0);
        }
    }
    //获取唯一动态
    private Pdynamic getDynamic(String pdtitle){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdtitleEqualTo(pdtitle);
        List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
        if(pdynamics.isEmpty()){
            return new Pdynamic();
        }
        else {
            return pdynamics.get(0);
        }
    }

    private List<Pdynamic> deSort(List<Pdynamic> pdynamics){
        if(pdynamics.size()>1){
        pdynamics.sort(new Comparator<Pdynamic>() {
            @Override
            public int compare(Pdynamic o1, Pdynamic o2) {
                 return o2.getPdtime().compareTo(o1.getPdtime());
            }
        });}
        return pdynamics;
    }
    private List<Pdcomment> deSortCom(List<Pdcomment> pdcomments){
        if(pdcomments.size()>1){
            pdcomments.sort(new Comparator<Pdcomment>() {
                @Override
                public int compare(Pdcomment o1, Pdcomment o2) {
                    return o2.getComdate().compareTo(o1.getComdate());
                }
            });}
        return pdcomments;
    }
    private List<Pdkeep>deSortKeep(List<Pdkeep> pdkeeps){
        if(pdkeeps.size()>1){
            pdkeeps.sort(new Comparator<Pdkeep>() {
                @Override
                public int compare(Pdkeep o1, Pdkeep o2) {
                    return o2.getPdkeepdata().compareTo(o1.getPdkeepdata());
                }
            });
        }
        return pdkeeps;
    }

    @PostMapping("/isOwner")
    public String isOwner(@RequestBody Pdynamic pdynamic){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        criteria.andPdownerEqualTo(pdynamic.getPdowner());
        int count= (int) pdynamicService.countPdynamics(pdynamicExample);
        return JSON.toJSONString(count);
    }
    @PostMapping("/getAllDynamics")
    public String getAllDynamics(){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdidGreaterThan(0);
        List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
        deSort(pdynamics);
        return JSON.toJSONString(new PdynamicResponse(pdynamics,"查询动态已经返回",1));
    }

    @PostMapping("/getUserDynamics")
    public String getUserDynamics(@RequestBody User reqUser){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdownerEqualTo(reqUser.getUname());
        List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
        deSort(pdynamics);
        return JSON.toJSONString(new PdynamicResponse(pdynamics,"查询用户动态已经返回",1));
    }
    @PostMapping("/getDynamic")
    public String getDynamic(@RequestBody Pdynamic reqPdynamic){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdtitleEqualTo(reqPdynamic.getPdtitle());
        List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
        deSort(pdynamics);
        return JSON.toJSONString(new PdynamicResponse(pdynamics,"查询动态详情已经返回",1));
    }
    @PostMapping("/getDynamicByName")
    public String getDynamicByName(@RequestBody Pdynamic reqPdynamic){
        PdynamicExample pdynamicExample=new PdynamicExample();
        PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
        criteria.andPdtitleLike("%"+reqPdynamic.getPdtitle()+"%");
        List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
        deSort(pdynamics);
        return JSON.toJSONString(new PdynamicResponse(pdynamics,"搜索动态已经返回",1));
    }
    @PostMapping("/addPdynamic")
    public String addPdynamic(@RequestBody Pdynamic reqPdynamic){
        User user=getUser(reqPdynamic.getPdowner());
        if(user!=null){
            PdynamicExample pdynamicExample=new PdynamicExample();
            PdynamicExample.Criteria criteria=pdynamicExample.createCriteria();
            criteria.andPdtitleEqualTo(reqPdynamic.getPdtitle());
            List<Pdynamic>pdynamics=pdynamicService.getPdynamics(pdynamicExample);
            if(pdynamics.isEmpty()){
                pdynamicService.newPdynamic(reqPdynamic);
                return JSON.toJSONString(new PdynamicResponse(reqPdynamic,"发布动态成功",1));
            }
            else {
                return JSON.toJSONString(new PdynamicResponse(reqPdynamic,"动态标题已存在",0));
            }
        }
        else {
            return JSON.toJSONString(new PdynamicResponse(new Pdynamic(),"用户不存在",0));
        }
    }

    //动态名字不能改
    @PostMapping("/updatePdynamic")
    public String updatePdynamic(@RequestBody Pdynamic reqPdynamic){
        Pdynamic pdynamic=getDynamic(reqPdynamic.getPdtitle());
        if(pdynamic!=null) {
            reqPdynamic.setPdid(pdynamic.getPdid());
            pdynamicService.updatePdynamic(reqPdynamic);
            //发送通知
            //1.获取收藏的用户
            List<Pdkeep>pdkeeps;
            PdkeepExample pdkeepExample=new PdkeepExample();
            PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
            criteria.andPdtitleEqualTo(reqPdynamic.getPdtitle());
            pdkeeps=pdkeepService.getPdkeeps(pdkeepExample);
            //2.设置message内容
            Message message=new Message();
            message.setMdate(reqPdynamic.getPdtime());
            message.setMsgtitle(reqPdynamic.getPdtitle()+"更新了");
            if(!pdkeeps.isEmpty()){
                for (int i=0;i<pdkeeps.size();i++){
                    String reciptant=pdkeeps.get(i).getPdkeeper();
                    message.setReciptant(reciptant);
                    message.setMsg("尊敬的用户"+reciptant+","+reqPdynamic.getPdowner()+"更新了动态："+reqPdynamic.getPdtitle());
                    try {
                        messageService.newMessage(message);
                    }catch (SqlSessionException e){
                        e.printStackTrace();
                    }
                }
            }

            return JSON.toJSONString(new PdynamicResponse(reqPdynamic,"更新动态成功",1));
        }
        else {
            return JSON.toJSONString(new PdynamicResponse(new Pdynamic(),"动态不存在",0));
        }
    }

    @PostMapping("/deletePdynamic")
    public String deletePdynamic(@RequestBody Pdynamic reqPdynamic){
        Pdynamic pdynamic=getDynamic(reqPdynamic.getPdtitle());
        if(pdynamic!=null) {
            Integer pdid=pdynamic.getPdid();
            String pdtitle=reqPdynamic.getPdtitle();
            //1.删除所有评论
            PdcommentExample pdcommentExample=new PdcommentExample();
            PdcommentExample.Criteria criteria=pdcommentExample.createCriteria();
            criteria.andPdtitleEqualTo(pdtitle);
            pdcommentService.deletePdcomment(pdcommentExample);
            //2.删除所有点赞
            PdloveExample pdloveExample=new PdloveExample();
            PdloveExample.Criteria criteria1=pdloveExample.createCriteria();
            criteria1.andPdtitleEqualTo(pdtitle);
            pdloveService.deletePdlove(pdloveExample);
            //3.删除所有收藏
            PdkeepExample pdkeepExample=new PdkeepExample();
            PdkeepExample.Criteria criteria2=pdkeepExample.createCriteria();
            criteria2.andPdtitleEqualTo(pdtitle);
            //发送通知
            //1.获取收藏的用户
            List<Pdkeep> pdkeeps=pdkeepService.getPdkeeps(pdkeepExample);
            //2.设置message内容
            Message message=new Message();
            message.setMdate(reqPdynamic.getPdtime());
            message.setMsgtitle(reqPdynamic.getPdtitle()+"被删除了");
            if(!pdkeeps.isEmpty()){
                for (int i=0;i<pdkeeps.size();i++){
                    String reciptant=pdkeeps.get(i).getPdkeeper();
                    message.setReciptant(reciptant);
                    message.setMsg("尊敬的用户"+reciptant+"\n"+reqPdynamic.getPdowner()+"删除了动态："+reqPdynamic.getPdtitle());
                    try {
                        messageService.newMessage(message);
                    }catch (SqlSessionException e){
                        e.printStackTrace();
                    }
                }
            }
            pdkeepService.deletePdkeep(pdkeepExample);
            //4.删除动态
            PdynamicExample pdynamicExample=new PdynamicExample();
            PdynamicExample.Criteria criteria3=pdynamicExample.createCriteria();
            criteria3.andPdidEqualTo(pdid);
            pdynamicService.deletePdynamic(pdynamicExample);
            return JSON.toJSONString(new PdynamicResponse(reqPdynamic,"删除动态成功",1));
        }
        else {
            return JSON.toJSONString(new PdynamicResponse(new Pdynamic(),"动态不存在",0));
        }
    }

    //动态评论
    @PostMapping("/getAllComCount")
    public  String getAllComCount(@RequestBody Pdynamic pdynamic){
        PdcommentExample pdcommentExample=new PdcommentExample();
        PdcommentExample.Criteria criteria=pdcommentExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        int count=(int)pdcommentService.countPdcomments(pdcommentExample);
        return JSON.toJSONString(new PdcommentResponse(new Pdcomment(), Integer.toString(count),1));
    }
    @PostMapping("/getAllPdcomments")
    public  String getAllPdcomments(@RequestBody Pdynamic pdynamic){
        PdcommentExample pdcommentExample=new PdcommentExample();
        PdcommentExample.Criteria criteria=pdcommentExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        //只获取顶层评论
        criteria.andParentEqualTo(0);
        List<Pdcomment>pdcomments=pdcommentService.getPdcomments(pdcommentExample);
        deSortCom(pdcomments);
        return JSON.toJSONString(new PdcommentResponse(pdcomments,"成功获取评论",1));
    }

    @PostMapping("/getSubPdcomments")
    public  String getSubPdcomments(@RequestBody Pdcomment pdcomment){
        PdcommentExample pdcommentExample=new PdcommentExample();
        PdcommentExample.Criteria criteria=pdcommentExample.createCriteria();
        criteria.andParentEqualTo(pdcomment.getComid());
        List<Pdcomment>pdcomments=pdcommentService.getPdcomments(pdcommentExample);
        deSortCom(pdcomments);
        return JSON.toJSONString(new PdcommentResponse(pdcomments,"成功获取子评论",1));
    }
    @PostMapping("/addPdcomment")
    public String addPdcomment(@RequestBody Pdcomment pdcomment){
       Pdynamic pdynamic=getDynamic(pdcomment.getPdtitle());
       User user=getUser(pdcomment.getComowner());
       try{
           assert(pdynamic!=null&&user!=null);
           pdcommentService.newPdcomment(pdcomment);
           return JSON.toJSONString(new PdcommentResponse(pdcomment,"评论成功",1));
       }
       catch (NullPointerException e){
           e.printStackTrace();
           return JSON.toJSONString(new PdcommentResponse(pdcomment,"评论失败",0));
       }
    }
    //必须传入id
    @PostMapping("/deletePdcomment")
    public String deletePdcomment(@RequestBody Pdcomment pdcomment){
        try{
            //1.删除顶层评论
            PdcommentExample pdcommentExample=new PdcommentExample();
            PdcommentExample.Criteria criteria=pdcommentExample.createCriteria();
            criteria.andComidEqualTo(pdcomment.getComid());
            pdcommentService.deletePdcomment(pdcommentExample);
            //2.删除子评论
            PdcommentExample pdcommentExample1=new PdcommentExample();
            PdcommentExample.Criteria criteria1=pdcommentExample1.createCriteria();
            criteria1.andParentEqualTo(pdcomment.getComid());
            pdcommentService.deletePdcomment(pdcommentExample1);
            return JSON.toJSONString(new PdcommentResponse(pdcomment,"删除评论成功",1));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return JSON.toJSONString(new PdcommentResponse(pdcomment,"删除评论失败",0));
        }
    }

    //动态点赞 1：表示已经点赞 0：表示未点赞
    @PostMapping("/isLoved")
    public  String isLoved(@RequestBody Pdlove pdlove){
        PdloveExample pdloveExample=new PdloveExample();
        PdloveExample.Criteria criteria=pdloveExample.createCriteria();
        criteria.andPdtitleEqualTo(pdlove.getPdtitle());
        criteria.andPdloveownerEqualTo(pdlove.getPdloveowner());
        int count=(int) pdloveService.countPdloves(pdloveExample);
        return JSON.toJSONString(count);
    }
    @PostMapping("/getAllPdloves")
    public  String getAllPdloves(@RequestBody Pdynamic pdynamic){
        PdloveExample pdloveExample=new PdloveExample();
        PdloveExample.Criteria criteria=pdloveExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        int count=(int) pdloveService.countPdloves(pdloveExample);
        return JSON.toJSONString(new PdloveResponse(new Pdlove(),Integer.toString(count),1));
    }

    @PostMapping("/addPdlove")
    public String addPdlove(@RequestBody Pdlove pdlove){
        Pdynamic pdynamic=getDynamic(pdlove.getPdtitle());
        User user=getUser(pdlove.getPdloveowner());
        try{
            assert(pdynamic!=null&&user!=null);
            pdloveService.newPdlove(pdlove);
            return JSON.toJSONString(new PdloveResponse(pdlove,"点赞成功",1));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return JSON.toJSONString(new PdloveResponse(pdlove,"点赞失败",0));
        }
    }
    @PostMapping("/deletePdlove")
    public String deletePdlove(@RequestBody Pdlove pdlove){
        Pdynamic pdynamic=getDynamic(pdlove.getPdtitle());
        User user=getUser(pdlove.getPdloveowner());
        try{
            assert(pdynamic!=null&&user!=null);
            PdloveExample pdloveExample=new PdloveExample();
            PdloveExample.Criteria criteria=pdloveExample.createCriteria();
            criteria.andPdtitleEqualTo(pdlove.getPdtitle());
            criteria.andPdloveownerEqualTo(pdlove.getPdloveowner());
            pdloveService.deletePdlove(pdloveExample);
            return JSON.toJSONString(new PdloveResponse(pdlove,"取消点赞成功",1));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return JSON.toJSONString(new PdloveResponse(pdlove,"取消点赞失败",0));
        }
    }

    //动态收藏 1：已经收藏 0：未收藏
    @PostMapping("/isKeeped")
    public  String isKeeped(@RequestBody Pdkeep pdkeep){
        PdkeepExample pdkeepExample=new PdkeepExample();
        PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
        criteria.andPdkeeperEqualTo(pdkeep.getPdkeeper());
        criteria.andPdtitleEqualTo(pdkeep.getPdtitle());
        int count=(int) pdkeepService.countPdkeeps(pdkeepExample);
        return JSON.toJSONString(count);
    }
    @PostMapping("/getAllKeepCount")
    public  String getAllKeepCount(@RequestBody Pdynamic pdynamic){
        PdkeepExample pdkeepExample=new PdkeepExample();
        PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        int count=(int)pdkeepService.countPdkeeps(pdkeepExample);
        return JSON.toJSONString(new PdkeepResponse(new Pdkeep(), Integer.toString(count),1));
    }
    @PostMapping("/getAllPdkeeps")
    public  String getAllPdkeeps(@RequestBody Pdynamic pdynamic){
        PdkeepExample pdkeepExample=new PdkeepExample();
        PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
        criteria.andPdtitleEqualTo(pdynamic.getPdtitle());
        List<Pdkeep>pdkeeps=pdkeepService.getPdkeeps(pdkeepExample);
        deSortKeep(pdkeeps);
        return JSON.toJSONString(new PdkeepResponse(pdkeeps,"成功获取收藏",1));
    }

    @PostMapping("/getMykeeps")
    public  String getMykeeps(@RequestBody User user){
        PdkeepExample pdkeepExample=new PdkeepExample();
        PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
        criteria.andPdkeeperEqualTo(user.getUname());
        List<Pdkeep>pdkeeps=pdkeepService.getPdkeeps(pdkeepExample);
        deSortKeep(pdkeeps);
        return JSON.toJSONString(new PdkeepResponse(pdkeeps,"成功获取个人收藏",1));
    }

    @PostMapping("/addPdkeep")
    public String addPdkeep(@RequestBody Pdkeep pdkeep){
        Pdynamic pdynamic=getDynamic(pdkeep.getPdtitle());
        User user=getUser(pdkeep.getPdkeeper());
        try{
            assert(pdynamic!=null&&user!=null);
            pdkeepService.newPdkeep(pdkeep);
            return JSON.toJSONString(new PdkeepResponse(pdkeep,"收藏成功",1));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return JSON.toJSONString(new PdkeepResponse(pdkeep,"收藏失败",0));
        }
    }
    @PostMapping("/deletePdkeep")
    public String deletePdkeep(@RequestBody Pdkeep pdkeep){
        Pdynamic pdynamic=getDynamic(pdkeep.getPdtitle());
        User user=getUser(pdkeep.getPdkeeper());
        try{
            assert(pdynamic!=null&&user!=null);
            PdkeepExample pdkeepExample=new PdkeepExample();
            PdkeepExample.Criteria criteria=pdkeepExample.createCriteria();
            criteria.andPdtitleEqualTo(pdkeep.getPdtitle());
//            criteria.andPdkeepdataEqualTo(pdkeep.getPdkeepdata());
            criteria.andPdkeeperEqualTo(pdkeep.getPdkeeper());
            pdkeepService.deletePdkeep(pdkeepExample);
            return JSON.toJSONString(new PdkeepResponse(pdkeep,"取消收藏成功",1));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return JSON.toJSONString(new PdkeepResponse(pdkeep,"取消收藏失败",0));
        }
    }
}
