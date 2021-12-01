package com.upload.upload_Game.controller;

import com.upload.upload_Game.pojo.Notice;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @GetMapping("/notice")
    public ModelAndView getNotice(HttpSession session){
        ModelAndView mv=new ModelAndView("notice");
        User user=(User)session.getAttribute("user");
        mv.addObject("user",user);
        List< Notice > noticeList=noticeService.getNoticeList();
        mv.addObject("noticeList",noticeList);
        return mv;
    }
}
