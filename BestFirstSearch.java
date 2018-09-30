import java.util.PriorityQueue;

public class BestFirstSearch {
	
	private Board board;
	private Node goalNode = null;
	PriorityQueue<Node> queue=new PriorityQueue<>(new NodeComparator());
	
	public BestFirstSearch(Board board) {
		this.board = board;
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
}
