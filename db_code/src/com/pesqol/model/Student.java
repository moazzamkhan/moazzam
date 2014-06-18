package com.pesqol.model;

import java.util.UUID;

import com.pesqol.db.annotation.Constraint;

public class Student {
	private String id;
	private String name;
	private int age;
	private String school;
	private boolean male;

	public Student() {
		setId(UUID.randomUUID().toString());
	}

	@Constraint(PRIMARY_KEY = true, NOT_NULL = true, UNIQUE = true)
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Constraint(CHECK = "age>5")
	public int getAge() {
		return age;
	}

	public String getSchool() {
		return school;
	}

	public boolean isMale() {
		return male;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

}
