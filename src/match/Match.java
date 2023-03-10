package match;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Match implements Comparable<Match>{
	private LocalDateTime date;
	private String season,homeTeam, awayTeam;
	private Integer homeGoals, awayGoals;
	private Result result;
	private String referee;
	private Integer homeShotsTarget,awayShotsTarget;
	private boolean hardMatch;
	
	
	// Constructors
	
	public Match(String season,String date, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, String result,
			String referee, Integer homeShotsTarget, Integer awayShotsTarget, Integer homeYellowCards,Integer awayYellowCards,
			Integer homeRedCards, Integer awayRedCards) {
		
		this.date = LocalDateTime.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
		this.homeTeam = homeTeam;
		this.season = season;
		this.awayTeam = awayTeam;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.result = parse_result(result);
		this.referee = referee;
		this.homeShotsTarget = homeShotsTarget;
		this.awayShotsTarget = awayShotsTarget;
		Cards cards = new Cards(homeYellowCards,awayYellowCards,homeRedCards,awayRedCards);
		this.hardMatch = cards.hardMatch();
	}
	
	public Match(String season,String date, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, String result) {
		this(season,date,homeTeam,awayTeam,homeGoals,awayGoals,result,null,null,null,null,null,null,null);
	}
	
	private Result parse_result(String result) {
		String res = (String) result;
		switch (res) {
		case "H":
				return Result.HOME_WIN;
		case "D":
				return Result.DRAW;
		default:
				return Result.AWAY_WIN;
		}
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
	
	// toString method
	
	@Override
	public String toString() {
		return "Match [date=" + date + ", season=" + season + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", homeGoals=" + homeGoals + ", awayGoals=" + awayGoals + ", result=" + result + ", referee="
				+ referee + ", homeShotsTarget=" + homeShotsTarget + ", awayShotsTarget=" + awayShotsTarget
				+ ", hardMatch=" + hardMatch + "]";
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
