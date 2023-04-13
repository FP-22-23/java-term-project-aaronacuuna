package test;

import types.FactoryMatch;
import types.Matches;

public class TestMatches {

	public static void main(String[] args) {
		
		Matches mt = FactoryMatch.readMatches("data/results.csv");
		//System.out.println(mt.getMatches());
		//System.out.println(mt.averageGoalsTeam("Liverpool"));
		System.out.println(mt.mapWinsTeams());
		//System.out.println(mt.mapRefereeMatches());
	}

}
