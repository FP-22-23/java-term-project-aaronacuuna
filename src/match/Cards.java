package match;

public class Cards {
	private Integer homeYellowCards, awayYellowCards, homeRedCards, awayRedCards;

	public Cards(Integer homeYellowCards, Integer awayYellowCards, Integer homeRedCards, Integer awayRedCards) {
		this.homeYellowCards = homeYellowCards;
		this.awayYellowCards = awayYellowCards;
		this.homeRedCards = homeRedCards;
		this.awayRedCards = awayRedCards;
	}

	
	
	// Getters
	
	public Integer getHomeYellowCards() {
		return homeYellowCards;
	}

	public Integer getAwayYellowCards() {
		return awayYellowCards;
	}

	public Integer getHomeRedCards() {
		return homeRedCards;
	}

	public Integer getAwayRedCards() {
		return awayRedCards;
	}
	
	// Methods
	
	public boolean hardMatch() {
		if (getHomeYellowCards()+getAwayYellowCards()>=5 ) {
			return true;
		}
		else if(getHomeRedCards()+getAwayRedCards() >=2) {
			return true;
		}
		else return false;
	}



	@Override
	public String toString() {
		return "Cards [homeYellowCards=" + homeYellowCards + ", awayYellowCards=" + awayYellowCards + ", homeRedCards="
				+ homeRedCards + ", awayRedCards=" + awayRedCards + "]";
	}
	
	
}
