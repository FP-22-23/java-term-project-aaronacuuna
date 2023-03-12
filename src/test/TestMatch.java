package test;

import types.*;

public class TestMatch {
	public static void main(String[] args) {
		
		Match m1 = new Match("2021-22", "2022-03-19T12:30:00Z", "Aston Villa", "Arsenal", 0, 1, "A", "A Madley", 1, 3, 3, 3, 0, 0);
		Match m2 = new Match("2021-22", "2021-12-16T20:00:00Z", "Liverpool", "Newcastle", 3, 1, "H", "M Dean", 8, 2, 1, 3, 0, 0);
		Match m3 = new Match("1993-94", "1994-05-07T00:00:00Z","Tottenham","QPR",1,2,"A");

		
		System.out.println(m1.toString());		
		System.out.println(m3.toString());
		System.out.println(m3.getAwayAccuracy());
		System.out.println(m3.getListRepresentation());
		System.out.println(m1.compareTo(m2));
		System.out.println(m2.getHomeTeam());
		System.out.println(m1.getAwayTeam());
		System.out.println(m2.getDate().getYear());
		System.out.println(m2.getHomeShotsTarget());
		System.out.println(m3.getSeason());
		System.out.println(m1.getReferee());
		System.out.println(m3.getResult());
		System.out.println(m1.isHardMatch());
		System.out.println(m2.getHomeAccuracy());


		
	}
}
