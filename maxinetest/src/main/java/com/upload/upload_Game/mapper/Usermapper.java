package com.upload.upload_Game.mapper;


import com.upload.upload_Game.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * user dao，use mybatis
 */

@Mapper
public interface Usermapper {
    @Select("Select uid,username,password,class_number,email,role,true_name,solved_number from user where username=#{username}")
    public User getUserByUsername(String username);
    @Select("Select uid,username,password,class_number,email,role,true_name,solved_number from user where uid=#{uid}")
    public User getUserById(Integer uid);
    @Insert("Insert into user(username,true_name,password,class_number,email) values (#{username},#{trueName},#{password},#{classNumber}" +
            ",#{email})")
    public Integer insertUser(User user);
    @Select("SELECT count(uid) FROM user")
    public int SelectUserNumber();
    @Select("SELECT solved_number from user where uid=#{uid}")
    public Integer getUserSolvedNumberById(Integer id);
    @Select("SELECT uid,username,password,class_number,email,role,true_name,solved_number from user order by solved_number desc")
    public List<User> listUsersDescOrderBySolvedNumber();
    @Update("UPDATE user set solved_number=solved_number-1 where uid in (SELECT  uid from solve where cid=#{cid})")
    int updateSolvedNumberCauseByChallengeDelete(int cid);

}
