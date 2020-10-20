<%@ page import="com.kb.www.vo.MemberHistoryVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kb.www.common.Parser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<MemberHistoryVo> list = (ArrayList<MemberHistoryVo>) request.getAttribute("list");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원히스토리</title>
</head>
<body>
<table>
    <tr>
        <td>구분</td>
        <td>발생일시</td>
    </tr>
    <%for (int i = 0; i < list.size(); i++) {%>
    <tr>
        <td>
            <%=Parser.parseMemberEventType(list.get(i).getEvt_type())%>
        </td>
        <td><%=list.get(i).getDttm()%></td>
    </tr>
    <%} %>
</table>
</body>
</html>
