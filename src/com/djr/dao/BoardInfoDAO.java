package com.djr.dao;

import com.djr.entity.BoardInfo;
import com.djr.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardInfoDAO {
    public List<BoardInfo> getAllFatherBoard() throws SQLException {
        List<BoardInfo> list = new ArrayList<BoardInfo>();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM boardinfo WHERE parentId = 0";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            BoardInfo info = new BoardInfo();
            info.setBoardId(rs.getInt("boardId"));
            info.setBoardName(rs.getString("boardName"));
            info.setParentId(rs.getInt("parentId"));
            list.add(info);
        }
        rs.close();
        statement.close();
        DBUtil.closeConn(conn);
        return list;
    }

    public List<BoardInfo> getAllChildBoard(int parentId) throws SQLException {
        List<BoardInfo> list = new ArrayList<BoardInfo>();
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM boardinfo WHERE parentId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,parentId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            BoardInfo info = new BoardInfo();
            info.setBoardId(rs.getInt("boardId"));
            info.setBoardName(rs.getString("boardName"));
            info.setParentId(rs.getInt("parentId"));
            list.add(info);
        }
        rs.close();
        DBUtil.closeConn(conn);
        return list;
    }

    public String getBoardName(int boardId) throws SQLException{
        Connection conn = DBUtil.getConn();
        String sql = "SELECT boardName FROM boardinfo WHERE boardId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,boardId);
        ResultSet rs = ps.executeQuery();
        String boardName = null;
        if(rs.next()){
            boardName = rs.getString("boardName");
        }
        return boardName;
    }
}
