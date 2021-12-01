package com.upload.upload_Game.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping(value = "/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String getLogin(){
        Subject sub=SecurityUtils.getSubject();
        if (sub.isAuthenticated()&&sub.getSession().getAttribute("USER_SESSION")!=null){
            return "redirect:/index";
        }
        else{
            return "login";
        }
    }
    @PostMapping(value = "/login")
    public String doLogin(String vrifycode,String username, String password, HttpSession session, Map<String,String> messageMap) {
        String kaptchaId=(String) session.getAttribute("vrifyCode");
        if (!(vrifycode.equals(kaptchaId))){
            messageMap.put("msg","The verification code you entered is incorrect");
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            return "login";
        }
        Subject sub = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            sub.login(token);
        } catch (UnknownAccountException e) {
//            log.error("Login verification of user[{}]is performed. The verification is not passed and the user does not exist", username);
            messageMap.put("msg","The user you logged in does not exist");
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            token.clear();
            return "login";
        }  catch (IncorrectCredentialsException e){
//            log.error("User [{}] was authenticated for login, but the authentication failed, and the password was incorrect",username);
            messageMap.put("msg","the password is wrong. Please log in again");
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            token.clear();
            return "login";
        }
        User user=(User)sub.getSession().getAttribute("USER_SESSION");
        user=userService.getUserById(user.getUid());
//        log.info("User [{}] logged in successfully",username);
        session.setAttribute("user",user);
        return "redirect:/index";
    }

}
