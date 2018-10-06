import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class IterativeDeepingDFS {
	private Board board;
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	Stack<Node> toVisitChildren = new Stack<Node>();
	private Node goalNode = null;
    private static final BlankTileMovementDirection movementPriority=Board.getBlankTileMovementDirection();
	public IterativeDeepingDFS(Board board) {
		this.board = board;
		
	}

	
	public Node getGoalNode(){
		return goalNode;
	}
	
	
	public void realIterativeDeepingDFS()
	{
		int depth=0;
		while(true)
		{
			toVisitChildren.push(board.getInitialStateNode());
			while (!toVisitChildren.isEmpty())
			{
				Node temp = toVisitChildren.pop();
				if(Board.isCurrentStateGoalState(temp.getState()))
				{
					System.out.println("Got goal State");
					goalNode = temp;
					return;
				}
				alreadyKnownChildren.add(temp.getCode());
				if(temp.getDepthOfNode() < depth)
				{
					ArrayList<Node> children = board.generateChildren(temp);
					for (int i =  children.size()-1; i >=0; i--) {
						Node t = children.get(i);
						if(!alreadyKnownChildren.contains(t.getCode()) && !toVisitChildren.contains(t))
						{
							toVisitChildren.push(t);
						}
					}
				}
			}
			System.out.println(depth);
			depth++;
			toVisitChildren.clear();
			alreadyKnownChildren.clear();
			
		}
	}
	
	public void iterativeDeepingDFS()
	{
		int depth=0;
		ArrayList<Node> toExploreNodes=new ArrayList<>();
		while(true)
		{
			toVisitChildren.push(board.getInitialStateNode());
			while (!toVisitChildren.isEmpty())
			{
				Node temp = toVisitChildren.pop();
				if(Board.isCurrentStateGoalState(temp.getState()))
				{
					System.out.println("Got goal State");
					goalNode = temp;
					return;
				}
				alreadyKnownChildren.add(temp.getCode());
				if(temp.getDepthOfNode() < depth)
				{
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
						if(!alreadyKnownChildren.contains(t.getCode()))
						{
							toVisitChildren.push(t);
							alreadyKnownChildren.add(t.getCode());
						}
					}
				}
			}
			System.out.println(depth);
			depth++;
			toVisitChildren.clear();
			alreadyKnownChildren.clear();
			
		}
	}

}
