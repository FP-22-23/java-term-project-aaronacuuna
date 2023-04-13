package types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import utils.Checkers;

public class FactoryMatch {

	public static Matches readMatches(String fileName) {
		Matches matches = null;
		
		try {
			Stream<Match> mt = Files.lines(Paths.get(fileName))
					.skip(1)
					.map(FactoryMatch::parseLine);
			matches = new Matches(mt);
		} catch(IOException e) {
			System.out.println("Error with the file"+ fileName);
			e.printStackTrace();
		}
		return matches;
	}
	
	public static Match parseLine(String line) {
		String[] values = line.split(",");
		
		//Integer length = values.length;
		//Checkers.check("Format line error", length.equals(23));
		
		String season = values[0].trim();
		String date = values[1].trim();
		String homeTeam = values[2].trim();
		String awayTeam = values[3].trim();
		Integer homeGoals = Integer.valueOf(values[4].trim());
		Integer awayGoals = Integer.valueOf(values[5].trim());
		String result = values[6].trim();
		String referee = values[10].trim();
		Integer homeShotsTarget = parse_format(values[13].trim());
		Integer awayShotsTarget = parse_format(values[14].trim());
		Integer homeYellowCards = parse_format(values[19].trim());
		Integer awayYellowCards = parse_format(values[20].trim());
		Integer homeRedCards = parse_format(values[21].trim());
		Integer awayRedCards = parse_format(values[22].trim());
		
		return new Match(season,date,homeTeam,awayTeam,homeGoals,awayGoals,result,referee,homeShotsTarget,
					awayShotsTarget,homeYellowCards,awayYellowCards,homeRedCards,awayRedCards);
		
	}
	
	private static Integer parse_format(String s) {
		if (s.equals("NA")) {
			return 0;
		} else {
			return Integer.valueOf(s);
		}	
	}
}


/* TWO FORMATS:
 * A) 1993-94,1993-08-14T00:00:00Z,Arsenal,Coventry,0,3,A,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA
 * B) 2007-08,2007-08-15T00:00:00Z,Reading,Chelsea,1,2,A,1,0,H,M Dean,15,15,11,7,4,11,15,16,4,5,1,0
 * 
*/




