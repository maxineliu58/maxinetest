package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.service.RankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankServiceImplTest {
    @Autowired
    private RankService rankService;
    @Test
    public void getUserRankByUid() {
        System.out.println(rankService.getUserRankByUid(1));
    }
    @Test
    public void getRankListTest(){
        rankService.getUserRankList().forEach(a->System.out.println(a.getUsername()));
    }
}