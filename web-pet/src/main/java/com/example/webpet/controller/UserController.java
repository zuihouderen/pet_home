package com.example.webpet.controller;

import com.alibaba.fastjson.JSON;
import com.example.webpet.entity.*;
import com.example.webpet.response.MessageResponse;
import com.example.webpet.response.PetResponse;
import com.example.webpet.response.UserResponse;
import com.example.webpet.service.MessageService;
import com.example.webpet.service.PetService;
import com.example.webpet.service.UserService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;
    @Autowired
    MessageService messageService;
    @PostMapping("/login")
    public String login(@RequestBody User reqUser) {
        int success = 0;
        String message;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUnameEqualTo(reqUser.getUname());
        List<User> users = userService.getUsers(userExample);
        int size = users.size();
        User user;
        if (size == 1) {
            user = users.get(0);
            if (users.get(0).getPassword().equals(reqUser.getPassword())) {
                success = 1;
                message="登录成功";
            }
            else {
                message="密码错误";
            }
        } else {
            user = new User();
            message=("用户不存在");
        }
        return JSON.toJSONString(new UserResponse(user,message,success));
    }


    /**
     * 用户id和用户名不可改
     * */
    @PostMapping("/updateInfo")
    public String updateInfo(@RequestBody User reqUser){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUidEqualTo(reqUser.getUid());
        List<User> users = userService.getUsers(userExample);
        int size=users.size();
        if (size==1){
            userService.updateUser(reqUser);
            return JSON.toJSONString(new UserResponse(reqUser, "用户信息更新成功",1));
        }
        else {
            return JSON.toJSONString(new UserResponse(reqUser, "用户信息更新失败",0));
        }
    }
    @PostMapping("/getInfo")
    public String getInfo(@RequestBody User reqUser){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUidEqualTo(reqUser.getUid());
        List<User> users = userService.getUsers(userExample);
        int size=users.size();
        if (size==1){
            User user=users.get(0);
            return JSON.toJSONString(new UserResponse(user, "获取用户信息成功",1));
        }
        else {
            return JSON.toJSONString(new UserResponse(reqUser, "获取用户信息失败",0));
        }
    }

    @PostMapping("/logout")
    public String logout(@RequestBody User reqUser){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUnameEqualTo(reqUser.getUname());
        List<User> users = userService.getUsers(userExample);
        int size = users.size();
        if(size==1){
            return JSON.toJSONString(new UserResponse(new User(),reqUser.getUname()+"!,退出登录成功!!",1));
        }
        else {
            return JSON.toJSONString(new UserResponse(reqUser,reqUser.getUname()+"!,退出登录失败!!",0));
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User reqUser){

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUnameEqualTo(reqUser.getUname());
        List<User> users = userService.getUsers(userExample);
        int size = users.size();
        if (size > 0) {
            User user = new User();
            return JSON.toJSONString(new UserResponse(user, "用户名已存在", 0));
        }
        int success=userService.newUser(reqUser);
        UserExample userExample1 = new UserExample();
        UserExample.Criteria criteria1 = userExample1.createCriteria();
        criteria.andUnameEqualTo(reqUser.getUname());
        List<User> users1 = userService.getUsers(userExample);
        User user=users1.get(0);
        return JSON.toJSONString(new UserResponse(user, null,success));

    }

    @PostMapping("/addPet")
    public String addPet( @RequestBody Pet reqPet){

        PetExample petExample=new PetExample();
        PetExample.Criteria criteria=petExample.createCriteria();
        criteria.andPnameEqualTo(reqPet.getPname());
        int count=(int) petService.countPets(petExample);
        if(count>0){
            return JSON.toJSONString(new PetResponse(reqPet,"宠物名已经存在，添加宠物失败",0));
        }
        petService.newPet(reqPet);
        return JSON.toJSONString(new PetResponse(reqPet,"添加宠物成功",1));
    }

    @PostMapping("/deleteMessage")
    public String deleteMessage(@RequestBody Message message){
        try {
            MessageExample messageExample=new MessageExample();
            MessageExample.Criteria criteria=messageExample.createCriteria();
            //获取传入的messageid
            criteria.andMidEqualTo(message.getMid());
            messageService.deleteMessage(messageExample);
            return JSON.toJSONString(new MessageResponse(message,"成功删除通知",1));
        }
        catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new MessageResponse(message,"删除通知失败",0));
        }
    }

    @PostMapping("/cleanMessage")
    public String cleanMessage(@RequestBody Message message){
        try {
            MessageExample messageExample=new MessageExample();
            MessageExample.Criteria criteria=messageExample.createCriteria();
            //获取传入的messagereciptant
            criteria.andReciptantEqualTo(message.getReciptant());
            messageService.deleteMessage(messageExample);
            return JSON.toJSONString(new MessageResponse(message,"成功清空通知",1));
        }
        catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new MessageResponse(message,"清空通知失败",0));
        }
    }
    @PostMapping("/updateMessage")
    public String updateMessage(@RequestBody Message message){
        try {
            messageService.updateMessage(message);
            return JSON.toJSONString(new MessageResponse(message,"成功更新通知状态",1));
        }
        catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new MessageResponse(message,"更新通知失败",0));
        }
    }
    @PostMapping("/getMessages")
    public String getMessages(@RequestBody User user){
        List<Message> messages=null;
        try {
            MessageExample messageExample=new MessageExample();
            MessageExample.Criteria criteria=messageExample.createCriteria();
            criteria.andReciptantEqualTo(user.getUname());
            messages=messageService.getMessages(messageExample);
            messages.sort(new Comparator<Message>() {
                @Override
                public int compare(Message o1, Message o2) {
                    return o2.getMdate().compareTo(o1.getMdate());
                }
            });
            return JSON.toJSONString(new MessageResponse(messages,"获取通知成功",1));
        }
        catch (SqlSessionException e){
            e.printStackTrace();
            return JSON.toJSONString(new MessageResponse(messages,"获取通知失败",0));
        }
    }

}
