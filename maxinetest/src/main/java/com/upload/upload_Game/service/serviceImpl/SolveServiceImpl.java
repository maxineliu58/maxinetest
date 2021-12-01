package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.mapper.Challengemapper;
import com.upload.upload_Game.mapper.Solvemapper;
import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.SolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "SolveService")
public class SolveServiceImpl implements SolveService {

    @Autowired
    private Challengemapper challengemapper;
    @Autowired
    private Solvemapper solvemapper;


    @Override
    public void saveNewSolved(Challenge challenge,User user) {
        solvemapper.insertNewSolve(challenge.getCid(),user.getUid());
    }

    @Override
    public int addOneSolvedNumber(int cid) {
       return solvemapper.addOneSolvedNumber(cid);
    }

    @Override
    public int addOneUserSolvedNumber(int uid) {
        return solvemapper.addOneUserSolvedNumber(uid);
    }
}

