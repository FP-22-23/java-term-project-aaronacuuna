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
	
	public Boolean existsMatchGoals(Integer n) {
		Boolean res = false;
		for (Match m: matches) {
			if (m.getHomeGoals()+m.getAwayGoals()>=n) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Boolean forAllCards() {
		Boolean res = true;
		for (Match m: matches) {
			if (m.getCards().getTotalCards()==0) {
				res = false;
			}
		}
		return res;
	}
	
	public Integer countWins(String team) {
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
	
	public Integer totalGoalsTeam(String team) {
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
	
	public Double averageGoalsTeam(String team) {
		Double matchesPlayed = 0.0;
		for (Match m: matches) {
			if (m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team)) {
				matchesPlayed+=1;
			}
		}
		return totalGoalsTeam(team)/matchesPlayed;
	}
	
	
	public Set<Match> filterTeam(String team){
		Set<Match> res = new HashSet<Match>();
		for(Match m: matches) {
			if(m.getHomeTeam()==team || m.getAwayTeam()==team) {
				res.add(m);
			}
		}
		return res;
	}
	
	public Map<String, List<Match>> mapRefereeMatches(){
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
	
	public Map<String,Integer> mapWinsTeams(){
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
