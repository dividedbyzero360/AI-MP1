
public class AlgoFactory {
	
	private AlgorithmName algoName;
	private HeuristicsType type;
	public Algo getAlgo(int i,Board board)
	{
		if(i==1)
		{
			algoName=AlgorithmName.DFS;
			return new DFS(board,false); 
		}
		else if(i==2)
		{
			algoName=AlgorithmName.ITERATIVE_DFS;
			return new IterativeDeepingDFS(board);
		}
		else if(i==3)
		{
			algoName=AlgorithmName.DFS;
			return new DFS(board,true); 
		}
		else if(i==4)
		{
			algoName=AlgorithmName.BFS;
			type=HeuristicsType.MisplacedTiles;
			Heuristics.setWhichHeristics(HeuristicsType.MisplacedTiles);
			return new  BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH,false);
		}
		else if(i==5)
		{
			algoName=AlgorithmName.BFS;
			type=HeuristicsType.Cheby_Shev_Distance;
			Heuristics.setWhichHeristics(HeuristicsType.Cheby_Shev_Distance);
			return new  BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH,false);
		}
		
		else if(i==6)
		{
			algoName=AlgorithmName.ASTAR;
			type=HeuristicsType.MisplacedTiles;
			Heuristics.setWhichHeristics(HeuristicsType.MisplacedTiles);
			return new  AStar(board);
		}
		else if(i==7) {
			algoName=AlgorithmName.ASTAR;
			type=HeuristicsType.Cheby_Shev_Distance;
			Heuristics.setWhichHeristics(HeuristicsType.Cheby_Shev_Distance);
			return  new  AStar(board);
		}
		
		else if(i==8)
		{
			algoName=AlgorithmName.BFS;
			type=HeuristicsType.MisplacedTiles;
			Heuristics.setWhichHeristics(HeuristicsType.MisplacedTiles);
			return new  BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH,true);
		}
		else if(i==9){
			algoName=AlgorithmName.BFS;
			type=HeuristicsType.Cheby_Shev_Distance;
			Heuristics.setWhichHeristics(HeuristicsType.Cheby_Shev_Distance);
			return new  BestFirstSearch(board,TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH,true);
			
		}
		else  {
			algoName=AlgorithmName.ASTAR;
			type=HeuristicsType.Linear_Conflict;
			Heuristics.setWhichHeristics(HeuristicsType.Linear_Conflict);
			return  new  AStar(board);
		}
	}
	
	public AlgorithmName getAlgoName()
	{
		return algoName;
	}
	
	public HeuristicsType getHeuristicsType()
	{
		return type;
	}

}
