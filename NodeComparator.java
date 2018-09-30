import java.util.Comparator;

//https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
public class NodeComparator implements Comparator<Node> {
	
 private BlankTileMovementDirection movementPriority; 
	public NodeComparator(BlankTileMovementDirection movementPriority)
	{
		this.movementPriority=movementPriority;
	}

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		int diff=o1.getHeristicsValue()-o2.getHeristicsValue();
		if(diff==0)
		{
			if(movementPriority==BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP)
			{
				return o1.getMovement().getPirority()-o2.getMovement().getPirority();	
			}else{
				return o2.getMovement().getPirority()-o1.getMovement().getPirority();	
			}
				
			
		}
		return diff;
	}
	

}
