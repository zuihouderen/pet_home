package com.example.webpet.service;

import com.example.webpet.entity.Message;
import com.example.webpet.entity.MessageExample;
import com.example.webpet.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    public List<Message> getMessages(MessageExample messageExample){
        return messageMapper.selectByExample(messageExample);
    }

    public long countMessages(MessageExample messageExample) {
        return messageMapper.countByExample(messageExample);
    }

    public int newMessage(Message message) {
        return messageMapper.insertSelective(message);
    }

    public int deleteMessage(MessageExample messageExample) {
        return messageMapper.deleteByExample(messageExample);
    }

    public int updateMessage(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }
}
