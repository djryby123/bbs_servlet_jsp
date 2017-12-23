package com.djr.controller;

import com.djr.entity.ReplyInfo;
import com.djr.entity.TopicInfo;
import com.djr.entity.UserInfo;
import com.djr.service.BoardInfoService;
import com.djr.service.ReplyInfoService;
import com.djr.service.TopicInfoService;
import com.djr.service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SimpleControllerServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String str = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

        //注册登录控制逻辑
        if ("login".equals(str)) {
            response.sendRedirect("login.jsp");//点击登录重定向到登录界面
        } else if ("reg".equals(str)) {
            response.sendRedirect("reg.jsp");//点击注册重定向到注册页面
        } else if ("doReg".equals(str)) {  //注册时获得前台参数
            UserInfo ui = new UserInfo();
            ui.setuName(request.getParameter("uName").trim());
            ui.setuPass(request.getParameter("uPass"));
            ui.setHead(request.getParameter("head"));
            ui.setGender(request.getParameter("gender"));
            UserInfoService uis = new UserInfoService();
            try {
                uis.reg(ui);  //调用后台注册方法
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().write("true"); //写出“true”
        } else if ("checkUserName".equals(str)) {  //检查用户名是否已被注册
            UserInfoService uis = new UserInfoService();
            String uName = request.getParameter("uName");
            uName = uName.trim();
            String flag = null;
            try {
                flag = uis.checkUserName(uName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().write(flag);
        } else if ("doLogin".equals(str)) {  //登录
            UserInfo ui = null;
            String uName = request.getParameter("uName");
            String uPass = request.getParameter("uPass");
            UserInfoService uis = new UserInfoService();
            try {
                ui = uis.login(uName, uPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ui != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userinfo", ui);
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        }

        //发起一个新话题控制逻辑
        if ("list".equals(str)) {  //列出某一板块的全部话题
            TopicInfoService topicInfoService = new TopicInfoService();
            BoardInfoService boardInfoService = new BoardInfoService();
            String boardId = request.getParameter("boardId");
            String uId = request.getParameter("uId");
            HttpSession session = request.getSession();
            session.setAttribute("uId", uId);
            session.setAttribute("boardId", boardId);
            List<TopicInfo> list = null;
            String boardName = null;
            try {
                list = topicInfoService.listTopicInfo(Integer.parseInt(boardId));
                boardName = boardInfoService.getBoardName(Integer.parseInt(boardId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("topicInfoList", list);
            session.setAttribute("boardName", boardName);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } else if ("publish".equals(str)) {  //发起一个新话题
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.setuId(Integer.parseInt(request.getParameter("uId")));
            topicInfo.setBoardId(Integer.parseInt(request.getParameter("bId")));
            topicInfo.setTitle(request.getParameter("title"));
            topicInfo.setContent(request.getParameter("content"));
            TopicInfoService topicInfoService = new TopicInfoService();
            try {
                topicInfoService.AddNewTopic(topicInfo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //展示帖子和回复帖子控制逻辑
        if ("detail".equals(str)) {
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } else if ("reply".equals(str)) {
            ReplyInfo replyInfo = new ReplyInfo();
            replyInfo.setuId(Integer.parseInt(request.getParameter("uId")));
            replyInfo.setTopicId(Integer.parseInt(request.getParameter("tId")));
            replyInfo.setTitle(request.getParameter("title"));
            replyInfo.setContent(request.getParameter("content"));
            ReplyInfoService replyInfoService = new ReplyInfoService();
            try {
                replyInfoService.AddNewReply(replyInfo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
