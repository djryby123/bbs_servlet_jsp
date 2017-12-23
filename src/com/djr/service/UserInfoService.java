package com.djr.service;

import com.djr.dao.UserInfoDAO;
import com.djr.entity.UserInfo;

import java.sql.SQLException;

public class UserInfoService {

    UserInfoDAO dao = new UserInfoDAO();

    public void reg(UserInfo ui) throws SQLException{
        dao.reg(ui);
    }

    public String checkUserName(String uName) throws SQLException {
        boolean flag = dao.checkUserName(uName);
        if(flag == true){
            return "true";
        }else{
            return "false";
        }
    }

    public UserInfo login(String uName,String uPass) throws SQLException {
        UserInfo ui = dao.login(uName,uPass);
        return ui;
    }

    public String findUname(int uId) throws SQLException{
        String uName = dao.findUname(uId);
        return uName;
    }

    public UserInfo getUserInfo(int uId) throws SQLException {
        UserInfo userInfo = dao.getUserInfo(uId);
        return userInfo;
    }
}
