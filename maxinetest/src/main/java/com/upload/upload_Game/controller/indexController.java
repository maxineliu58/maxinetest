package com.upload.upload_Game.controller;

import com.upload.upload_Game.pojo.Notice;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
@Slf4j
@Controller
public class indexController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private SolveService solveService;
    @Autowired
    private RankService rankService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ChallengeService challengeService;
    @GetMapping("/index")
    public ModelAndView getindex(){
        ModelAndView mv=new ModelAndView();
        User user=userService.getUserById(((User)session.getAttribute("user")).getUid());
        session.setAttribute("user",user);
        //getTopFiveUserList
        List<User> userRankList=rankService.getUserRankList();
        List<User> topFive;
        if (userRankList.size()>=5){
            topFive=userRankList.subList(0,5);
        }
        else {
            topFive=userRankList;
        }
        List<Notice> noticeList=noticeService.getNoticeList();
        List<Notice> lastNoticeList=new LinkedList<>();
        if (noticeList.size()>=3){
            lastNoticeList=noticeList.subList(0,3);
        }
        else {
            lastNoticeList=noticeList;
        }

        int userNumber=userService.getUserNumber();
        mv.addObject("userNumber",userNumber);
        mv.addObject("lastNoticeList",lastNoticeList);
        mv.addObject("rank",rankService.getUserRankByUid(user.getUid()));
        mv.addObject("user",user);
        mv.addObject("topFive",topFive);
        mv.setViewName("index");
        return mv;
    }

}
