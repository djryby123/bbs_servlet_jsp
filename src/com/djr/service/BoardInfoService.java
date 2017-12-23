package com.djr.service;

import com.djr.dao.BoardInfoDAO;
import com.djr.entity.BoardInfo;

import java.sql.SQLException;
import java.util.List;

public class BoardInfoService  {
    BoardInfoDAO dao = new BoardInfoDAO();

    public List<BoardInfo> getAllFatherBoard() throws SQLException {
        List<BoardInfo> list = dao.getAllFatherBoard();
        return list;
    }

    public List<BoardInfo> getAllChildBoard(int parentId) throws SQLException {
        List<BoardInfo> list = dao.getAllChildBoard(parentId);
        return list;
    }

    public String getBoardName(int boardId) throws SQLException {
        String boardName = dao.getBoardName(boardId);
        return  boardName;
    }
}
