import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch {
	
	private Board board;
	private Node goalNode = null;
	PriorityQueue<Node> queue=null;
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	
	public BestFirstSearch(Board board, TypeOfEvaluationFuntion ev) {
		this.board = board;
		Comparators comparator=new Comparators(Board.getBlankTileMovementDirection());
		if(ev==TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH)
		{
//			this.queue=new PriorityQueue<>(new NodeComparator(Board.getBlankTileMovementDirection()));	
			this.queue=new PriorityQueue<>(Comparators.getJustHeuristicsComparator());	
		}
		else if(ev==TypeOfEvaluationFuntion.CONSIDER_DEPTH)
		{
			this.queue=new PriorityQueue<>(Comparators.getJustHeuristicsPlusDepthComparator());
		}
		
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
	
	
	public void go()
	{
		queue.add(board.getInitialStateNode());
		Utility.printMatrix(board.getInitialStateNode().getState());
		alreadyKnownChildren.add(board.getInitialStateNode().getCode());
		while (!queue.isEmpty()) {
			Node temp=queue.poll();
//			System.out.println("The node that comes out from priority queue is");
//			Utility.printMatrix(temp.getState(),temp.getMovement().getDirectionName(),temp.getHeristicsValue());
//			System.out.println("--------------------------------------------------------------");
			if (Board.isCurrentStateGoalState(temp.getState())) {
				System.out.println("Got goal State");
				goalNode = temp;
				return;
			}
			ArrayList<Node> children = board.generateChildren(temp);
			for (int i =  0; i <children.size() ; i++) {
				Node t = children.get(i);
				//Utility.printMatrix(t.getState(),t.getMovement().getDirectionName(),t.getHeristicsValue());
				
				if(Board.isCurrentStateGoalState(t.getState()))
				{
					
					System.out.println("Got goal State at children nodes");
					System.out.println("This is the goal state");
					System.out.println(t.getPrintableInfo() +  " "+ t.getMovement().getDirectionName());
					goalNode = t;
					return;
					
				}
//				if( t.getCode().equals("72108391041165"))
//				{
//					boolean dw=alreadyKnownChildren.contains("72198351041160");
//					throw new RuntimeException("HEREREE "+dw);
//				}
//				if(alreadyKnownChildren.contains("72198351041160"))
//				{
//					throw new RuntimeException("HEREREE ");
//				}
				if (!alreadyKnownChildren.contains(t.getCode()) ) {
					queue.add(t);
					alreadyKnownChildren.add(t.getCode());
				}
			}
		}
		
	}
}
