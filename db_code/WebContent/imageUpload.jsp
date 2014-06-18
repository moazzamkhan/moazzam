<!DOCTYPE html>
<%@page import="com.pesqol.dao.ImageDao"%>
<%@page import="com.pesqol.model.Image"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Upload</title>
</head>
<body>
	<%
		ImageDao imd = new ImageDao();
		List<Image> images = imd.getAllImages();
		for (Image image : images) {
	%>
	<img alt="" src="<%=application.getContextPath() + image.getImagePath()%>">
	<%
		}
	%>
	<form action="uploadImage" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" />
	</form>
</body>
</html>