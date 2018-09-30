import java.util.*;
public class Heuristics {

	private static int[] goalState;
	
	private static boolean cached=false;
	private static HashMap<Integer,ArrayList<Integer>> numbersThatFollowANumberInGoalState=new HashMap<Integer,ArrayList<Integer>>();
	private static HeuristicsType whichHeristics;
	
	
	public static final void setGoalState(int[] goalState)
	{
		Heuristics.cached=false;
		Heuristics.goalState=goalState;
	}
	
	public static final void setWhichHeristics(HeuristicsType whichHeristics)
	{
		Heuristics.whichHeristics=whichHeristics;
		Heuristics.cached=false;
	}
	
	public static final int getHerirsticValue(int[] state)
	{
		if(whichHeristics==HeuristicsType.MisplacedTiles)
		{
			return Heuristics.numberOfMisplacedTitlesSlow(state);
		}
		else if(whichHeristics==HeuristicsType.SUM_OF_PI)
		{
			return Heuristics.sumPermutationInversionSlow(state);
		}
		else
			return Heuristics.manhattanDistance(state);
	}
	
	//h1
	public static final int numberOfMisplacedTitlesSlow(int[] state)
	{
		int noOfMisplacedTitles=0;
		for(int i=0; i< state.length;i++)
		{
			if(goalState[i]!=0 && goalState[i]!=state[i]){
				noOfMisplacedTitles++;
			}
		}
		return noOfMisplacedTitles;
	}
	
	//h2
	public static final  int sumPermutationInversionSlow(int[] state)
	{
		if(!cached)
		{
			System.out.println("Runs once only");
			 int goalStateLength=goalState.length;
			 for(int i=goalStateLength-1; i>=0;i--)
			 {
				 if(goalState[i]!=0)
				 {
					 numbersThatFollowANumberInGoalState.put(goalState[i], new ArrayList<Integer>());
					 for(int j=i-1;j>=0;j--)
					 {
						 if(goalState[j]!=0)
						 {
							 numbersThatFollowANumberInGoalState.get(goalState[i]).add(goalState[j]);
						 }
					 }
					 
				 }
			 }
			cached=true;
		}
		int sumOfPerInv=0;
		for(int i=0;i <state.length-1;i++)
		{
			if(state[i]!=0)
			{
				ArrayList<Integer> shouldBeOnItsLeft=numbersThatFollowANumberInGoalState.get(state[i]);
				int count=0;
				for(int j=i+1;j<state.length;j++)
				{
					if(shouldBeOnItsLeft.contains(state[j]))
					{
						count++;
					}
				}
				sumOfPerInv+=count;
			}
		}
		return sumOfPerInv;
	}
	
	
	
	
	
	
	
	//h3  Not generic
	public static final int manhattanDistance(int[] state)
	{
		int md=0;
		return md;
	}
	
}
