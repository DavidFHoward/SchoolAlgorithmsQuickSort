/* Name: Olivia Howard
 * Class: CSCI 3005 Algorithms
 * Instructor: Dr. Smith
 * Date due: 22 February 2023
 */

/**
 * LeagueRankings uses Team objects to rank team according to their points, wins, or simply aphabetical order. Teams are gathered through file input 
 * and the rankings are passed out to the terminal.
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


public class LeagueRankings
{  
    
    /**
     * Swaps the values located at the provided index in the provided list
     * @param teams the list you are swithing values in
     * @param i the first index you are switching
     * @param j the second index you are switching
     */
    public static void swap(List<Team> teams, int i, int j)
    {
        Team temp = teams.get(i);
        teams.set(i, teams.get(j));
        teams.set(j, temp);
        
    }
    /**
     * Swaps the items according to league standards for high rankings and returns the pivot point for the next recursive quicksort calls
     * @param teams the list you are sorting
     * @param low the lower index you are sorting
     * @param high the index you would like to sort up to
     * @return the index of the pivot point
     */
    public static int partition(List<Team> teams, int low, int high)
    {   
        int i, j;
        Team pivotItem = teams.get(low);
        int pivotPoint;
        j = low;
        for (i = low + 1; i <= high; i++)
        {
            if(pivotItem.compareTo(teams.get(i)) < 0)
            {
                j++;
                swap(teams, i, j);
            }
            
        }
        pivotPoint = j;
        swap(teams, low, pivotPoint);
        return pivotPoint;
    }
    /**
     * recursive quicksort algorithm
     * @param teams the list you are sorting
     * @param low the lowest index you would like to sort from
     * @param high the highest index you would like to sort to (this value is included in the sort)
     */
    public static void quickSort(List<Team> teams, int low, int high)
    {
        int pivotPoint = low;

        if(low < high)
        {
            pivotPoint = partition(teams, low, high);
            quickSort(teams, low, pivotPoint - 1);
            quickSort(teams, pivotPoint + 1, high);
            
        }
    } 
    /**
     * Reads the file and stores Team objects n the provided list
     * @param fileName the name of the file you would like to read from; example: "input.txt"
     * @param teams the list that you would like to store the Team objects in
     * @throws IOException
     */
    public static void read(String fileName, List<Team> teams) throws IOException
    {
        Scanner in = new Scanner(new File(fileName));
        String name; // name of the team
        String tail; // end of the line that contains number of loses
        int wins;// the number of wins
        int draws;//the number of draws
        int loses; // the number of loses
        in.nextLine(); // start reading after the first line

        while(in.hasNextLine())
        {
            in.useDelimiter(", ");
            name = in.next();
            wins = in.nextInt();
            draws = in.nextInt();
            tail = in.nextLine(); // for some reason Scanner doen't like reading ints at the end of the line so I used a string called tail to extract it
            loses = Integer.parseInt(tail.substring(2));
            teams.add(new Team(name, wins, draws, loses));
            

        }
        in.close();
    }
    /**
     * This main method reads from an input file and produces rankings for each of the teams according to league criteria. Specifically the team with the most points ranks higher;
     * in the case there is a tie in points then the team with the most wins ranks higher. Finnally if both teams have the same points and wins they are ordered alphabeticcally
     * @param args serves no purpose but to satisfy java's convention
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        List<Team> teams = new ArrayList<Team>(); // list for our team objects
        
        read("input.txt", teams);
        quickSort(teams, 0, teams.size() -1);


        for(int i = 0; i < teams.size(); i++)  //print the results
        {
            System.out.println(teams.get(i));
        }
        

    }
}