package test;

import match.*;

public class TestMatch {
	public static void main(String[] args) {
		Match m1 = new Match("2021-22", "2022-03-19T12:30:00Z", "Aston Villa", "Arsenal", 0, 1, "A", "A Madley", 1, 3, 3, 3, 3, 3);
		
		System.out.println(m1.toString());
	}
}
