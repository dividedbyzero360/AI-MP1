import java.util.*;
public class Utility {
	
	
	public static final void printMatrix(int[] a)
	{
		for(int i=0; i< a.length;i++)
		{
			if(i==4  || i==8)
			{
				System.out.println();
			}
			if((a[i]+"").length()==2 )
			{
				System.out.print(a[i]+" ");	
			}else
			{
				System.out.print(a[i]+"  ");
			}
			
		}
		System.out.println("\n");
	}
	
	public static final void writeGoalTraceToFile(Node goalNode)
	{
		if(goalNode!=null){
			ArrayList<String> goalStateToRootTrace=new ArrayList<String>();
			while(goalNode!=null)
			{
				goalStateToRootTrace.add(goalNode.getPrintableInfo());
				goalNode=goalNode.getParent();
			}
			
			for(int i=goalStateToRootTrace.size()-1; i>= 0;i--)
			{
//				try{
//					Thread.sleep(3000);	
//				}catch(Exception ex){
//					System.out.println(ex);
//				}
//				
				System.out.println(goalStateToRootTrace.get(i));
			}
		}
		
	}

}
