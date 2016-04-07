package com.khan.academy.infection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LimitedInfection {

	public List<User> spreadLimitedInfection(List<User> userHeads, int numberOfTargetUsers) {

		int numberOfTargetUserRemaining = numberOfTargetUsers;

		List<UserGroup> groups = new ArrayList<UserGroup>();
		for (User head : userHeads) {
			groups.add(new UserGroup(head));
		}
		Collections.sort(groups);
		Collections.reverse(groups);

		List<User> usersToInfect = new ArrayList<User>();
		for (int index = 0; index < groups.size(); index++) {
			UserGroup group = groups.get(index);
			if (group.getSize() <= numberOfTargetUserRemaining) {
				movetFromOldVersionToNewVersion(group.getCollectionOfUsers());
				usersToInfect.addAll(group.getCollectionOfUsers());
				numberOfTargetUserRemaining = numberOfTargetUserRemaining - group.getSize();
				groups.remove(index--);

				System.out.println("Total number of infected users are : " + group.getSize());
			}
		}

		if (groups.size() > 0) {
			// Lets try again to check if anything else is possible to be
			// infected
			UserGroup smallestUserGroup = groups.get(groups.size() - 1);
			if (Math.abs(numberOfTargetUserRemaining - smallestUserGroup.getSize()) < numberOfTargetUserRemaining) {
				movetFromOldVersionToNewVersion(smallestUserGroup.getCollectionOfUsers());
				usersToInfect.addAll(smallestUserGroup.getCollectionOfUsers());
			}
		}

		return usersToInfect;

	}

	private void movetFromOldVersionToNewVersion(Collection<User> groupUsers) {
		for (User user : groupUsers) {
			user.moveUserFromOldToNewVersion();
		}
	}
}
