import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayGround {

	public static void main(String[] args) throws Exception {
		Options o=new Options();
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		Heuristics.setGoalState(goalState);
		 int[] initialState={0,11,9,10,8,7,6,5,4,3,2,1};
		//int[] initialState={1,2,6,4,5,9,7,3,0,10,11,8};
		//int[] initialState={1,0,3,7,5,2,6,4,9,10,11,8};
		//int[] initialState={1,8,6,2,5,11,7,9,3,4,0,10};
		//int[] initialState={2,3,1,5,7,4,10,11,0,9,8,6};
		//int[] initialState={1,2,3,4,5,6,7,8,9,0,10,11};
		Board board=new Board(goalState,initialState,BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP);
		AlgoFactory factory=new AlgoFactory();
		Algo algo=factory.getAlgo( o.diplayOption(), board);
		long startTime=System.currentTimeMillis();
		algo.run();
        Utility.writeGoalTraceToScreen(algo.getGoalNode());
        long endTime=System.currentTimeMillis();
        System.out.println("Total time taken "+(endTime-startTime));
        System.out.println("State explored "+algo.getNumberOfStatesExplored());
        Utility.writeGoalTraceToFile(algo.getGoalNode(),factory.getAlgoName(),factory.getHeuristicsType());
       
	}
}

class Options
{
	public int diplayOption()
	{
		System.out.println("Please select one of the following options");
		System.out.println("1. DFS");
		System.out.println("2. Iterative Deeping DFS");
		System.out.println("3. BFS with Manhattan distance");
		System.out.println("4. BFS with Cheby_Sev_Distance");
		System.out.println("5. AStar with Manhattan distance");
		System.out.println("6. AStar with Cheby_Sev_Distance");
		System.out.println("7. AStar with Manhattan+Linear tiles");
		System.out.println("8. DFS with child check");
		System.out.println("9. BFS with Manhattan distance with child check");
		System.out.println("10.BFS with Cheby_Sev_Distance with child check");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int option=-1;
		try{
			option=Integer.parseInt(br.readLine());
			if(option!=1 && option!=2 && option!=3 && option!=4 && option!=5 && option!=6 && option!=7 && option!=8 && option!=9 && option!=10)
			{
				throw new Exception();
			}
		}
		catch(Exception ex)
		{
			throw new RuntimeException("Please restart the program and enter a valid option");
		}
		return option;
	}
	
	private int[] takeInitialState()
	{
		String[] line=null;
		int[] initialState=new int[12];
		System.out.println("Please enter space seperated initial state. 12 digit");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 try {
			line=br.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 for(int i=0;i< line.length;i++)
		 {
			 initialState[i]=Integer.parseInt(line[i]);
		 }
		
		return initialState;
	}
}


//public static void main(String[] args) throws Exception {
//	int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
//	//int[] initialState={1,2,6,4,5,9,7,3,0,10,11,8}; // Professor
//	int[] initialState={0,11,10,9,8,7,6,5,4,3,2,1};
//	 //int[] initialState={0,11,9,10,8,7,6,5,4,3,2,1};
//	//int[] initialState={1,0,3,7,5,2,6,4,9,10,11,8}; // Professor 2
//	//int[] initialState={1,2,6,4,0,9,7,3,5,10,11,8}; 
//	//int[] initialState={1,2,3,4,5,6,0,8,9,10,11,7};
//	Board board=new Board(goalState,initialState,BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP);
//	Heuristics.setGoalState(goalState);
//	Heuristics.setWhichHeristics(HeuristicsType.Cheby_Shev_DistanceV2);
////	BestFirstSearch bfs=new BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH);
////	bfs.realBfs1();
//	//DFS dfs=new DFS(board);
//    //dfs.go1();
////    //dfs.realDFS();
//	long startTime=System.currentTimeMillis();
//	AStar aStar=new AStar(board);
//	aStar.realAstarOptimize();
//	
////	IterativeDeepingDFS idfs=new IterativeDeepingDFS(board);
////	idfs.iterativeDeepingDFS();
//    Utility.writeGoalTraceToScreen(aStar.getGoalNode());
//    long endTime=System.currentTimeMillis();
//    System.out.println("Total time taken "+(startTime-endTime));
//}


