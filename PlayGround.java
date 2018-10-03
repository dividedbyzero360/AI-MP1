

public class PlayGround {

	public static void main(String[] args) throws Exception {
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		//int[] initialState={1,2,6,4,5,9,7,3,0,10,11,8}; // Professor
		int[] initialState={0,11,9,10,8,7,6,5,4,3,2,1};
		//int[] initialState={1,0,3,7,5,2,6,4,9,10,11,8}; // Professor 2
		 //int[] initialState={1,2,6,4,0,9,7,3,5,10,11,8}; 
		//1 0 3 7 5 2 6 4 9 10 11 8
		//1 0 3 7 5 2 6 4 9 10 11 8
		//int[] initialState={1,2,3,4,5,6,0,8,9,10,11,7};
		Board board=new Board(goalState,initialState,BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP);
		Heuristics.setGoalState(goalState);
		Heuristics.setWhichHeristics(HeuristicsType.MANHATTAN_DISTANCE);
//		BestFirstSearch bfs=new BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH);
//		bfs.realBfs1();
		//DFS dfs=new DFS(board);
        //dfs.go1();
//        //dfs.realDFS();
		
		AStar aStar=new AStar(board);
		aStar.realAstar();
        Utility.writeGoalTraceToFile(aStar.getGoalNode());
	}

}


//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//System.out.println("Please enter the initial state of the board (0-8) in a comma separated line");
//String[] initialState=br.readLine().split(",");
//System.out.println(initialState);
//Arrays.stream(initialState).forEach(System.out::println);

//Test cases
//int[] initialState={0,1,2,6,4,5,9,7,3,10,11,8};
//int[] initialState={1,0,2,6,4,5,9,7,3,10,11,8};
//int[] initialState={1,2,0,6,4,5,9,7,3,10,11,8};
//int[] initialState={1,2,6,0,4,5,9,7,3,10,11,8};
//int[] initialState={1,2,6,4,0,5,9,7,3,10,11,8};
//int[] initialState={1,2,6,4,5,0,9,7,3,10,11,8};
//int[] initialState={1,2,6,4,5,9,0,7,3,10,11,8};
//int[] initialState={1,2,6,4,5,9,7,0,3,10,11,8};
//int[] initialState={1,2,6,4,5,9,7,3,10,11,8,0};


//Utility.printMatrix(initialState);
		//int[] initialState={1,2,3,4,5,6,7,0,9,10,11,8};
		//int[] initialState={1,2,3,4,5,6,7,8,9,10,0,11};



//Test case 1(Sujay)
//int[] initialState={1,2,3,4,5,6,7,8,9,10,0,11};
//private static int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
