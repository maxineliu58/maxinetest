package com.upload.upload_Game.mapper;

import com.upload.upload_Game.pojo.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Noticemapper {
    /**
     * @return Returns a chronologically sorted list of notices (most recent first)
     */
    @Select("SELECT nid,date,description FROM notice ORDER BY date DESC")
    public List<Notice> listAllNotices();
    @Insert("INSERT INTO notice(date,description) values (CURRENT_TIME,#{description})")
    public int insertNotice(String description);
    @Delete("Delete  from notice where nid=#{nid}")
    public  int deleteNotice(int nid);
}
