package com.djr.entity;

public class BoardInfo {

    private int boardId;
    private String boardName;
    private int parentId;

    public BoardInfo(){}

    public BoardInfo(int boardId, String boardName, int parentId){
        this.boardId = boardId;
        this.boardName = boardName;
        this.parentId = parentId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentID) {
        this.parentId = parentID;
    }
}
