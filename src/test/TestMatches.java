package test;

import types.FactoryMatch;
import types.Match;
import types.Matches;

import java.util.List;
import java.util.Map;

public class TestMatches {

	public static void main(String[] args) {
		
		Matches mt = FactoryMatch.readMatches("data/results.csv");

		//testMatches(mt);
		//testExistsMatchMoreNGoals(mt, 10);
		//testForAllCards(mt);
		//testCountWins(mt, "Man United");
		//testTotalGoalsTeam(mt, "Arsenal");
		//testAverageGoalsTeam(mt, "Liverpool");
		//testFilterTeam(mt, "Arsenal");
		testMapWinsTeams(mt);
		//testMapRefereeMatches(mt);
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
}
