package com.djr.dao;

import com.djr.entity.ReplyInfo;
import com.djr.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyInfoDAO {

    public List<ReplyInfo> getReplyInfo(int topicId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM replyinfo WHERE topicId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,topicId);
        ResultSet rs = ps.executeQuery();
        List<ReplyInfo> list = new ArrayList<ReplyInfo>();
        while(rs.next()){
            ReplyInfo replyInfo = new ReplyInfo();
            replyInfo.setTitle(rs.getString("title"));
            replyInfo.setContent(rs.getString("content"));
            replyInfo.setPublishTime(rs.getTimestamp("publishTime"));
            replyInfo.setModifyTime(rs.getTimestamp("modifyTime"));
            replyInfo.setuId(rs.getInt("uId"));
            replyInfo.setTopicId(rs.getInt("topicId"));
            list.add(replyInfo);
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return list;

    }

    public int countReply(int topicId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT COUNT(*) FROM replyinfo WHERE topicId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,topicId);
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

    public void AddNewReply(ReplyInfo replyInfo) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO replyinfo (title,content,uId,topicId) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,replyInfo.getTitle());
        ps.setString(2,replyInfo.getContent());
        ps.setInt(3,replyInfo.getuId());
        ps.setInt(4,replyInfo.getTopicId());
        ps.executeUpdate();
        ps.close();
        DBUtil.closeConn(conn);
    }

    public List<ReplyInfo> getReplyByPage(int topicId, int start) throws SQLException {
        List<ReplyInfo> list = new ArrayList<ReplyInfo>();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM replyinfo WHERE topicId = ? ORDER BY publishTime DESC limit ?,10";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,topicId);
        ps.setInt(2,start);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ReplyInfo replyInfo = new ReplyInfo();
            replyInfo.setReplyId(rs.getInt("replyId"));
            replyInfo.setTitle(rs.getString("title"));
            replyInfo.setContent(rs.getString("content"));
            replyInfo.setPublishTime(rs.getTimestamp("publishTime"));
            replyInfo.setModifyTime(rs.getTimestamp("modifyTime"));
            replyInfo.setuId(rs.getInt("uId"));
            replyInfo.setTopicId(rs.getInt("topicId"));
            list.add(replyInfo);
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return list;
    }
}
