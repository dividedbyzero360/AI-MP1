
public class BestFirstSearch {
	
	private Board board;
	private Node goalNode = null;
	
	
	public BestFirstSearch(Board board) {
		this.board = board;
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
}
