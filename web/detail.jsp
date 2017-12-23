<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.djr.entity.ReplyInfo" %>
<%@ page import="com.djr.entity.TopicInfo" %>
<%@ page import="com.djr.entity.UserInfo" %>
<%@ page import="com.djr.service.ReplyInfoService" %>
<%@ page import="com.djr.service.TopicInfoService" %>
<%@ page import="com.djr.service.UserInfoService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.djr.util.PageUtil" %>
<%@ page pageEncoding="UTF-8" %>
<%
    int currentPage = 1;
    int boardId = 0;
    int topicId = 0;
    if (request.getParameter("currentPage") != null) {
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    }
    if (request.getParameter("topicId") != null) {
        topicId = Integer.parseInt(request.getParameter("topicId"));
    }
    PageUtil pageUtil = new PageUtil(currentPage, boardId, topicId);
    List<ReplyInfo> replyList = pageUtil.getReplyByPage(topicId);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
    <TITLE>简单小论坛--看贴</TITLE>
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

<!-- 主体 -->
<DIV><br/>
    <!--      导航        -->
    <DIV>
        &gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
        <B><a href="list.do?boardId=${sessionScope.boardId}&uId=${sessionScope.uId}">${sessionScope.boardName}</a></B>
    </DIV>
    <br/>
    <!--      回复、新帖        -->
    <%
        TopicInfoService topicInfoService = new TopicInfoService();
        TopicInfo topicInfo = topicInfoService.detailTopicInfo(topicId);
        UserInfoService userInfoService = new UserInfoService();
        UserInfo userInfo1 = userInfoService.getUserInfo(topicInfo.getuId());
    %>
    <DIV>
        <A href="reply.jsp?topicId=<%=topicId%>"><IMG src="image/reply.gif" border="0" id="td_post1"></A>
        <A href="post.jsp?boardId=${sessionScope.boardId}"><IMG src="image/post.gif" border="0" id="td_post2"></A>
    </DIV>
    <!--         翻 页         -->
    <DIV>
        <a href="detail.jsp?topicId=<%=request.getParameter("topicId")%>&currentPage=<%=pageUtil.getFirstPage()%>">首页</a>
        <a href="detail.jsp?topicId=<%=request.getParameter("topicId")%>&currentPage=<%=pageUtil.getPrePage()%>">上一页</a>|
        <a href="detail.jsp?topicId=<%=request.getParameter("topicId")%>&currentPage=<%=pageUtil.getNextPage()%>">下一页</a>
        <a href="detail.jsp?topicId=<%=request.getParameter("topicId")%>&currentPage=<%=pageUtil.getLastPage()%>">末页</a>
    </DIV>
    <!--      本页主题的标题        -->
    <DIV>
        <TABLE cellSpacing="0" cellPadding="0" width="100%">
            <TR>
                <TH class="h">本页主题: <%=topicInfo.getTitle()%>
                </TH>
            </TR>
            <TR class="tr2">
                <TD>&nbsp;</TD>
            </TR>
        </TABLE>
    </DIV>

    <!--      主题        -->

    <DIV class="t">
        <TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
            <TR class="tr1">
                <TH style="WIDTH: 20%">
                    <B><%=userInfo1.getuName()%>
                    </B>&nbsp;&nbsp;&nbsp;<span class="red">[ 楼主 ]</span><BR/>
                    <img src="image/head/<%=userInfo1.getHead()%>"/><BR/>
                    注册:<%=new SimpleDateFormat("yyyy-MM-dd").format(userInfo1.getRegTime())%><BR/>
                </TH>
                <TH>
                    <H4><%=topicInfo.getTitle()%>
                    </H4>
                    <DIV><%=topicInfo.getContent()%>
                    </DIV>
                    <DIV class="tipad gray">
                        发表：[ <%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(topicInfo.getPublishTime())%> ]
                    </DIV>
                </TH>
            </TR>
        </TABLE>
    </DIV>

    <!--      回复        -->
    <%
        UserInfoService userInfoService1 = new UserInfoService();
        if (replyList != null && replyList.size() != 0) {
            UserInfo uInfo = new UserInfo();
            for (ReplyInfo replyInfo : replyList) {
                uInfo = userInfoService1.getUserInfo(replyInfo.getuId());
    %>
    <DIV class="t">
        <TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
            <TR class="tr1">
                <TH style="WIDTH: 20%">
                    <B><%=uInfo.getuName()%>
                    </B><BR/>
                    <img src="image/head/<%=uInfo.getHead()%>"/><BR/>
                    注册:<%=new SimpleDateFormat("yyyy-MM-dd").format(uInfo.getRegTime())%><BR/>
                </TH>
                <TH>
                    <H4><%=replyInfo.getTitle()%>
                    </H4>
                    <DIV><%=replyInfo.getContent()%>
                    </DIV>
                    <DIV class="tipad gray">
                        发表：[ <%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(replyInfo.getPublishTime())%> ]
                    </DIV>
                </TH>
            </TR>
        </TABLE>
    </DIV>
    <%
        }
    } else {
    %>
    <DIV class="t">
        <TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
            <TR class="tr1">
                <TH style="WIDTH: 100%">
                    <SPAN class="red">[暂无人回帖]</SPAN>
                </TH>
            </TR>
        </TABLE>
    </DIV>
    <%
        }
    %>

</DIV>

<!-- 声明 -->
<BR>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
    Information Technology Co.,Ltd 版权所有
</CENTER>
</BODY>
</HTML>