package com.khan.academy.infection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TotalInfection {

	public UserGraph spreadTotalInfection(User infectionSpreadUser) {
		UserGraph userGraph = new UserGraph();

		Set<User> infectedUsers = new HashSet<User>();
		Map<Integer, Boolean> mapOfUser = new HashMap<Integer, Boolean>();

		Queue<User> queueOfUsers = new LinkedList<User>();
		queueOfUsers.add(infectionSpreadUser);

		while (!queueOfUsers.isEmpty()) {
			User polledUser = queueOfUsers.poll();
			if (!infectedUsers.contains(polledUser) && !mapOfUser.containsKey(polledUser.getUserId())) {
				polledUser.moveUserFromOldToNewVersion();
				infectedUsers.add(polledUser);
				mapOfUser.put(polledUser.getUserId(), true);
			}
			for (User coach : polledUser.getCoaches()) {
				if (!infectedUsers.contains(coach) && !mapOfUser.containsKey(coach.getUserId())) {
					queueOfUsers.add(coach);
				}
			}
			for (User teacher : polledUser.getTeachers()) {
				if (!infectedUsers.contains(teacher) && !mapOfUser.containsKey(teacher.getUserId())) {
					queueOfUsers.add(teacher);
				}
			}
		}

		userGraph.setInfected(infectedUsers);
		return userGraph;
	}
}
