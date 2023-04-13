package types;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matches {

	private Set<Match> matches;
	
	public Matches() {
		matches = new HashSet<Match>();	
	}
	public Matches(Stream<Match> sm) {
		matches = sm.collect(Collectors.toSet());
	}
	public Matches(Set<Match> sm) {
		matches = sm;
	}
	public Set<Match> getMatches(){
		return matches;
	}
	
	public Integer getNoItems() {
		return matches.size();
	}
	
	public void addMatch(Match m) {
		matches.add(m);
	}
	
	public void addMatches(Collection<Match> m) {
		matches.addAll(m);
	}
	
	public void deleteMatch(Match m) {
		matches.remove(m);
	}
	
	public Boolean existsMatchMoreNGoals(Integer n) {		// returns true if there exists at least one match with more than n goals
		Boolean res = false;
		for (Match m: matches) {
			if (m.getHomeGoals()+m.getAwayGoals()>=n) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Boolean forAllCards() {							// returns false if there is at least one match without cards
		Boolean res = true;
		for (Match m: matches) {
			if (m.getCards().getTotalCards()==0) {
				res = false;
			}
		}
		return res;
	}
	
	public Integer countWins(String team) {					// returns the number of wins a team has
		Integer res = 0;
		for (Match m: matches) {
			if(m.getHomeTeam().equals(team) && m.getResult().equals(Result.H)) {
				res += 1;
			} else {
				if (m.getAwayTeam().equals(team) && m.getResult().equals(Result.A)){
					res += 1;
				}
			}
		}
		return res; 
	}
	
	public Integer totalGoalsTeam(String team) {			// returns the number of goals a team has scored along the years
		Integer res = 0;
		for (Match m: matches) {
			if(m.getHomeTeam().equals(team)) {
				res += m.getHomeGoals();
			} else {
				if (m.getAwayTeam().equals(team)){
					res += m.getAwayGoals();
				}
			}
		}
		return res; 
	}
	
	public Double averageGoalsTeam(String team) {			// returns the average of goals per match of the team
		Double matchesPlayed = 0.0;
		for (Match m: matches) {
			if (m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team)) {
				matchesPlayed+=1;
			}
		}
		return totalGoalsTeam(team)/matchesPlayed;
	}
	
	
	public Set<Match> filterTeam(String team){				// return a set of matches of a given team
		Set<Match> res = new HashSet<Match>();
		for(Match m: matches) {
			if(m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team)) {
				res.add(m);
			}
		}
		return res;
	}
	
	public Map<String, List<Match>> mapRefereeMatches(){	// returns a map in which the keys correspond to the referees and the values to the matches they have refereed
		Map<String, List<Match>> mp = new HashMap<String, List<Match>>();
		for (Match m: matches) {
			if (!m.getReferee().equals("NA")) {
				if (mp.containsKey(m.getReferee())) {
					mp.get(m.getReferee()).add(m);
				} else {
					List<Match> ms = new LinkedList<>();
					ms.add(m);
					mp.put(m.getReferee(), ms);
				}
			}
		}
		return mp;
	}
	
	public Map<String,Integer> mapWinsTeams(){				// returns a map in which the keys correspond to the teams and the values to the total number of victories
		Map<String,Integer> mp = new HashMap<String,Integer>();
		Set<String> teams = new HashSet<String>();
		for (Match m: matches) {
			teams.add(m.getHomeTeam());
			teams.add(m.getAwayTeam());
		}
		for(String team: teams) {
			mp.put(team, countWins(team));
		}
		return mp;
	}
}
