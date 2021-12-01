package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.mapper.Challengemapper;
import com.upload.upload_Game.mapper.Solvemapper;
import com.upload.upload_Game.mapper.Usermapper;
import com.upload.upload_Game.pojo.Challenge;
import com.upload.upload_Game.pojo.ChallengeList;
import com.upload.upload_Game.pojo.ChallengeType;
import com.upload.upload_Game.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ChallengeService")
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private Challengemapper challengemapper;
    @Autowired
    private Usermapper usermapper;
    @Autowired
    private Solvemapper solvemapper;
    @Autowired
    private ChallengeService challengeService;
    @Override
    public ChallengeList listAllChallenges() {
        ChallengeList challengeList = ChallengeList.getInstance();
        for (ChallengeType value : ChallengeType.values()) {
            challengeList.getChallengeListMap().put(value.getTypecode(), challengemapper.listChallengesByType(value.getTypecode()));
        }
        return challengeList;
    }

    @Override
    public Challenge getChallengeById(int cid) {
        return challengemapper.getChallengeById(cid);
    }

    @Override
    public int saveChallenge(Challenge challenge) {
       return challengemapper.updateChallenge(challenge);
    }

    /**
     * cid 需要删除的题目，删除之后，需要将题目从TypeChallengeMap中移除
     * 从数据库移除相关题目信息，将做过这道题的人的solvedNumber-1;
     *由于Sort界面是视图，所以不需要更改rankview
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteChallenge(int cid) {
        Challenge challengeWantToDelete=challengemapper.getChallengeById(cid);
        challengemapper.deleteChallengeById(cid);
        challengeService.listAllChallenges().getChallengeListMap().get(challengeWantToDelete.getType()).remove(challengeWantToDelete);
        usermapper.updateSolvedNumberCauseByChallengeDelete(cid);
        solvemapper.deleteSolvedByCid(cid);
    }

    @Override
    public int addChallenge(Challenge challenge) {
//        challengeService.listAllChallenges().getChallengeListMap().get(challenge.getType()).add(challenge);
        return challengemapper.insertChallenge(challenge);

    }
}
