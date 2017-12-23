<%
    String url = request.getParameter("url");
    session.removeAttribute("userinfo");
    response.sendRedirect(url);
%>