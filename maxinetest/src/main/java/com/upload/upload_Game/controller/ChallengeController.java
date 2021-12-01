package com.upload.upload_Game.controller;

import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.ChallengeType;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.ChallengeService;
import com.upload.upload_Game.service.SolveService;
import com.upload.upload_Game.util.ListLinks;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
public class ChallengeController {
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private SolveService solveService;
    @GetMapping("/challenge")
    public ModelAndView getChanllenge(HttpSession session) {
        ModelAndView mv=new ModelAndView("Challenge");
        User user=(User) session.getAttribute("user");
        mv.addObject("user",user);
        ConcurrentHashMap<Integer, List<Challenge>> typeChallengeMap=challengeService.listAllChallenges().getChallengeListMap();
        for (ChallengeType value : ChallengeType.values()) {
            mv.addObject(value.name()+"_SOLVED",user.getSolvedChallenge().get(value.getTypecode()));
        }
        mv.addObject("typeChallengeMap",typeChallengeMap);
        return mv;
    }
    @PostMapping("/search")
    public ModelAndView search(HttpSession session,String url) throws Exception{
        ModelAndView mv=new ModelAndView("Challenge");
        User user=(User) session.getAttribute("user");
        mv.addObject("user",user);
        mv.addObject("url",url);
        List list;
		try {
			list = ListLinks.getAllUrl(url);
			for(int i=0;i<list.size();i++) {
	        	Challenge challenge = (Challenge)list.get(i);
	        	challengeService.addChallenge(challenge);
	        }
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		ConcurrentHashMap<Integer, List<Challenge>> typeChallengeMap=challengeService.listAllChallenges().getChallengeListMap();
		for (ChallengeType value : ChallengeType.values()) {
			mv.addObject(value.name()+"_SOLVED",user.getSolvedChallenge().get(value.getTypecode()));
		}
		mv.addObject("typeChallengeMap",typeChallengeMap);
        
        return mv;
    }
    @PostMapping("/challenge")
    @Transactional(rollbackFor = Exception.class)
    public ModelAndView postChallenge(String vrifycode,int cid, String flag, String msg,HttpSession session){
        Challenge challenge=challengeService.getChallengeById(cid);
        String kaptchaId=(String) session.getAttribute("vrifyCode");
        User user=(User) session.getAttribute("user");
        if (!vrifycode.equals(kaptchaId)){
            msg="sorry, your verification code is wrong";
            ModelAndView mv=getChanllenge(session);
            mv.addObject("msg",msg);
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            return mv;
        }
        if (challenge.getFlag().equals(flag)){
            List<Integer> typeSolvedChallenge=user.getSolvedChallenge().get(challenge.getType());
            if (!typeSolvedChallenge.contains(cid)) {
                try {
                    //need to store the questions we solved,oprate at the database level and operate at the memory level
                    solveService.saveNewSolved(challenge, user);
                    solveService.addOneSolvedNumber(cid);
                    user.setSolvedNumber(user.getSolvedNumber()+1);
                    solveService.addOneUserSolvedNumber(user.getUid());
                    typeSolvedChallenge.add(cid);
                }
                catch (Exception e){
                    msg="an unknown error occurs, please contact Maxine";
                    ModelAndView mv=getChanllenge(session);
                    mv.addObject("msg",msg);
                    return mv;
                }
                msg="good!";
            }
            else {
                msg="you have already tested this, change it";
                session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            }
            ModelAndView mv=getChanllenge(session);
            mv.addObject("msg",msg);
            return mv;
        }
        else{
            ModelAndView mv=getChanllenge(session);
            msg="try it again";
            mv.addObject("msg",msg);
            session.setAttribute("vrifyCode", RandomUtils.nextInt(10000,99999));
            return mv;
        }
    }
}
