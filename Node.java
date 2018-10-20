
public class Node {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if(this.getCode().equals(other.getCode()))
		{
			return true;
		}
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	private String titleConfig;
	private char[] config={'a','b','c','d','e','f','g','h','i','j','k','l'};
	private Node parentNode;
	private int[] state;
	private String printableCode=null;
	private Direction direction=new Direction();
	private int depthOfNode; 
	private boolean cached=false;
    private int heristicsValuePlusDepth;
    private String code=null;
	
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
			heristicsValuePlusDepth= Heuristics.getHerirsticValue(this.getState())+depthOfNode;
//			if(parentNode!=null)
//			{
//				heristicsValuePlusDepth=Math.max(parentNode.getHeristicsValuePlusDepth(), heristicsValuePlusDepth);
//			}
				
			return heristicsValuePlusDepth;
		}
		else
		{
//			System.out.println(depthOfNode);
//			if(parentNode!=null)
//			{
//				heristicsValuePlusDepth=Math.max(parentNode.getHeristicsValuePlusDepth(), heristicsValuePlusDepth);
//			}
			return heristicsValuePlusDepth;
		}
		
	}
	
	
	public void setState(int[] state)
	{
		this.state=state;
		getCode();
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
		if(code==null)
		{
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<state.length;i++)
			{
				sb.append(state[i]+"");
			}
			code=sb.toString();
			return code;
		}
		return code;
	}
	
	public String getPrintableInfo()
	{
		if(printableCode!=null)
		{
			return printableCode;
		}
		StringBuilder sb=new StringBuilder();
		sb.append(titleConfig + " [");
		for(int i=0;i<state.length;i++)
		{
			sb.append(state[i]+", ");
		}
		String result=sb.toString();
		result=result.substring(0, result.length()-2);
		result+="]";
		printableCode=result;
		return result;
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

