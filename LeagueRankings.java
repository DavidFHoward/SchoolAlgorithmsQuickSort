/**
 * LeagueRankings uses Team objects to rank team according to their points, wins, or simply aphabetical order. Teams are gathered through file input 
 * and the rankings are passed out to the terminal.
 */
public class LeagueRankings
{   
    public static void main(String[] args)
    {
        Team team1 = new Team("The Bears", 3, 3, 3);
        Team team2 = new Team("The Ones", 3, 3, 0);

        System.out.println(team1.compareTo(team2));

    }
}