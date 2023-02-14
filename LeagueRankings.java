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
    

    public static void swap(List<Team> teams, int i, int j)
    {
        Team temp = teams.get(i);
        teams.set(i, teams.get(j));
        teams.set(j, temp);
        
    }
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
    public static void read(String fileName, List<Team> teams) throws IOException
    {
        Scanner in = new Scanner(new File(fileName));
        Team team;
        String name;
        String stupid;
        int wins;
        int draws;
        int loses;
        int numberOfTeams = in.nextInt();
        in.nextLine();

        while(in.hasNextLine())
        {
            in.useDelimiter(", ");
            name = in.next();
            wins = in.nextInt();
            draws = in.nextInt();
            stupid = in.nextLine();
            loses = Integer.parseInt(stupid.substring(2));
            team = new Team(name, wins, draws, loses);
            teams.add(team);
            

        }
        in.close();
    }
    public static void main(String[] args) throws IOException
    {
        List<Team> teams = new ArrayList<Team>();
        
        read("input.txt", teams);
        quickSort(teams, 0, teams.size() -1);


        for(int i = 0; i < teams.size(); i++)
        {
            System.out.println(teams.get(i));
        }
        

    }
}