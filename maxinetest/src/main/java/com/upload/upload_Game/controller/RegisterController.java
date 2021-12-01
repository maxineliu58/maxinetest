package com.upload.upload_Game.controller;

import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class RegisterController {
//    private static final Logger log= LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private UserService userService;
    @PostMapping(value = "/register")
    public String register(HttpSession session, String vrifycode, User user, Map<String,String> map){
        String kaptchaId=(String) session.getAttribute("vrifyCode");
        if (!vrifycode.equals(kaptchaId)){
            map.put("msg","The verification code you entered is incorrect");
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            return "register";
        }
        try {
            userService.insertUser(user);
        }catch (DuplicateKeyException e){
            map.put("msg","The user name you entered already exists");
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            return "register";
        }
//        log.info("User [{}] has successfully registered",user.getUsername());
        map.put("msg","You have successfully registered. Go to the login page to log in");
        return "register";
    }
    @GetMapping(value = "/register")
    public String getRegister(){
        return "register";
    }
}
