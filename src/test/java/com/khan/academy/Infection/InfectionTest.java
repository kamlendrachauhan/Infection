package com.khan.academy.Infection;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.khan.academy.infection.ExactInfection;
import com.khan.academy.infection.LimitedInfection;
import com.khan.academy.infection.PopulateUserGraph;
import com.khan.academy.infection.PopulateUserGraph.UserData;
import com.khan.academy.infection.TotalInfection;
import com.khan.academy.infection.User;
import com.khan.academy.infection.UserGraph;

public class InfectionTest {
	List<User> populatedUsers;
	PopulateUserGraph populateUserGraph;
	UserData parsedData;

	@Before
	public void setUp() throws Exception {
		// To run other test cases just change the indexnumber in
		// inputfile_(indexnumber).txt
		String inputFile = "/input/inputfile_1.txt";

		populateUserGraph = new PopulateUserGraph();
		parsedData = parseAndInterpretData(inputFile);
		populatedUsers = populateUserGraph.populateUsers(parsedData);

	}

	private PopulateUserGraph.UserData parseAndInterpretData(String inputFile) {

		InputStream resourceAsStream = this.getClass().getResourceAsStream(inputFile);
		PopulateUserGraph.UserData userData = null;
		Scanner scanner = null;
		try {
			scanner = new Scanner(resourceAsStream);
			String numberOfGroups = scanner.nextLine();
			String numberOfStudentsPerGroup = scanner.nextLine();
			String numberOfUsersForLimitedInfection = scanner.nextLine();
			String numberOfUsersForExactInfection = scanner.nextLine();
			userData = new PopulateUserGraph.UserData(Integer.parseInt(numberOfGroups),
					Integer.parseInt(numberOfStudentsPerGroup), Integer.parseInt(numberOfUsersForLimitedInfection),
					Integer.parseInt(numberOfUsersForExactInfection));
		} catch (Exception exception) {
			// TODO put logger here
		} finally {
			scanner.close();
		}
		return userData;
	}

	@Test
	public void testTotalInfection() {
		populateUserGraph.printUserGraph();

		TotalInfection totalInfection = new TotalInfection();
		UserGraph spreadTotalInfection = totalInfection.spreadTotalInfection(populateUserGraph.getHeadUser().get(0));
		System.out.println("Users with following UserId's are considered for total infection :");
		for (User user : spreadTotalInfection.getInfected()) {
			System.out.println(user.getUserId());
		}
		System.out.println("---------------------");

	}

	@Test
	public void testLimitedInfection() {
		populateUserGraph.printUserGraph();

		LimitedInfection limitedInfection = new LimitedInfection();
		List<User> listOfLimitedInfecitonUsers = limitedInfection.spreadLimitedInfection(
				populateUserGraph.getHeadUser(), parsedData.getNumberOfUsersForLimitedInfection());
		System.out.println("Users with following UserId's are considered for limited infection :");
		for (User user : listOfLimitedInfecitonUsers) {
			System.out.println(user.getUserId());
		}
		System.out.println("---------------------");

	}

	@Test
	public void testExactInfection() {
		populateUserGraph.printUserGraph();
		ExactInfection exactInfection = new ExactInfection();
		List<User> listOfExactInfecitonUsers = exactInfection.spreadExactInfection(populateUserGraph.getHeadUser(),
				parsedData.getNumberOfUsersForExactInfection());
		if (!listOfExactInfecitonUsers.isEmpty()) {
			System.out.println("Users with following UserId's are considered for limited infection :");
			for (User user : listOfExactInfecitonUsers) {
				System.out.println(user.getUserId());
			}
		}
		System.out.println("---------------------");

	}
}
