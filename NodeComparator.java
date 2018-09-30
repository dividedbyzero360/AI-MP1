import java.util.Comparator;

//https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		int diff=o1.getHeristicsValue()-o2.getHeristicsValue();
		if(diff==0)
		{
			return o1.getMovement().getPirority()-o1.getMovement().getPirority();
		}
		return diff;
	}
	

}
