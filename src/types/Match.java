package types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import utils.Checkers;


public class Match implements Comparable<Match>{
	private LocalDateTime date;
	private String season,homeTeam, awayTeam;
	private Integer homeGoals, awayGoals;
	private Result result;
	private String referee;
	private Integer homeShotsTarget,awayShotsTarget;
	private boolean hardMatch;
	private Cards cards;
	
	// Constructors with constraints
	
	public Match(String season,String date, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, String result,
			String referee, Integer homeShotsTarget, Integer awayShotsTarget, Integer homeYellowCards,Integer awayYellowCards,
			Integer homeRedCards, Integer awayRedCards) {
		
		Checkers.check("Number of goals cannot be negative.", homeGoals>=0 && awayGoals>=0);
		Checkers.check("Incorrect format for result.", result.equals("H")||result.equals("D")||result.equals("A"));
		Checkers.check("Number of shots cannot be negative.", homeShotsTarget>=0 && awayShotsTarget>=0);
		Checkers.check("Number of cards cannot be negative.", homeYellowCards>=0 && awayYellowCards>=0 && homeRedCards>=0 && awayRedCards>=0);

		this.date = LocalDateTime.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
		this.homeTeam = homeTeam;
		this.season = season;
		this.awayTeam = awayTeam;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.result = Result.valueOf(result);
		this.referee = referee;
		this.homeShotsTarget = homeShotsTarget;
		this.awayShotsTarget = awayShotsTarget;
		Cards cards = new Cards(homeYellowCards,awayYellowCards,homeRedCards,awayRedCards);
		this.cards=cards;
		this.hardMatch = cards.hardMatch();
	}
	
	public Match(String season,String date, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, String result) {
		this(season,date,homeTeam,awayTeam,homeGoals,awayGoals,result,"No data",0,0,0,0,0,0);
	}
	

	// Getters
	
	public LocalDateTime getDate() {
		return date;
	}

	public String getSeason() {
		return season;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public Result getResult() {
		return result;
	}

	public String getReferee() {
		return referee;
	}

	public Integer getHomeShotsTarget() {
		return homeShotsTarget;
	}

	public Integer getAwayShotsTarget() {
		return awayShotsTarget;
	}

	public boolean isHardMatch() {
		return hardMatch;
	}
	
	public Cards getCards() {
		return cards;
	}
	
	public Double getHomeAccuracy() {
		if (getHomeShotsTarget()!=0) {
			return (double) getHomeGoals()/getHomeShotsTarget();
		}
		else {
			return null;
		}
	}
	
	public Double getAwayAccuracy() {
		if (getAwayShotsTarget()!=0) {
			return (double) getHomeGoals()/getAwayShotsTarget();
		}
		else {
			return null;
		}
	}
	
	public ArrayList<String> getListRepresentation(){
		ArrayList<String> rep = new ArrayList<String>();
		for (int i=0; i<getHomeGoals(); i++) {
			rep.add(getHomeTeam());
		}
		for (int i=0; i<getAwayGoals(); i++) {
			rep.add(getAwayTeam());
		}
		return rep;
	}
	
	// toString method
	
	public String toString() {
		if (referee=="No data") {
			return homeTeam + " vs. " + awayTeam + ", "+season+", date of the match: "+date+", result: "+homeGoals+"-"+awayGoals;
		}
		else {
			return homeTeam + " vs. " + awayTeam + ", "+season+", date of the match: "+date+", result: "+homeGoals+"-"+awayGoals+" -- "+ getResult()+", referee: "+referee
				+ ", home team shots on target: "+homeShotsTarget+", away team shots on target: "+awayShotsTarget+", home team accuracy: "+
				String.format("%.3f", getHomeAccuracy())+", away team accuracy: "+String.format("%.3f", getAwayAccuracy())+", hard match? "+hardMatch;
		}
	}

	// Criterion of equality: Two matches are the same if they have the same season, date, homeTeam and awayTeam attributes
	
	@Override
	public int hashCode() {
		return Objects.hash(awayTeam, date, homeTeam, season);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return Objects.equals(awayTeam, other.awayTeam) && Objects.equals(date, other.date)
				&& Objects.equals(homeTeam, other.homeTeam) && Objects.equals(season, other.season);
	}
	
	// Criterion of natural order: 
	
	@Override
	public int compareTo(Match o) {
		int res = Integer.compare(getGoals(),o.getGoals());
		if (res==0) {
			res = Integer.compare(getShots(), o.getShots());
		}
		return res;
	}
	
	private int getGoals() {
		return getHomeGoals()+getAwayGoals();
	}
	private int getShots() {
		return getHomeShotsTarget()+ getAwayShotsTarget();
	}
	
}
