package types;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
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
	public Matches(Collection<Match> sm) {
		matches = (Set<Match>) sm;
	}
	public Set<Match> getMatches(){
		return matches;
	}
	
	public int hashCode() {
		return Objects.hash(matches);
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matches other = (Matches) obj;
		return Objects.equals(matches, other.matches);
	}
	
	public String toString() {
		return "Matches [matches=" + matches + "]";
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
	
	//*******************************THIRD DELIVERY*******************************
	
	//BLOCK I
	
	//1. To choose one of the following two: exists / for everything (the same implemented 
	//in delivery 2, but with streams). I did both :)
	public Boolean existsMatchMoreNGoalsST(Integer n) {
		return matches.stream().anyMatch(x->x.getAwayGoals()+x.getHomeGoals()>n);
	}
	
	public Boolean forAllCardsST() {
		return matches.stream().allMatch(x->x.getCards().getTotalCards()>=1);
	}
	
	//2. To choose one of the following three: counter / sum / average (the same 
	//implemented in delivery 2, but with streams). I did the three methods :)
	public Integer countWinsST(String team) {
		return (int) matches.stream()
				.filter(x->x.getHomeTeam().equals(team)&&x.getResult().equals(Result.H) || x.getAwayTeam().equals(team)&&x.getResult().equals(Result.A))
				.count();
	}
	
	public Integer totalGoalsTeamST(String team) {
		return matches.stream()
	            .filter(m -> m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team))
	            .mapToInt(m -> m.getHomeTeam().equals(team) ? m.getHomeGoals() : m.getAwayGoals())
	            .sum();
	}
	
	public Double averageGoalsTeamST(String team) {
		return matches.stream()
	            .filter(m -> m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team))
	            .mapToInt(m -> m.getHomeTeam().equals(team) ? m.getHomeGoals() : m.getAwayGoals())
	            .average().orElse(0.0);
	}
	
	//3. A selection with filtering (the same implemented in delivery 2, but with streams).
	public Set<Match> filterTeamST(String team){				
		return matches.stream()
				.filter(m->m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team))
				.collect(Collectors.toSet());
	}
	
	//4. A maximum/minimum with filtering.
	public Match matchMoreGoalsYear(Integer year) {
		return matches.stream().filter(x->x.getDate().getYear()==year)
				.max(Comparator.comparingInt(x->x.getAwayGoals()+x.getHomeGoals()))
				.get();
	}
	
	//5. A selection, with filtering and sorting.
	public List<Match> matchesMoreGoalsTeam(String team){
		return matches.stream().filter(x->x.getHomeTeam().equals(team)||x.getAwayTeam().equals(team))
				.sorted(Comparator.comparing(Match::getGoals).reversed())
				.collect(Collectors.toList());
	}
	
	//BLOCK II
	
	//6. One of the methods (4) or (5) implemented in delivery 2, but with streams.
	public Map<String, List<Match>> mapRefereeMatchesST(){
		return matches.stream()
				.collect(Collectors.groupingBy(x->x.getReferee()));
	}
	
	
	public Map<String,Integer> mapWinsTeamsST(){
		return matches.stream()
                .flatMap(m -> Stream.of(m.getHomeTeam(), m.getAwayTeam()))
                .distinct()
                .collect(Collectors.toMap(team -> team, team -> countWinsST(team)));
	}
	
	//7. A method whose implementation is used, either the Collector 
	//collectingAndThen, or the Collector mapping.
	public Map<Integer, Integer> totalNumberGoalsYear(){
		return matches.stream()
				.collect(Collectors.groupingBy(x->x.getDate().getYear(), 
						Collectors.mapping(x->x.getGoals(), Collectors.toList())))
				.entrySet().stream()
				.collect(Collectors.toMap(e->e.getKey(), e->e.getValue().stream().mapToInt(Integer::intValue).sum()));
	}
	
	//8. A method that returns a Map in which the keys are an attribute or a function over an attribute, 
	//and the values are maximum/minimum of the elements that have that value.
	public Map<Integer, String> mostVictoriesYear(){
		return matches.stream()
				.collect(Collectors.groupingBy(x->x.getDate().getYear(),Collectors.mapping(Match::getHomeTeam, Collectors.toList())))
				.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().stream()
						.max(Comparator.comparing(t->countWinsYear(t,e.getKey()))).orElse(null)));
	}
	
	private Integer countWinsYear(String team, Integer year) {
		return (int) matches.stream()
				.filter(x->x.getDate().getYear()==year)
				.filter(x->x.getHomeTeam().equals(team)&&x.getResult().equals(Result.H) || x.getAwayTeam().equals(team)&&x.getResult().equals(Result.A))
				.count();
	}
	
	//9. A method that returns a SortedMap in which the keys are an attribute or a function over an attribute, 
	//and the values are lists with the n best or worst elements that share the value of that attribute (or 
	//function over the attribute).
	public SortedMap<String, List<Match>> nMatchesMoreGoalsSeason(Integer n){
		return matches.stream()
				.collect(Collectors.toMap(
						Match::getSeason,
						x->moreGoalsSeason(x.getSeason(),n),
						(s1, s2) -> s1,
						TreeMap::new));
		}

	private List<Match> moreGoalsSeason(String season, Integer n) {
		return matches.stream()
				.filter(x->x.getSeason().equals(season))
				.sorted(Comparator.comparing(Match::getGoals).reversed())
				.toList()
				.subList(0, n);
	}
	
	//10. A method that calculates a Map and returns the key with the associated value (largest or 
	//smallest) of the entire Map.
	
	public String localTeamMoreVictMonth(Integer month) {
	    return matches.stream()
	            .filter(m -> m.getDate().getMonthValue() == month)
	            .filter(m -> m.getResult().equals(Result.H))
	            .collect(Collectors.groupingBy(Match::getHomeTeam, Collectors.counting()))
	            .entrySet().stream()
	            .max(Map.Entry.comparingByValue())
	            .map(Map.Entry::getKey)
	            .orElse(null);
	}
}
