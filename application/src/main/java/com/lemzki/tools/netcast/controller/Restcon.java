package com.lemzki.tools.netcast.controller;

import com.lemzki.tools.netcast.model.Message;
import com.lemzki.tools.netcast.repo.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class Restcon {

    private Random random= new Random();

    @Autowired
    MessageDao messageDao;

    @GetMapping("/insert")
    public void insertMessage(){
        String number =  random.nextInt(1000) + "+(ABC)";
        String message = random.nextInt(5000)  + " my message is " + random.nextInt(2000);
        Message mess = new Message();
        mess.setNumber(number);
        mess.setMessage(message);

        messageDao.insert(mess);


    }
}
