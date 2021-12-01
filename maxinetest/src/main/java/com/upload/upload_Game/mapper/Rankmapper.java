package com.upload.upload_Game.mapper;

import com.upload.upload_Game.pojo.RankList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Rankmapper {

    @Select("SELECT solved_number,uid from rankview ORDER BY solved_number desc")
    RankList[] countUserNumberOfSolvedNumber();


}
