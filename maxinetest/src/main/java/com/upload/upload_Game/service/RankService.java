package com.upload.upload_Game.service;

import com.upload.upload_Game.pojo.User;

import java.util.List;

public interface RankService {
    List<User> getUserRankList();
    int getUserRankByUid(int uid);
}
