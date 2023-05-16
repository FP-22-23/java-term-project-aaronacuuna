package test;

import types.FactoryMatch;
import types.Match;
import types.Matches;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestMatches {


	public static void main(String[] args) {
		
		Matches mt = FactoryMatch.readMatches("data/newResults.csv");
		
		
	    System.out.println("***********************METHODS INDEX***********************\n"+
	    		"1.- getMatches() \n2.- existsMatchMoreNGoals(Integer n) \n3.- forAllCards() \n4.- countWins(String team) \n"
	    		+ "5.- totalGoalsTeam(String team) \n6.- averageGoalsTeam(String team) \n7.- filterTeam(String team) \n8.- mapRefereeMatches() \n"
	    		+ "9.- mapWinsTeams() \n10.- existsMatchMoreNGoalsST(Integer n) \n11.- forAllCardsST() \n12.- countWinsST(String team) \n"
	    		+ "13.- totalGoalsTeamST(String team) \n14.- averageGoalsTeamST(String team) \n15.- filterTeamST(String team) \n"
	    		+ "16.- mapRefereeMatchesST() \n17.- mapWinsTeamsST() \n18.- matchMoreGoalsYear(Integer year) \n19.- matchesMoreGoalsTeam(String team) \n"
	    		+ "20.- totalNumberGoalsYear() \n21.- mostVictoriesYear() \n22.- nMatchesMoreGoalsSeason(Integer n) \n23.- localTeamMoreVictMonth(Integer month)");
	    System.out.println("Introduce the number of the function that you want to test: ");
	    
	    Scanner sc = new Scanner(System.in);
	    Integer	index = sc.nextInt();
	    sc.close();
	    
	    switch (index) {
	    case 1: testMatches(mt); break;
	    case 2: testExistsMatchMoreNGoals(mt, 10); break;
	    case 3: testForAllCards(mt); break;
	    case 4: testCountWins(mt, "Man United"); break;
	    case 5: testTotalGoalsTeam(mt, "Arsenal"); break;
	    case 6: testAverageGoalsTeam(mt, "Liverpool"); break;
	    case 7: testFilterTeam(mt, "Arsenal"); break;
	    case 8: testMapRefereeMatches(mt); break;
	    case 9: testMapWinsTeams(mt); break;
	    case 10: testExistsMatchMoreNGoalsST(mt, 4);break;
	    case 11: testForAllCardsST(mt); break;
	    case 12: testCountWinsST(mt, "Man United"); break;
	    case 13: testTotalGoalsTeamST(mt, "Arsenal"); break;
	    case 14: testAverageGoalsTeamST(mt, "Liverpool"); break;
	    case 15: testFilterTeamST(mt, "Arsenal"); break;
	    case 16: testMapRefereeMatchesST(mt); break;
	    case 17: testMapWinsTeamsST(mt); break;
	    case 18: testMatchMoreGoalsYear(mt,2004); break; 
	    case 19: testMatchesMoreGoalsTeam(mt, "Arsenal"); break;
	    case 20: testTotalNumberGoalsYear(mt); break;
	    case 21: testMostVictoriesYear(mt); break;
	    case 22: testNMatchesMoreGoalsSeason(mt, 3); break;
	    case 23: testLocalTeamMoreVictMonth(mt, 4); break;
	    default: System.out.println("Please, introduce a correct index."); break;
	    }
	    
	}

	private static void testMatches(Matches mt) {
		System.out.println("Matches: " + mt.getMatches());
	}

	private static void testExistsMatchMoreNGoals(Matches mt, Integer n) {
		System.out.println("There exists a match with more than "+ n + " goals? "+mt.existsMatchMoreNGoals(n));
	}
	
	private static void testForAllCards(Matches mt) {
		System.out.println("Al matches have at least one card? "+mt.forAllCards());
	}
	
	private static void testCountWins(Matches mt, String team) {
		System.out.println(team + " has won a total of "+mt.countWins(team)+" matches.");
	}
	
	private static void testTotalGoalsTeam(Matches mt, String team) {
		System.out.println(team + " has scored a total of "+mt.totalGoalsTeam(team)+" goals.");
	}
	
	private static void testAverageGoalsTeam(Matches mt, String team) {
		System.out.println("Average goals of team " + team + ": " + mt.averageGoalsTeam(team));
	}
	
	private static void testFilterTeam(Matches mt, String team) {
		System.out.println("Matches played by the team " + team + " are: " + mt.filterTeam(team));
	}
	
	// Auxiliary function to print the wins map by teams
	private static void testMapWinsTeams(Matches mt) {
		Map<String, Integer> winsMap = mt.mapWinsTeams();
		System.out.println("Wins map by teams: " + winsMap);
	}

		// Auxiliary function to print the referee matches map
	private static void testMapRefereeMatches(Matches mt) {
		Map<String, List<Match>> refereeMatchesMap = mt.mapRefereeMatches();
		System.out.println("Referee matches map: " + refereeMatchesMap);
	}
	
	//***********************STREAM METHODS TEST***************************
	
	private static void testExistsMatchMoreNGoalsST(Matches mt, Integer n) {
		System.out.println("There exists a match with more than "+ n + " goals? "+mt.existsMatchMoreNGoalsST(n));
	}
	
	private static void testForAllCardsST(Matches mt) {
		System.out.println("Al matches have at least one card? "+mt.forAllCardsST());
	}
	
	private static void testCountWinsST(Matches mt, String team) {
		System.out.println(team + " has won a total of "+mt.countWinsST(team)+" matches.");
	}
	
	private static void testTotalGoalsTeamST(Matches mt, String team) {
		System.out.println(team + " has scored a total of "+mt.totalGoalsTeamST(team)+" goals.");
	}
	
	private static void testAverageGoalsTeamST(Matches mt, String team) {
		System.out.println("Average goals of team " + team + ": " + mt.averageGoalsTeamST(team));
	}
	
	private static void testFilterTeamST(Matches mt, String team) {
		System.out.println("Matches played by the team " + team + " are: " + mt.filterTeamST(team));
	}
	
	// Auxiliary function to print the wins map by teams
	private static void testMapWinsTeamsST(Matches mt) {
		Map<String, Integer> winsMap = mt.mapWinsTeamsST();
		System.out.println("Wins map by teams: " + winsMap);
	}

		// Auxiliary function to print the referee matches map
	private static void testMapRefereeMatchesST(Matches mt) {
		Map<String, List<Match>> refereeMatchesMap = mt.mapRefereeMatchesST();
		System.out.println("Referee matches map: " + refereeMatchesMap);
	}
	
	private static void testMatchMoreGoalsYear(Matches mt, Integer year){
		System.out.println("Match with more goals given the year " + year + ": \n"+mt.matchMoreGoalsYear(year) );
	}
	
	private static void testMatchesMoreGoalsTeam(Matches mt, String team){
		System.out.println("Matches with more goals given the team " + team + ": \n"+mt.matchesMoreGoalsTeam(team) );
	}
	
	private static void testTotalNumberGoalsYear(Matches mt){
		System.out.println("Number of goals per natural year: \n" + mt.totalNumberGoalsYear());
	}
	
	private static void testMostVictoriesYear(Matches mt) {
		System.out.println("Map with the team with most victories per natural year: \n"+ mt.mostVictoriesYear());
	}
	
	private static void testNMatchesMoreGoalsSeason(Matches mt, Integer n) {
		System.out.println("Map with the "+n+" matches with more goals per season: \n"+mt.nMatchesMoreGoalsSeason(n));
	}
	
	private static void testLocalTeamMoreVictMonth(Matches mt, Integer month) {
		System.out.println("Local team with the more victories in the month of "
	+ Month.of(month)+": "+mt.localTeamMoreVictMonth(month));
	}
}
