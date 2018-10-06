

public class PlayGround {

	public static void main(String[] args) throws Exception {
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		//int[] initialState={1,2,6,4,5,9,7,3,0,10,11,8}; // Professor
		// int[] initialState={0,11,10,9,8,7,6,5,4,3,2,1};
		 int[] initialState={0,11,9,10,8,7,6,5,4,3,2,1};
		//int[] initialState={1,0,3,7,5,2,6,4,9,10,11,8}; // Professor 2
		//int[] initialState={1,2,6,4,0,9,7,3,5,10,11,8}; 
		//int[] initialState={1,2,3,4,5,6,0,8,9,10,11,7};
		Board board=new Board(goalState,initialState,BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP);
		Heuristics.setGoalState(goalState);
		Heuristics.setWhichHeristics(HeuristicsType.Cheby_Shev_Distance);
//		BestFirstSearch bfs=new BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH);
//		bfs.realBfs1();
		//DFS dfs=new DFS(board);
        //dfs.go1();
//        //dfs.realDFS();
		long startTime=System.currentTimeMillis();
		AStar aStar=new AStar(board);
		aStar.realAstarOptimize();
		
//		IterativeDeepingDFS idfs=new IterativeDeepingDFS(board);
//		idfs.iterativeDeepingDFS();
        Utility.writeGoalTraceToFile(aStar.getGoalNode());
        long endTime=System.currentTimeMillis();
        System.out.println("Total time taken "+(startTime-endTime));
	}

}


