package com.upload.upload_Game.service.serviceImpl;

import com.upload.upload_Game.mapper.Noticemapper;
import com.upload.upload_Game.pojo.Notice;
import com.upload.upload_Game.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private Noticemapper noticemapper;
    @Override
    public int saveNotice(String description) {
        return noticemapper.insertNotice(description);
    }
    @Override
    public int deleteNotice(int nid) {
        return noticemapper.deleteNotice(nid);
    }
    @Override
    public List<Notice> getNoticeList() {
        return noticemapper.listAllNotices();
    }
}
