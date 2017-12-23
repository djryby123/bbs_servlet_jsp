package com.djr.dao;

import com.djr.entity.ReplyInfo;
import com.djr.entity.TopicInfo;
import com.djr.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicInfoDAO {
    public void AddNewTopic(TopicInfo topicInfo) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO topicinfo (title,content,uId,boardId) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,topicInfo.getTitle());
        ps.setString(2,topicInfo.getContent());
        ps.setInt(3,topicInfo.getuId());
        ps.setInt(4,topicInfo.getBoardId());
        ps.executeUpdate();
        ps.close();
        DBUtil.closeConn(conn);
    }

    public List<TopicInfo> listTopicInfo(int boardId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM topicinfo WHERE boardId = ? ORDER BY publishTime DESC ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,boardId);
        ResultSet rs = ps.executeQuery();
        List<TopicInfo> list = new ArrayList<TopicInfo>();
        while(rs.next()){
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.setTitle(rs.getString("title"));
            topicInfo.setContent(rs.getString("content"));
            topicInfo.setBoardId(rs.getInt("boardId"));
            topicInfo.setuId(rs.getInt("uId"));
            topicInfo.setTopicId(rs.getInt("topicId"));
            topicInfo.setModifyTime(rs.getTimestamp("modifyTime"));
            topicInfo.setPublishTime(rs.getTimestamp("publishTime"));
            list.add(topicInfo);
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return list;
    }

    public int countTopic(int boardId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT COUNT(*) FROM topicinfo WHERE boardId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,boardId);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        if(rs.next()){
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return count;
    }

    public TopicInfo getLastTopicInfo(int boardId) throws SQLException {
        TopicInfo info = null;
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM topicinfo WHERE boardId = ? ORDER BY publishTime DESC limit 0,1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,boardId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            info = new TopicInfo();
            info.setBoardId(rs.getInt("boardId"));
            info.setContent(rs.getString("content"));
            info.setModifyTime(rs.getTimestamp("modifyTime"));
            info.setPublishTime(rs.getTimestamp("publishTime"));
            info.setTitle(rs.getString("title"));
            info.setTopicId(rs.getInt("topicId"));
            info.setuId(rs.getInt("uId"));
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return info;
    }

    public TopicInfo detailTopicInfo(int topicId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM topicinfo WHERE topicId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,topicId);
        ResultSet rs = ps.executeQuery();
        TopicInfo topicInfo = new TopicInfo();
        if(rs.next()){
            topicInfo.setTitle(rs.getString("title"));
            topicInfo.setContent(rs.getString("content"));
            topicInfo.setBoardId(rs.getInt("boardId"));
            topicInfo.setuId(rs.getInt("uId"));
            topicInfo.setTopicId(rs.getInt("topicId"));
            topicInfo.setModifyTime(rs.getTimestamp("modifyTime"));
            topicInfo.setPublishTime(rs.getTimestamp("publishTime"));
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return topicInfo;
    }

    public List<TopicInfo> getTopicByPage(int boardId,int start) throws SQLException {
        List<TopicInfo> list = new ArrayList<TopicInfo>();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM topicinfo WHERE boardId = ? ORDER BY publishTime DESC limit ?,10";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,boardId);
        ps.setInt(2,start);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.setTitle(rs.getString("title"));
            topicInfo.setContent(rs.getString("content"));
            topicInfo.setBoardId(rs.getInt("boardId"));
            topicInfo.setuId(rs.getInt("uId"));
            topicInfo.setTopicId(rs.getInt("topicId"));
            topicInfo.setModifyTime(rs.getTimestamp("modifyTime"));
            topicInfo.setPublishTime(rs.getTimestamp("publishTime"));
            list.add(topicInfo);
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return list;
    }
}
