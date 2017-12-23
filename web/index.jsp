<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.djr.entity.BoardInfo" %>
<%@ page import="com.djr.entity.UserInfo" %>
<%@ page import="com.djr.service.BoardInfoService" %>
<%@ page import="com.djr.service.TopicInfoService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.djr.entity.TopicInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.djr.dao.UserInfoDAO" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
    <TITLE>欢迎访问简单小论坛—杜君然</TITLE>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
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
<DIV class="t">
    <TABLE cellSpacing="0" cellPadding="0" width="100%">
        <TR class="tr2" align="center">
            <TD colSpan="2">论坛</TD>
            <TD style="WIDTH: 10%;">主题</TD>
            <TD style="WIDTH: 30%">最后发表</TD>
        </TR>
        <!--       主版块       -->

        <%
            BoardInfoService boardInfoService = new BoardInfoService();
            List<BoardInfo> plist = boardInfoService.getAllFatherBoard();
            for (BoardInfo pBoardInfo : plist) {
        %>
        <TR class="tr3">
            <TD colspan="4"><%=pBoardInfo.getBoardName()%>
            </TD>
        </TR>
        <!--       子版块       -->
        <%
            List<BoardInfo> clist = boardInfoService.getAllChildBoard(pBoardInfo.getBoardId());
            for (BoardInfo cBoardInfo : clist) {
        %>
        <TR class="tr3">
            <TD width="5%">&nbsp;</TD>
            <TH align="left">
                <IMG src="image/board.gif">
                <A href="list.do?boardId=<%=cBoardInfo.getBoardId()%>&currentPage=1"><%=cBoardInfo.getBoardName()%>
                </A>
            </TH>
            <%
                TopicInfoService topicInfoService = new TopicInfoService();
                TopicInfo topicInfo = topicInfoService.getLastTopicInfo(cBoardInfo.getBoardId());
                if (topicInfo != null) {
            %>
            <TD align="center"><%=topicInfoService.countTopic(cBoardInfo.getBoardId())%>
            </TD>
            <TH>
				<SPAN>
					<A href="detail.do?topicId=<%=topicInfo.getTopicId()%>&currentPage=1"><%=topicInfo.getTitle()%> </A>
				</SPAN>
                <BR/>
                <%
                    int uId = topicInfo.getuId();
                    UserInfoDAO userInfoDAO = new UserInfoDAO();
                %>
                <SPAN><%=userInfoDAO.findUname(uId)%></SPAN>
                <SPAN class="gray">[ <%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(topicInfo.getPublishTime())%> ]</SPAN>
            </TH>
            <%
            } else if (topicInfo == null) {
            %>
            <TD align="center">0</TD>
            <TH>
                <SPAN class="red">[暂无人发帖]</SPAN>
            </TH>
            <%
                }
            %>
        </TR>
        <%
                }
            }
        %>
    </TABLE>
</DIV>

<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
    Information Technology Co.,Ltd 版权所有
</CENTER>
</BODY>
</HTML>