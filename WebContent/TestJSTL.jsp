<%@ page import="java.util.ArrayList"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:out value="Hello" /><br>
<c:out value="${null}">Nice</c:out><br>
<c:out value="Hello">Nice</c:out><br>
<c:out value="${null}">${null}</c:out><br>
<br>
<c:set var="username1" value="GON" />
<c:set var="username2" value="JOO" />
${username1}<br>
${username2}<br>
${pageScope.username1}<br>
${requestScope.username1}<br>
<br>
<%!
public static class MyMember {
	int no;
	String name;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
%>
<%
MyMember member = new MyMember();
member.setNo(100);
member.setName("GON");
pageContext.setAttribute("member", member);
%>
${member.name}<br>
<c:set target="${member}" property="name" value="Hambak" />
${member.name}<br>
<h3>Remove the value of storageContext</h3>
<% pageContext.setAttribute("username1", "GON"); %>
<br>${username1}
<c:remove var="username1" />
<br>${username1}
<br>
<c:if test = "${10 > 20}" var = "result1">
10 is bigger than 20
</c:if>
${result1}<br>
<br>
<c:set var="userid" value="Hambak" />
<c:choose>
	<c:when test="${userid == 'GON'}">
		This is GON<br>
	</c:when>
	<c:when test="${userid == 'Hambak'}">
		This is Hambak<br>
	</c:when>
</c:choose>
<br>
<% pageContext.setAttribute("nameList", new String[] {"GON", "JOO", "Hambak"}); %>
<ul>
<c:forEach var="name" items="${nameList}">
	<li>${name}</li>
</c:forEach>
</ul>
<br>
<%
ArrayList<String> nameList3 = new ArrayList<String>();
nameList3.add("GON");
nameList3.add("HAMBAK");
nameList3.add("JJOO");
pageContext.setAttribute("nameList3", nameList3);
%>
<ul>
<c:forEach var="name" items="${nameList3}" begin="1" end="2">
	<li>${name}</li>
</c:forEach>
</ul>
<br>
<c:forEach var="no" begin="0" end="7">
	<li><a href="jstl0${no}.jsp">JSTL Example 0${no}</a></li>
</c:forEach>
<br>
<textarea rows="10" cols="80">
</textarea>
<br>
<fmt:parseDate var="date1" value="2016-07-01" pattern="yyyy-MM-dd" />
<fmt:formatDate value="${date1}" pattern="yyyyMMdd" />
</body>
</html>