package com.khan.academy.infection;

import java.util.HashSet;
import java.util.Set;

public class UserGraph {

	Set<User> infected;
	Set<User> uninfected;

	public UserGraph() {
		infected = new HashSet<User>();
		uninfected = new HashSet<User>();
	}

	public void addInfected(User user) {
		user.moveUserFromOldToNewVersion();
		infected.add(user);
		uninfected.remove(user);
	}

	public void addUnifected(User user) {
		user.moveUserFromNewToOldVersion();
		uninfected.add(user);
		infected.remove(user);
	}

	public Set<User> getInfected() {
		return infected;
	}

	public void setInfected(Set<User> infected) {
		this.infected = infected;
	}

	public Set<User> getUninfected() {
		return uninfected;
	}

	public void setUninfected(Set<User> uninfected) {
		this.uninfected = uninfected;
	}

}
