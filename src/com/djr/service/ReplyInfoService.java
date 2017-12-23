package com.djr.service;

import com.djr.dao.ReplyInfoDAO;
import com.djr.entity.ReplyInfo;
import com.djr.entity.TopicInfo;

import java.sql.SQLException;
import java.util.List;

public class ReplyInfoService {

    ReplyInfoDAO dao = new ReplyInfoDAO();

    public List<ReplyInfo> getReplyInfo(int topicId) throws SQLException {
        List<ReplyInfo> list = (List<ReplyInfo>)dao.getReplyInfo(topicId);
        return  list;
    }

    public int countReply(int topicId) throws SQLException {
        int count = dao.countReply(topicId);
        return count;
    }

    public void AddNewReply(ReplyInfo replyInfo) throws SQLException{
        dao.AddNewReply(replyInfo);
    }
}
