<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Member</title>
</head>
<body>
<jsp:include page="/Header.jsp" />
<h1>Register Member</h1>
<form action='add' method='post'>
Name: <input type='text' name='name'><br>
Email: <input type='text' name='email'><br>
Password: <input type='password' name='password'><br>
<input type='submit' value='add'>
<input type='reset' value='cancel'>
</form>
<jsp:include page="/Tail.jsp" />
</body>
</html>