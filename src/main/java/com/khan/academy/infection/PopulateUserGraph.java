package com.khan.academy.infection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PopulateUserGraph {

	private Random random = new Random();
	private List<User> headUser = new ArrayList<User>();

	public List<User> getHeadUser() {
		return headUser;
	}



	public List<User> populateUsers(UserData inputData) {
		int headIndex = Integer.MAX_VALUE;
		for (int i = 0; i < inputData.getNumberOfGroups(); i++) {
			User user = new User();
			user.setUserId(headIndex % 10000);
			headIndex--;
			headUser.add(user);

			createRandomUsers(user, inputData.getNumberOfStudentsPerGroup(), 1);
		}
		return headUser;
	}

	public void createRandomUsers(User teacher, int numberOfStudentsPerGroup, int currentStudentsInGroup) {

		for (int i = 0; i < numberOfStudentsPerGroup; i++) {

			if (currentStudentsInGroup > numberOfStudentsPerGroup) {
				currentStudentsInGroup = 0;
				return;
			}

			User student = new User();
			student.setUserId(
					random.nextInt(numberOfStudentsPerGroup) * currentStudentsInGroup + numberOfStudentsPerGroup);
			student.addCoach(teacher);
			teacher.addStudent(student);

			createRandomUsers(student, numberOfStudentsPerGroup, ++currentStudentsInGroup);
		}
	}

	public void printUserGraph() {

		for (User principal : headUser) {
			Map<Integer, Boolean> isVisitedMap = new HashMap<Integer, Boolean>();

			isVisitedMap.put(principal.getUserId(), true);
			System.out.println("User Head ID : " + principal.getUserId());
			recursivelyPrintUserGraph(principal, isVisitedMap);
		}
	}

	private void recursivelyPrintUserGraph(User user, Map<Integer, Boolean> isVisitedMap) {
		for (User student : user.getCoaches()) {
			if (!isVisitedMap.containsKey(student.getUserId()) || !isVisitedMap.get(student.getUserId())) {
				System.out.println(user.getUserId() + " coaches " + student.getUserId());
				isVisitedMap.put(student.getUserId(), true);
				recursivelyPrintUserGraph(student, isVisitedMap);
			}
		}
		for (User teacher : user.getTeachers()) {
			if (!isVisitedMap.containsKey(teacher.getUserId())) {
				System.out.println(teacher.getUserId() + " coaches " + user.getUserId());
				isVisitedMap.put(teacher.getUserId(), true);
				recursivelyPrintUserGraph(teacher, isVisitedMap);
			}
		}
	}

	public static Collection<User> getRelations(User user) {
		HashMap<Integer, User> visited = new HashMap<Integer, User>();
		getRelations(user, visited);
		return visited.values();
	}

	private static void getRelations(User user, HashMap<Integer, User> visited) {
		if (visited.put(user.getUserId(), user) == null) {
			for (User student : user.getCoaches()) {
				getRelations(student, visited);
			}
			for (User teacher : user.getTeachers()) {
				getRelations(teacher, visited);
			}
		}
	}

	public static class UserData {
		private int numberOfGroups;
		private int numberOfStudentsPerGroup;
		private int numberOfUsersForLimitedInfection;
		private int numberOfUsersForExactInfection;

		public UserData(int numberOfGroups, int numberOfStudentsPerGroup, int numberOfUsersForLimitedInfection,
				int numberOfUsersForExactInfection) {
			this.numberOfGroups = numberOfGroups;
			this.numberOfStudentsPerGroup = numberOfStudentsPerGroup;
			this.numberOfUsersForLimitedInfection = numberOfUsersForLimitedInfection;
			this.numberOfUsersForExactInfection = numberOfUsersForExactInfection;
		}

		public int getNumberOfGroups() {
			return numberOfGroups;
		}

		public int getNumberOfStudentsPerGroup() {
			return numberOfStudentsPerGroup;
		}

		public int getNumberOfUsersForLimitedInfection() {
			return numberOfUsersForLimitedInfection;
		}

		public int getNumberOfUsersForExactInfection() {
			return numberOfUsersForExactInfection;
		}

	}
}
