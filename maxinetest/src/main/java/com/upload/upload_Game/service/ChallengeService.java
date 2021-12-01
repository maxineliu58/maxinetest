package com.upload.upload_Game.service;

import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.ChallengeList;

public interface ChallengeService {
    ChallengeList listAllChallenges();
    Challenge getChallengeById(int cid);
    int saveChallenge(Challenge challenge);
    void deleteChallenge(int cid);
    int addChallenge(Challenge challenge);

}
