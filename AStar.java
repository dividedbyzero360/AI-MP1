import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
	private Board board;
	private Node goalNode = null;
	PriorityQueue<Node> queue=null;
	HashSet<String> alreadyKnownChildren = new HashSet<String>();
	HashMap<String,Integer> alreadyInsideQueueWithHeuristicValue = new HashMap<String,Integer>();
	public AStar(Board board)
	{
		this.board = board;
		new Comparators(Board.getBlankTileMovementDirection());
		this.queue=new PriorityQueue<>(Comparators.getJustHeuristicsPlusDepthComparator());
	}
	
	public Node getGoalNode(){
		return goalNode;
	}
	
	public void go()
	{
		queue.add(board.getInitialStateNode());
		while (!queue.isEmpty()) {
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
				if(Board.isCurrentStateGoalState(t.getState()))
				{
					
					System.out.println("Got goal State at children nodes");
					System.out.println("This is the goal state");
					System.out.println(t.getPrintableInfo() +  " "+ t.getMovement().getDirectionName());
					goalNode = t;
					return;
					
				}
				if (!alreadyKnownChildren.contains(t.getCode()) ) {
					if(queue.contains(t))
					{
						System.out.println("HERE");
						Node c=null;
						for(Node n : queue)
						{
							if(n.equals(t))
							{
								c=n;
								break;
							}
						}
						if(c==null)
						{
							throw new RuntimeException("Not possible1");
						}
						if(c.getHeristicsValuePlusDepth() > t.getHeristicsValuePlusDepth())
						{
							boolean removed= queue.remove(c);
							if(!removed)
							{
								throw new RuntimeException("Not possible2");
							}
							
						}
					}
					
					queue.add(t);
				}
			}
		}
		
	}
	

	
	public void realAstarOptimize() {
		Node initilaNode=board.getInitialStateNode();
		queue.add(initilaNode);
		alreadyInsideQueueWithHeuristicValue.put(initilaNode.getCode(), initilaNode.getHeristicsValuePlusDepth());
		while (!queue.isEmpty()) {
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
				if (!alreadyKnownChildren.contains(t.getCode()) ) {
			       if(alreadyInsideQueueWithHeuristicValue.containsKey(t.getCode()))
			       {
			    	   int value=alreadyInsideQueueWithHeuristicValue.get(t.getCode());
			    	   if(value > t.getHeristicsValuePlusDepth())
			    	   {
			    		   alreadyInsideQueueWithHeuristicValue.put(t.getCode(), t.getHeristicsValuePlusDepth());
			    		   queue.remove(t);
			    		   queue.add(t);
			    	   }
			       }else{
			    	   queue.add(t);
			    	   alreadyInsideQueueWithHeuristicValue.put(t.getCode(), t.getHeristicsValuePlusDepth());
			       }
				}
				
			}
		}

	}
	
	
	public  boolean couldNodeBeAdded(Node t)
	{
		boolean couldNodeBeAdded=false;
		Node found=null;
		for(Node n: queue)
		{
			if(n.equals(t))
			{
				found=n;
				break;
			}
		}
		if(found!=null){
			if(found.getHeristicsValuePlusDepth() > t.getHeristicsValuePlusDepth())
			{
				boolean removed= queue.remove(found);
				if(!removed)
				{
					throw new RuntimeException("Not possible2");
				}
				couldNodeBeAdded=true;
			}
			else{
				couldNodeBeAdded=false;
			}
		}
		
		else{
			couldNodeBeAdded=true;
		}
		
		return couldNodeBeAdded;
	}
	
	
}


//public void realAstar() {
//	queue.add(board.getInitialStateNode());
//	while (!queue.isEmpty()) {
//		Node temp = queue.poll();
////		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
////		Utility.printMatrix(temp.getState());
////		System.out.println(temp.getHeristicsValuePlusDepth());
////		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		if (Board.isCurrentStateGoalState(temp.getState())) {
//			System.out.println("Got goal State");
//			goalNode = temp;
//			return;
//		}
//		//System.out.println("Poped node "+ temp.getHeristicsValuePlusDepth());
//		alreadyKnownChildren.add(temp.getCode());
//		ArrayList<Node> children = board.generateChildren(temp);
//		for (int i = 0; i < children.size(); i++) {
//			Node t = children.get(i);
//			//System.out.println("Children node "+ t.getHeristicsValuePlusDepth());
////			if(Board.isCurrentStateGoalState(t.getState()))
////			{
////				
////				System.out.println("Got goal State at children nodes");
////				System.out.println("This is the goal state");
////				System.out.println(t.getPrintableInfo() +  " "+ t.getMovement());
////				goalNode = t;
////				return;
////				
////			}
//			if (!alreadyKnownChildren.contains(t.getCode()) ) {
//				if(queue.contains(t))
//				{
//					//System.out.println("HERE");
//					Node c=null;
//					for(Node n : queue)
//					{
//						if(n.equals(t))
//						{
//							c=n;
//							break;
//						}
//					}
//					if(c==null)
//					{
//						throw new RuntimeException("Not possible1");
//					}
//					if(c.getHeristicsValuePlusDepth() > t.getHeristicsValuePlusDepth())
//					{
////					    System.out.println(c.getHeristicsValuePlusDepth() + " " +t.getHeristicsValuePlusDepth());
////						try{
////							Thread.sleep(2000);	
////						}catch(Exception ex){
////							
////						}
//						
////						Utility.printMatrix(c.getState());
////						Utility.printMatrix(t.getState());
//						boolean removed= queue.remove(c);
//					//	System.out.println("HERE");
//						if(!removed)
//						{
//							throw new RuntimeException("Not possible2");
//						}
//						
//					}
//				}
//				
//				queue.add(t);
//			}
//			
//		}
//	}
//
//}


