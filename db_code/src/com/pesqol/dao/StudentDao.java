package com.pesqol.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pesqol.db.Database;
import com.pesqol.model.Student;

public class StudentDao {
	public synchronized int addStudent(Student s) throws SQLException {
		Connection c = Database.getConnection();
		String query = "insert into student(id,name,age,school) values(?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, s.getId());
		ps.setString(2, s.getName());
		ps.setInt(3, s.getAge());
		ps.setString(4, s.getSchool());
		return ps.executeUpdate();
	}

	public List<Student> getAllStudent() throws SQLException {
		Connection c = Database.getConnection();
		String query = "select * from student";
		PreparedStatement ps = c.prepareStatement(query);

		ResultSet rs = ps.executeQuery();
		List<Student> students = new ArrayList<Student>();

		while (rs.next()) {
			Student s = new Student();
			s.setId(rs.getString(1));
			s.setName(rs.getString(2));
			s.setAge(rs.getInt(3));
			s.setSchool(rs.getString(4));
			students.add(s);
		}
		return students;
	}

	public static void main(String[] args) throws SQLException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Student s = new Student();
		s.setAge(12);
		s.setName("moazzam");
		s.setSchool("JNU");

		StudentDao sd = new StudentDao();

		System.out.println(sd.addStudent(s));
		List<Student> list = sd.getAllStudent();

		for (Student student : list) {
			sd.printObject(student);
		}
	}

	public void printObject(Object o) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method[] methods = o.getClass().getDeclaredMethods();

		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("get")) {
				System.out.println(method.getName() + ":"
						+ method.invoke(o, null));
			}
		}
	}
}
