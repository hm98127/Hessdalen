<%@ page contentType="text/plain;charset=UTF-8" language="java"%>
<%
	int count = (int) request.getAttribute("count");
%>

<%
	if (count > 0) {
%>
{"id" : "false"}
<%
	} else {
%>
{"id" : "true"}
<%
	}
%>

