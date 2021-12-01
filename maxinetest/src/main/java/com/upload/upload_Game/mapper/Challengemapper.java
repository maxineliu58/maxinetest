package com.upload.upload_Game.mapper;

import com.upload.upload_Game.pojo.Challenge;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Challengemapper {
    @Select("Select title,cid,flag,type,link,description,solved_number From challenge where type=#{type}")
    public List<Challenge> listChallengesByType(int type);
    @Select("Select title,cid,flag,type,link,description,solved_number From challenge where cid=#{cid}")
    public Challenge getChallengeById(int cid);
    @Update("UPDATE challenge set title=#{title},flag=#{flag},description=#{description},link=#{link} WHERE cid=#{cid}")
    public int updateChallenge(Challenge challenge);
    @Delete("DELETE  FROM challenge where cid =#{cid}")
    public int deleteChallengeById(int cid);
    @Insert("Insert into challenge(title,flag,description,link,type) values (#{title},#{flag},#{description},#{link},#{type})")
    public int insertChallenge(Challenge challenge);
}
