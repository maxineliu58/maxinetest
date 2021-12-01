package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void main() {
     User user=userService.getUserById(1);
        List<Integer> typeSolvedChallenge=user.getSolvedChallenge().get(2);
        System.out.println(typeSolvedChallenge.contains(3));
    }

}