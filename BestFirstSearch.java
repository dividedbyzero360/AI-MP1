import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch {
	
	private Board board;
	private Node goalNode = null;
	PriorityQueue<Node> queue=new PriorityQueue<>(new NodeComparator());
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	
	public BestFirstSearch(Board board) {
		this.board = board;
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
	
	
	public void go()
	{
		queue.add(board.getInitialStateNode());
		alreadyKnownChildren.add(board.getInitialStateNode().getCode());
		while (!queue.isEmpty()) {
			Node temp=queue.poll();
			if (Board.isCurrentStateGoalState(temp.getState())) {
				System.out.println("Got goal State");
				goalNode = temp;
				return;
			}
			ArrayList<Node> children = board.generateChildren(temp);
			for (int i =  children.size()-1; i >=0; i--) {
				Node t = children.get(i);
				
				if(Board.isCurrentStateGoalState(t.getState()))
				{
					
					System.out.println("Got goal State at children nodes");
					System.out.println("This is the goal state");
					System.out.println(t.getPrintableInfo() +  " "+ t.getMovement());
					goalNode = t;
					return;
					
				}
				if (!alreadyKnownChildren.contains(t.getCode()) ) {
					queue.add(t);
					alreadyKnownChildren.add(t.getCode());
				}
			}
		}
		
	}
}
