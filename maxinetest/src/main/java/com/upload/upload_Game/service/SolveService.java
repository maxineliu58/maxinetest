package com.upload.upload_Game.service;

import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.User;

public interface SolveService {
     void saveNewSolved(Challenge challenge,User user);
     int addOneSolvedNumber(int cid);
     int addOneUserSolvedNumber(int uid);
}
