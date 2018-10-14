import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch implements Algo{
	
	private Board board;
	private Node goalNode = null;
	PriorityQueue<Node> queue=null;
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	private boolean childCheckAlgo=false;
	private int numberOfStatesExplored=0;
	public BestFirstSearch(Board board, TypeOfEvaluationFuntion ev, boolean childCheckAlgo) {
		this.board = board;
		new Comparators(Board.getBlankTileMovementDirection());
		if(ev==TypeOfEvaluationFuntion.DONOT_CONSIDER_DEPTH)
		{
//			this.queue=new PriorityQueue<>(new NodeComparator(Board.getBlankTileMovementDirection()));	
			this.queue=new PriorityQueue<>(Comparators.getJustHeuristicsComparator());	
		}
		else if(ev==TypeOfEvaluationFuntion.CONSIDER_DEPTH)
		{
			this.queue=new PriorityQueue<>(Comparators.getJustHeuristicsPlusDepthComparator());
		}
		this.childCheckAlgo=childCheckAlgo;
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
	
	public void run() {
		if(!childCheckAlgo)
		{
			realBfs1();	
		}else{
			go();
		}
		
	}
	public int getNumberOfStatesExplored()
	{
		return numberOfStatesExplored;
	}
	
	
	public void go()
	{
		queue.add(board.getInitialStateNode());
		Utility.printMatrix(board.getInitialStateNode().getState());
		alreadyKnownChildren.add(board.getInitialStateNode().getCode());
		//int x=0;
		while (!queue.isEmpty()) {
			numberOfStatesExplored++;
			//x++;
			Node temp=queue.poll();
//			System.out.println("The node that comes out from priority queue is");
//			if(x==10)
//			{
//				return;
//			}
//			if(temp.getParent()!=null)
//			{
//				Utility.printMatrix(temp.getParent().getState(),temp.getParent().getMovement().getDirectionName(),temp.getParent().getHeristicsValue());
//			}
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
	
	
	public void realBfs() {
		queue.add(board.getInitialStateNode());
		Utility.printMatrix(board.getInitialStateNode().getState());
		alreadyKnownChildren.add(board.getInitialStateNode().getCode());
		while (!queue.isEmpty()) {
			numberOfStatesExplored++;
			Node temp = queue.poll();
			if (Board.isCurrentStateGoalState(temp.getState())) {
				System.out.println("Got goal State");
				goalNode = temp;
				return;
			}
			ArrayList<Node> children = board.generateChildren(temp);
			for (int i = 0; i < children.size(); i++) {
				Node t = children.get(i);
				if (!alreadyKnownChildren.contains(t.getCode())) {
					queue.add(t);
					alreadyKnownChildren.add(t.getCode());
				}
			}
		}

	}
	
	
	public void realBfs1() {
		queue.add(board.getInitialStateNode());
		while (!queue.isEmpty()) {
			numberOfStatesExplored++;
			Node temp = queue.poll();
			if (Board.isCurrentStateGoalState(temp.getState())) {
				System.out.println("Got goal State");
				goalNode = temp;
				return;
			}
			alreadyKnownChildren.add(temp.getCode());
			ArrayList<Node> children = board.generateChildren(temp);
			for (int i = 0; i < children.size(); i++) {
				Node t = children.get(i);
				if (!alreadyKnownChildren.contains(t.getCode()) && !queue.contains(t)) {
					queue.add(t);
				}
			}
		}

	}
}
