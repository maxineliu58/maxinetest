package com.upload.upload_Game.service.serviceImpl;


import com.upload.upload_Game.mapper.Solvemapper;
import com.upload.upload_Game.mapper.Usermapper;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.UserService;
import com.upload.upload_Game.util.TypeChallengeResultHandler;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "UserService")
public class UserServiceImpl implements UserService{
    @Autowired
    private Usermapper usermapper;
    @Autowired
    private Solvemapper solvemapper;
    /**
     *
     * @param id
     * @return 完整的user信息，包括其各种类型的题目的解题情况
     */
    @Override
    public User getUserById(Integer id) {
        User user=usermapper.getUserById(id);
        ResultHandler resultHandler=new TypeChallengeResultHandler();
        solvemapper.listTypeGroupSolvedByUserId(id,resultHandler);
        Map<Integer, List<Integer>> typeSolvedChallenges=((TypeChallengeResultHandler) resultHandler).getResults();
        user.setSolvedChallenge(typeSolvedChallenges);
        return user;
    }

    /**
     * @param user 插入的用户
     * @return 改变的行数
     */
    @Override
    public Integer insertUser(User user) {
        return usermapper.insertUser(user);
    }

    @Override
    public Integer getUserNumber() {
        return usermapper.SelectUserNumber();
    }




}
