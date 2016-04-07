package com.khan.academy.infection;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int userId;
	private int version;
	private List<User> coaches;
	private List<User> teachers;

	public User() {
		this.coaches = new ArrayList<User>();
		this.teachers = new ArrayList<User>();
		this.version = Constants.OLD_VERSION;
	}

	/**
	 * Adds student to the list of students to which current teacher teaches
	 */
	public void addStudent(User student) {
		this.coaches.add(student);
	}

	/**
	 * Adds teacher for current student
	 */
	public void addCoach(User teacher) {
		this.teachers.add(teacher);
	}

	public void moveUserFromOldToNewVersion() {
		this.setVersion(Constants.NEW_VERSION);
	}

	public void moveUserFromNewToOldVersion() {
		this.setVersion(Constants.OLD_VERSION);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<User> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<User> coaches) {
		this.coaches = coaches;
	}

	public List<User> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<User> teachers) {
		this.teachers = teachers;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.userId == ((User) obj).userId;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
