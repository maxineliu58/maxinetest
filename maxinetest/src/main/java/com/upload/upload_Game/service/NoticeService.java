package com.upload.upload_Game.service;

import com.upload.upload_Game.pojo.Notice;

import java.util.List;

public interface NoticeService {
     int saveNotice(String description);
     int deleteNotice(int nid);
      List<Notice> getNoticeList();

}
