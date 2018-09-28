import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
	private Board board;
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	Stack<Node> toVisitChildren = new Stack<Node>();
	private Node goalNode = null;

	public DFS(Board board) {
		this.board = board;
	}

	
	public void go() {
		toVisitChildren.push(board.getInitialStateNode());
		while (!toVisitChildren.isEmpty()) {
			Node temp = toVisitChildren.pop();
			if(!alreadyKnownChildren.contains(temp.getCode()))
			{
				alreadyKnownChildren.add(temp.getCode());
				//System.out.println(temp.getPrintableInfo());
				if (Board.isCurrentStateGoalState(temp.getState())) {
					System.out.println("Got goal State");
					goalNode = temp;
					return;
				}
				ArrayList<Node> children = board.generateChildren(temp);
				for (int i =  children.size()-1; i >=0; i--) {
					Node t = children.get(i);
						toVisitChildren.push(t);
					
				}
			}
			

		}

	}
	
	public void go2(){
		toVisitChildren.push(board.getInitialStateNode());
		while(!toVisitChildren.isEmpty()){
			Node temp=toVisitChildren.pop();
			alreadyKnownChildren.add(temp.getCode());
			if (Board.isCurrentStateGoalState(temp.getState())) {
				System.out.println("Got goal State");
				goalNode = temp;
				return;
			}
			ArrayList<Node> children = board.generateChildren(temp);
			for (int i =  children.size()-1; i >=0; i--) {
				Node child = children.get(i);
				if(!alreadyKnownChildren.contains(child.getCode()))
					toVisitChildren.push(child);
				
			}
			
			
		}
	}
	
	public Node getGoalNode(){
		return goalNode;
	}
	
	
	
	//For now correct version
	
	public void go1() {
		toVisitChildren.push(board.getInitialStateNode());
		alreadyKnownChildren.add(board.getInitialStateNode().getCode());
		
		while (!toVisitChildren.isEmpty()) {
			Node temp = toVisitChildren.pop();
			//System.out.println(temp.getPrintableInfo());
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
					toVisitChildren.push(t);
					alreadyKnownChildren.add(t.getCode());
				}
			}

		}

	}
	
}



////System.out.println(t.getPrintableInfo());
//if(alreadyKnownChildren.contains(t.getCode()) && t.getCode().equals("12345678910110"))
//{
//	throw new RuntimeException("Not possibele");
//}
