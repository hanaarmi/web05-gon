<%@ page import="spms.vo.Member"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Only Test</title>
</head>
<body>
<%
pageContext.setAttribute("scores", new int[]{90,80,70,100});
%>
${scores[3]}
<br>
<%
List<String> nameList = new LinkedList<String>();
nameList.add("곤빵");
nameList.add("쭈쭈");
nameList.add("함박");
pageContext.setAttribute("nameList", nameList);
%>
${nameList[2]}
<br>
<%
Map<String,String> map = new HashMap<String,String>();
map.put("s01", "고니고니");
map.put("s02", "쭈리쭈리");
map.put("s03", "함바기함박");
pageContext.setAttribute("map", map);
%>
${map.s03}
<br>
<%
pageContext.setAttribute("member",
		new Member().setNo(111)
			.setName("쭈쭈")
			.setEmail("pposaysi@gmail.com"));
%>
${member.name}
<br>
</body>
</html>