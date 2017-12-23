package com.djr.service;

import com.djr.dao.TopicInfoDAO;
import com.djr.entity.TopicInfo;

import java.sql.SQLException;
import java.util.List;

public class TopicInfoService {

    TopicInfoDAO topicInfoDAO = new TopicInfoDAO();

    public void AddNewTopic(TopicInfo topicInfo) throws SQLException {
        topicInfoDAO.AddNewTopic(topicInfo);
    }

    public List<TopicInfo> listTopicInfo(int boardId) throws SQLException {
        List<TopicInfo> list = topicInfoDAO.listTopicInfo(boardId);
        return list;
    }

    public int countTopic(int boardId) throws SQLException{
        int count = topicInfoDAO.countTopic(boardId);
        return count;
    }

    public TopicInfo getLastTopicInfo(int boardId) throws SQLException{
        TopicInfo topicInfo = topicInfoDAO.getLastTopicInfo(boardId);
        return topicInfo;
    }

    public TopicInfo detailTopicInfo(int topicId) throws SQLException {
        TopicInfo topicInfo = topicInfoDAO.detailTopicInfo(topicId);
        return topicInfo;
    }
}
