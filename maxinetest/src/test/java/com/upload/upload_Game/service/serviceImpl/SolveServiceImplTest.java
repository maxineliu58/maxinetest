package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.SolveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SolveServiceImplTest {
    @Autowired
    private SolveService solveService;
    @Test
    public void solvedNumber() {

    }

    @Test
    public void solveChallenge() {
    }
    @Test
    public void insertSolve(){
        Challenge challenge=new Challenge();
        challenge.setCid(7);
        User user=new User();
        user.setUid(10);
        //solveService.saveNewSolved(challenge,user);
    }
}