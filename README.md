# Second Term Project Programming Fundamentals (Curso 2022/23)
Author: Aarón Jesús Acuña Bellido   uvus: CGL7536

## Project folder structure

* **/src**: Directory with the source code.
  * **types**: Package which contains the types of the project.
  * **test**: Package which contains the test classes of the project. 
  * **utils**:  Package which contains utility classes. 
* **/data**: It contains the dataset of the project.
    * **resultados.csv**: Csv file that contains data of all Premier League games.
    
## Estructura del *dataset*

The dataset English Premier League Results can be obtained by the URL (https://www.kaggle.com/datasets/irkaal/english-premier-league-results). Originally it has 23 columns and each row contains data about a game played in the football english league. It has been used a total of 14 columns in the project. Next, the 14 columns used in the project will be described:

* **season**: type String. It indicates the season in which the game has been played.
* **date**: type LocalDateTime. It indicates when the game was played.
* **homeTeam**: type String. It indicates the team playing at home.
* **awayTeam**: type String. It indicates the team playing as a visitor.
* **homeGoals**: type Integer. It indicates the number of goals of the local team.
* **awayGoals**: type Integer. It indicates the number of goals of the away team.
* **result**: type String. It can obtained the following values: H, D, A. "H" meaning home win, "D" draw and "A" away win.
* **referee**: type String. It indicates who refereed in that match.
* **homeShotsTarget**: type Integer. It indicates the number of shots on target of the home team.
* **awayShotsTarget**: type Integer. It indicates the number of shots on target of the away team.
* **homeYellowCards**: type Integer. It indicates the number of yellow cards for the local team.
* **awayYellowCards**: type Integer. It indicates the number of yellow cards for the visiting team.
* **homeRedCards**: type Integer. It indicates the number of red cards for the local team.
* **awayRedCards**: type Integer. It indicates the number of red cards for the visiting team.

## Types implemented

Types implemented in the project are the following: 

### Base Type - Match
It represents a specific football match.
**Properties**:

- _season_, type _String_, consultable. It indicates the season in which the game has been played.
- _date_, type _LocalDateTime_, consultable. It indicates when the game was played.
- _homeTeam_, type _String_, consultable. It indicates the team playing at home.
- _awayTeam_, type _String_, consultable. It indicates the team playing as a visitor.
- _homeGoals_, type _Integer_, consultable. It indicates the number of goals of the local team.
- _awayGoals_, type _Integer_, consultable. It indicates the number of goals of the away team.
- _result_, type _Result_, consultable. It can obtained the following values: HOME_WIN, DRAW and AWAY_WIN.
- _referee_, type _String_, consultable. It indicates who refereed in that match.
- _cards_, type _Cards_, consultable. This auxiliar class compiles the data both yellow and red cards.
- _hardMatch_, type _Boolean_, consultable. If it is true, it indicates that it has been a hard match, meaning that there has been 5 or more yellow cards or two or more red cards in the whole match.
- _homeShotsTarget_, type _Integer_, consultable. It indicates the number of shots on target of the home team.
- _awayShotsTarget_, type _Integer_, consultable. It indicates the number of shots on target of the away team.
- _accuracy_, de tipo _Double_, consultable. Indicates how many goals per shots on target are scored. It is calculated as the number of goals over the number of shots on target.

**Constructors**: 

- C1: It has a parameter for each basic property of the type.
- C2: Crea un objeto de tipo ```Partida``` a partir de los siguientes parámetros: ```String season,String date, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, String result```.

**Restricciones**:
 
- R1: Number of goals cannot be negative.
- R2: result must be "H","D" or "A".
- R3: Number of shots cannot be negative.
- R4: Number of cards cannot be negative.

***Criterion of equality**: Two matches are the same if they have the same season, date, homeTeam and awayTeam attributes

**Criterion of natural order**: Per number of goals and number of shots on target.

#### Auxiliar types

- Result, enumerado. It can obtained the following values: HOME_WIN, DRAW and AWAY_WIN.
- Cards, class. It compiles the information of the number of yellow and red cards both of home team and away team.

