
public class Node {

	private String titleConfig;
	private char[] config={'a','b','c','d','e','f','g','h','i','j','k','l'};
	private Node parentNode;
	private int[] state;
	private String printableCode=null;
	private String direction;
	
	
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
		String s="";
		for(int i=0;i<state.length;i++)
		{
			s+=state[i]+"";
		}
		return s;
	}
	
	public String getPrintableInfo()
	{
		if(printableCode!=null)
		{
			return printableCode;
		}
		String result=titleConfig + "[";
		for(int i=0;i<state.length;i++)
		{
			result+=state[i]+", ";
		}
		result=result.substring(0, result.length()-2);
		result+="]";
		printableCode=result;
		return result;
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
	
	public void setMovement(String direction)
	{
		this.direction=direction;
	}
	
	public String getMovement()
	{
		return this.direction;
	}
}
