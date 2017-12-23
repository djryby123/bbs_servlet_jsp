package com.djr.dao;

import com.djr.entity.UserInfo;
import com.djr.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAO {

    public void reg(UserInfo ui) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "INSERT INTO userinfo (uName,uPass,head,gender) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,ui.getuName());
        ps.setString(2,ui.getuPass());
        ps.setString(3,ui.getHead());
        ps.setString(4,ui.getGender());
        ps.executeUpdate();
        ps.close();
        DBUtil.closeConn(conn);
    }

    public boolean checkUserName(String uName) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM userinfo WHERE uName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,uName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            rs.close();
            ps.close();
            DBUtil.closeConn(conn);
            return false;
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return true;
    }

    public UserInfo login(String uName,String uPass) throws SQLException {
        UserInfo ui = new UserInfo();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM userinfo WHERE uName = ? AND uPass = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,uName);
        ps.setString(2,uPass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            ui.setuId(rs.getInt("uID"));
            ui.setuName(rs.getString("uName"));
            ui.setuPass(rs.getString("uPass"));
            ui.setHead(rs.getString("head"));
            ui.setGender(rs.getString("gender"));
            ui.setRegTime(rs.getTimestamp("regTime"));
            rs.close();
            ps.close();
            DBUtil.closeConn(conn);
            return ui;
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return null;
    }

    public String findUname(int uId) throws SQLException {
        UserInfo userInfo = new UserInfo();
        String uName = null;
        Connection conn = DBUtil.getConn();
        String sql = "SELECT uName FROM userinfo WHERE uId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,uId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            uName = rs.getString("uName");
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return uName;
    }

    public UserInfo getUserInfo(int uId) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM userinfo WHERE uId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,uId);
        ResultSet rs = ps.executeQuery();
        UserInfo userInfo = new UserInfo();
        if(rs.next()){
            userInfo.setuName(rs.getString("uName"));
            userInfo.setuPass(rs.getString("uPass"));
            userInfo.setHead(rs.getString("head"));
            userInfo.setGender(rs.getString("gender"));
            userInfo.setuId(rs.getInt("uId"));
            userInfo.setRegTime(rs.getTimestamp("regTime"));
        }
        rs.close();
        ps.close();
        DBUtil.closeConn(conn);
        return userInfo;
    }
}
