
public class Node {

	private String titleConfig;
	private char[] config={'a','b','c','d','e','f','g','h','i','j','k','l'};
	private Node parentNode;
	private int[] state;
	private String printableCode=null;
	private Direction direction=new Direction();
	private int depthOfNode; 
	private boolean cached=false;
    private int heristicsValuePlusDepth;
	
	public Node()
	{
		
	}
	
	public Node(int depthOfNode)
	{
		this.depthOfNode=depthOfNode;
	}
	
	public void setDepthOfNode(int depthOfNode)
	{
		this.depthOfNode=depthOfNode;
	}
	
	public int getDepthOfNode()
	{
		return depthOfNode;
	}
	
	public int getHeristicsValue()
	{
		return Heuristics.getHerirsticValue(this.getState());
	}
	
	
	//TODO: Performance enhancement
	public int getHeristicsValuePlusDepth()
	{
		if(!cached)
		{
			cached=true;
//			System.out.println(depthOfNode);
			heristicsValuePlusDepth= Heuristics.getHerirsticValue(this.getState())+depthOfNode;
			return heristicsValuePlusDepth;
		}
		else
		{
//			System.out.println(depthOfNode);
			return heristicsValuePlusDepth;
		}
		
	}
	
	
	public void setState(int[] state)
	{
		this.state=state;
	}
	
	public int[] getState()
	{
		return state;
	}
	
	public void setParent(Node parent)
	{
		this.parentNode=parent;
	}
	
	public Node getParent()
	{
		return parentNode;
	}
	
	public String getTileConfig()
	{
		return titleConfig;
	}
	
	public void setTileConfig(int index)
	{
		titleConfig=config[index]+"";
	}
	
	public void setTileConfig()
	{
		titleConfig="0";
		
	}
	
	public String getCode(){
		StringBuilder sb=new StringBuilder();
		//String s="";
		for(int i=0;i<state.length;i++)
		{
			//ss+=state[i]+"";
			sb.append(state[i]+"");
		}
		return sb.toString();
	}
	
	public String getPrintableInfo()
	{
		if(printableCode!=null)
		{
			return printableCode;
		}
		StringBuilder sb=new StringBuilder();
		//String result=titleConfig + "[";
		sb.append(titleConfig + "[");
		for(int i=0;i<state.length;i++)
		{
			//result+=state[i]+", ";
			sb.append(state[i]+", ");
		}
		String result=sb.toString();
		result=result.substring(0, result.length()-2);
		result+="]";
		printableCode=result;
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this) { 
            return true; 
        } 
		Node t= (Node)obj;
		return t.getCode().equals(this.getCode());
	}
	
	
	public void setMovement(int pirority,String direction)
	{
		this.direction.setDirectionName(direction);
		this.direction.setPirority(pirority);
	}
	
	public Direction getMovement()
	{
		return this.direction;
	}
	
//	public String getPrintableInfo()
//	{
//		if(printableCode!=null)
//		{
//			return printableCode;
//		}
//		String result="";
//		for(int i=0;i<state.length;i++)
//		{
//			result+=state[i]+" ";
//		}
//		//result=result.substring(0, result.length()-2);
//		//result+="]";
//		printableCode=result;
//		return result;
//	}
}

class Direction
{
  private int pirority;
  private String directionName;
	public int getPirority() {
		return pirority;
	}
	public void setPirority(int pirority) {
		this.pirority = pirority;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
}

