package com.djr.util;

import com.djr.dao.ReplyInfoDAO;
import com.djr.dao.TopicInfoDAO;
import com.djr.entity.ReplyInfo;
import com.djr.entity.TopicInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    //第一页页码
    private int firstPage = 1;
    //最后一页页码
    private int lastPage;
    //上一页
    private int prePage;
    //下一页
    private int nextPage;
    //当前页
    private int currentPage;
    //每页条数
    private int sizeOfPage = 10;

   private TopicInfoDAO topicInfoDAO = new TopicInfoDAO();
   private ReplyInfoDAO replyInfoDAO = new ReplyInfoDAO();

   public PageUtil(int currentPage,int boardId){
       this.currentPage = currentPage;
       this.firstPage = 1;
       try {
           this.lastPage = topicInfoDAO.countTopic(boardId) % sizeOfPage == 0 ? topicInfoDAO.countTopic(boardId)/sizeOfPage :
                   topicInfoDAO.countTopic(boardId)/sizeOfPage+1;
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   public PageUtil(int currentPage,int boardId,int topicId){
       this.currentPage = currentPage;
       this.firstPage = 1;
       try {
           this.lastPage = replyInfoDAO.countReply(topicId) % sizeOfPage == 0 ? replyInfoDAO.countReply(topicId)/sizeOfPage :
                   replyInfoDAO.countReply(topicId)/sizeOfPage+1;
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   public int getPrePage(){
       if(this.currentPage == 1){
           this.prePage = 1;
       }else{
           this.prePage = this.currentPage - 1;
       }
       return this.prePage;
   }

   public int getNextPage(){
       if(this.currentPage == this.lastPage){
           this.nextPage = this.lastPage;
       }else{
           this.nextPage = this.currentPage + 1;
       }
       return this.nextPage;
   }

   public int getFirstPage(){
       return this.firstPage;
   }

   public int getLastPage(){
       return this.lastPage;
   }

   public List<TopicInfo> getTopicByPage(int boardId){
       List<TopicInfo> list = new ArrayList<TopicInfo>();
       int start = (this.currentPage - 1) * sizeOfPage;
       try {
           list = topicInfoDAO.getTopicByPage(boardId,start);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return list;
   }

    public List<ReplyInfo> getReplyByPage(int topicId){
        List<ReplyInfo> list = new ArrayList<ReplyInfo>();
        int start = (this.currentPage - 1) * sizeOfPage;
        try {
            list = replyInfoDAO.getReplyByPage(topicId,start);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
