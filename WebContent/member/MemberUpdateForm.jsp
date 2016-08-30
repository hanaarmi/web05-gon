<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information of member</title>
</head>
<body>
<h1>Information of member</h1>
<form action='update' method='post'>
Number: <input type='text' name='no' value='${member.no}' readonly><br>
Name: <input type='text' name='name' value='${member.name}'><br>
EMail: <input type='text' name='email' value='${member.email}'><br>
Register Date: ${member.createdDate}<br>
<input type='submit' value='Save'>
<input type='button' value='Delete' onclick='location.href="delete?no=${member.no}"'>
<input type='button' value='Cancel' onclick='location.href="list"'>
</form>
</body>
</html>