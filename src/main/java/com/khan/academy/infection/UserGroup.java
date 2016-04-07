package com.khan.academy.infection;

import java.util.Collection;

public class UserGroup implements Comparable<UserGroup> {
	private Collection<User> collectionOfUsers;

	public UserGroup(User user) {
		this.collectionOfUsers = PopulateUserGraph.getRelations(user);
	}

	public int getSize() {
		return collectionOfUsers.size();
	}

	public Collection<User> getCollectionOfUsers() {
		return collectionOfUsers;
	}

	public int compareTo(UserGroup g) {
		if (collectionOfUsers.size() < g.getSize()) {
			return -1;
		} else if (collectionOfUsers.size() > g.getSize()) {
			return 1;
		}
		return 0;
	}
}
