import java.util.Comparator;

public class Comparators {
	private static BlankTileMovementDirection movementPriority; 
	public Comparators(BlankTileMovementDirection movementPriority)
	{
		Comparators.movementPriority=movementPriority;
	}
	static Comparator<Node> getJustHeuristicsComparator()
	{
		return new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				
				int diff=o1.getHeristicsValue()-o2.getHeristicsValue();
				if(diff==0)
				{
					if(movementPriority==BlankTileMovementDirection.CLOCKWISE_STARTING_FROM_UP)
					{
//						System.out.println("***************************************************");
//						System.out.println("o1 "+o1.getMovement().getDirectionName()  + " "+o1.getMovement().getPirority() + " "+o1.getHeristicsValue() );
//						System.out.println("o2 "+o2.getMovement().getDirectionName()  + " "+o2.getMovement().getPirority() + " "+o2.getHeristicsValue());
//						System.out.println("***************************************************");
						return o1.getMovement().getPirority()-o2.getMovement().getPirority();	
					}else{
//						if(1==1)
//						{
//							throw new RuntimeException("Fiales");	
//						}
						
						return o2.getMovement().getPirority()-o1.getMovement().getPirority();	
					}
				}
				return diff;
			}
		};
	}
	
	static Comparator<Node> getJustHeuristicsPlusDepthComparator()
	{
		return new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				
				int diff=o1.getHeristicsValuePlusDepth()-o2.getHeristicsValuePlusDepth();
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
		};
	}

}
