package com.pesqol.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pesqol.dao.StudentDao;
import com.pesqol.model.Student;

@u
public class StudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("StudentServlet.doGet()");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String ageString = req.getParameter("age");
		int age = Integer.parseInt(ageString);
		String name = req.getParameter("name");
		String school = req.getParameter("school");

		Student s = new Student();
		s.setAge(age);
		s.setName(name);
		s.setSchool(school);

		StudentDao sd = new StudentDao();
		try {
			sd.addStudent(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("student.jsp").forward(req, resp);
	}
}
