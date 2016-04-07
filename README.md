# Project Infection

Implemented three infection models as part of the specification provided with respected to feature roll-out model.

## Instructions to test
This is a maven based project and runs with the help of junits.

To run the test cases go to the src/test/java package and run the InfectionTest.java.
Various test cases are provided in the src/test/resources/input folder, named as inputfile_1.txt etc.

To change the default testcase (inputfile_1.txt) please go to the file InfectionTest.java and change the inputFile name to the required test case file name.

## Results
### A Sample Result Set for the input test case 
#### Input :: 
      1: Number of groups
      2: Approximate Number of users per group
      3: Number of users for limited infection
      4: Number of users for exact infection

#### Output ::
      User Head ID : 3647
      3647 coaches 2
      2 coaches 4
      Total number of infected users are : 3
      Users with following UserId's are considered for limited infection :
      3647
      2
      4
      ---------------------
      User Head ID : 3647
      3647 coaches 2
      2 coaches 4
      Users with following UserId's are considered for total infection :
      2
      4
      3647
      ---------------------
      User Head ID : 3647
      3647 coaches 2
      2 coaches 4
      It is not possible to infect exacatly 4 users
      ---------------------
    
## Future Extention
 - To integrate GraphViz library to provide a visualization of how the infection affects the userbase.
