/* Name: David Howard
 * Class: CSCI 3005 Algorithms
 * Instructor: Dr. Smith
 * Date due: 22 February 2023
 */

/**
 * This Team Object that implements the Comparable interface is used in LeagueRankings.java to construct Team objects for Comparison and Sorting
 */
public class Team implements Comparable
{
    private String name;
    private int wins;
    private int draws;
    private int loses;
    private int points;
    /**
     * Simple constructor for a Team Object; handles point assignment automatically
     * @param name the name of the team
     * @param wins how many wins they have
     * @param draws how many draws they have
     * @param loses how many times they lost
     */
    public Team(String name, int wins, int draws, int loses)
    {
        this.name = name;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.points = 3*wins + draws;
    }

    /**
     * tells if this Team Object equals the other Object based on points
     * @return true if the Objects are equal; false otherwise.
     */
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Team)
        {
            Team team = (Team) other;
            return (this.points == team.points);
        }
        return false;
    }
    /**
     * compares two Team objects according to points first, wins second, and finally alphabetical order if needed
     * @param other the generic object to pass for comparison to this Team Object
     * @return possitive integer if this Team object is greater than the other Object, 0 if they are equal, a negative integer otherwise.
     * @throws ClassCastException
     */
    @Override
    public int compareTo(Object other)
    {   
        if (!(other instanceof Team))
            throw new ClassCastException();
        
        Team team = (Team) other;
        if(!this.equals(team)) // if the teams don't have the same number of points
            return this.points - team.points; // return the difference
        else if (!(this.wins == team.wins))// otherwise if the teams dont have the same number of points
            return this.wins - team.wins; // return the difference of wins
        return this.alphabetOrder(team); // if the team are equal in points and wins, just give a number to indicate alphabetical order
    }
    /**
     * This method deturmines the alphabetical ordering of a team according to the name
     * @param team the Team object you would like to get the alphabetical ordering to compared to this object
     * @return possitive integer if this.name comes first. 0 or negative otherwise.
    */
    public int alphabetOrder(Team team)
    {   
        int difference = 0;
        for (int i = 0; difference == 0 && i < this.name.length() && i < team.name.length(); i++) // check each matching index up to the last index of the sortest name
        {
           difference = team.name.toLowerCase().charAt(i) - this.name.toLowerCase().charAt(i);
        }
        return difference; //the difference between the first characters in the name that are different. 0 if there is no different character

    }

    /**
     * Converts data in object to a string
     * @return this Team Object with it variables spelled out on a single line.
     */
    @Override
    public String toString()
    {
        return String.format("%-15s%2d %2d %2d  %2d", name, wins, draws, loses, points);
    }
}
