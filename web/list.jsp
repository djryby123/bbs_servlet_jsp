<%@ page import="com.djr.entity.TopicInfo" %>
<%@ page import="com.djr.entity.UserInfo" %>
<%@ page import="com.djr.service.ReplyInfoService" %>
<%@ page import="com.djr.service.UserInfoService" %>
<%@ page import="com.djr.util.PageUtil" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%
    int currentPage = 1;
    int boardId = 0;
    if(request.getParameter("currentPage") != null){
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    }
    if(request.getParameter("boardId")!=null){
        boardId = Integer.parseInt(request.getParameter("boardId"));
    }
    PageUtil pageUtil = new PageUtil(currentPage,boardId);
    List<TopicInfo> topicList = pageUtil.getTopicByPage(boardId);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
    <TITLE>简单小论坛--帖子列表</TITLE>
    <META http-equiv=Content-Type content="text/html; charset=gbk">
    <Link rel="stylesheet" type="text/css" href="style/style.css"/>
</HEAD>

<BODY>
<DIV>
    <IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

<DIV class="h">
    <%
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if (userInfo != null) {
    %>
    欢迎您，会员 <%=userInfo.getuName()%>&nbsp;| &nbsp; <A href="reg.jsp">新用户注册</A>&nbsp;| &nbsp;
    <A href="exit.jsp?url=index.jsp">退出登录</A>
    <%} else {%>
    您尚未 <a href="login.jsp">登录</a>
    &nbsp;| &nbsp; <A href="reg.jsp">新用户注册</A> |
    <%}%>
</DIV>


<!--      主体        -->
<DIV>
    <!--      导航        -->
    <br/>
    <DIV>
        &gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
        <B><a href="list.do?boardId=${sessionScope.boardId}&uId=${sessionScope.uId}">${sessionScope.boardName}</a></B>
    </DIV>
    <br/>
    <!--      新帖        -->
    <DIV>
        <A href="post.jsp?boardId=${sessionScope.boardId}"><IMG src="image/post.gif" name="td_post" border="0" id="td_post"></A>
    </DIV>
    <!--         翻 页         -->
    <DIV>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getFirstPage()%>">首页</a>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getPrePage()%>">上一页</a>|
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getNextPage()%>">下一页</a>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getLastPage()%>">末页</a>
    </DIV>

    <DIV class="t">
        <TABLE cellSpacing="0" cellPadding="0" width="100%">
            <TR>
                <TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
            </TR>
            <!--       表 头           -->
            <TR class="tr2">
                <TD>&nbsp;</TD>
                <TD style="WIDTH: 80%" align="center">文章</TD>
                <TD style="WIDTH: 10%" align="center">作者</TD>
                <TD style="WIDTH: 10%" align="center">回复</TD>
            </TR>
            <!--         主 题 列 表        -->
            <%
                for (TopicInfo topicInfo : topicList) {
            %>
            <TR class="tr3">
                <TD><IMG src="image/topic.gif" border=0></TD>
                <TD style="FONT-SIZE: 15px">
                    <A href="detail.do?topicId=<%=topicInfo.getTopicId()%>&currentPage=1"><%=topicInfo.getTitle()%>
                    </A>
                </TD>
                <%
                    UserInfoService userInfoService = new UserInfoService();
                    String uName = null;
                    try {
                        uName = userInfoService.findUname(topicInfo.getuId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ReplyInfoService replyInfoService = new ReplyInfoService();
                    int count = replyInfoService.countReply(topicInfo.getTopicId());
                %>
                <TD align="center"><%=uName%>
                </TD>
                <TD align="center"><%=count%></TD>
            </TR>
            <%
                }
            %>
        </TABLE>
    </DIV>
    <!--            翻 页          -->
    <DIV>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getFirstPage()%>">首页</a>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getPrePage()%>">上一页</a>|
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getNextPage()%>">下一页</a>
        <a href="list.jsp?boardId=${sessionScope.boardId}&currentPage=<%=pageUtil.getLastPage()%>">末页</a>
    </DIV>
</DIV>
<!--             声 明          -->
<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
    Information Technology Co.,Ltd 版权所有
</CENTER>

</BODY>
</HTML>
