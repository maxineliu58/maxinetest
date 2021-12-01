package com.upload.upload_Game.service.serviceImpl;


import com.upload.upload_Game.mapper.Challengemapper;
import com.upload.upload_Game.mapper.Rankmapper;
import com.upload.upload_Game.mapper.Usermapper;
import com.upload.upload_Game.pojo.RankList;
import com.upload.upload_Game.pojo.User;
import com.upload.upload_Game.service.RankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "RankService")
public class RankServiceImpl implements RankService{
    private static final Logger log= LoggerFactory.getLogger(RankServiceImpl.class);
    @Autowired
    private Usermapper usermapper;
    @Autowired
    private Rankmapper rankmapper;
    @Autowired
    private Challengemapper challengemapper;

    @Override
    public int getUserRankByUid(int uid) {
        Integer solvedNumber=usermapper.getUserSolvedNumberById(uid);
        RankList[] countUserNumberOfSolvedNumberList=rankmapper.countUserNumberOfSolvedNumber();
        int countAmountUser=1;
        for (RankList rankList : countUserNumberOfSolvedNumberList) {
            if (rankList.getSolvedNumber()<=solvedNumber){
                break;
            }
            else {
                if(rankList.getUserNumber() == uid){
                    break;
                }
                else {
                    countAmountUser++;

                }
            }
        }

        return countAmountUser;
    }

    @Override
    public List<User> getUserRankList() {
        return usermapper.listUsersDescOrderBySolvedNumber();
    }
}
