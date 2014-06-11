<!doctype html>
<%@page import="com.pesqol.model.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.pesqol.dao.StudentDao"%>
<html>
<head></head>
<body>
	<%
		StudentDao sd = new StudentDao();
		List<Student> students = sd.getAllStudent();
		for (Student s : students) {
	%>
	<div>
		My name is
		<em><%=s.getName()%></em>
		, and I am
		<em><%=s.getAge()%>year old.
		</em>
		. I study in
		<em><%=s.getSchool()%></em>
		.
	</div>
	<%
		}
	%>
	<form action="student" method="post">
		<input placeholder="Enter Student Name" name="name" />
		<br />
		<input placeholder="Enter Student Age" name="age" />
		<br />
		<input placeholder="Enter Student School" name="school" />
		<br />
		<input type="submit" value="Add Student" />
	</form>
</body>
</html>