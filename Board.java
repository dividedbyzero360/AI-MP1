import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	
	//private static int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};//Professor
	//private static int[] goalState={1,2,3,0,5,6,7,4,9,10,11,8};
	private static int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
	private int[] initialState;
	
	public Board(int[] initialState)
	{
		this.initialState=initialState;
	}
	
	public Node getInitialStateNode(){
		Node root=new Node();
		root.setState(initialState);
		root.setTileConfig();
		return root;
	}
	
	public static final boolean isCurrentStateGoalState(int[] currentState)
	{
		return Arrays.equals(goalState, currentState);
	}
	
	public ArrayList<Node> generateChildren(Node currentState)
	{
		ArrayList<Node> childrens=new ArrayList<Node>(); 
		int indexOfBlankTitle=-1;
		int[] cs=currentState.getState();
		for(int i=0; i< cs.length;i++)
		{
			if(cs[i]==0)
			{
				indexOfBlankTitle=i;
			}
			
		}
		if(indexOfBlankTitle==-1)
		{
			throw new RuntimeException("No blank title. How is this even possible?");
		}
		//Generate UP
		if(indexOfBlankTitle-4>=0)
		{
			//System.out.println("UP");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle-4));
			child.setTileConfig(indexOfBlankTitle-4);
			child.setParent(currentState);
			child.setMovement(1,"UP");
			childrens.add(child);
		}
		//Generate UP –RIGHT
		if(indexOfBlankTitle!=7 && indexOfBlankTitle!=11 &&  indexOfBlankTitle-3 >0 )
		{
			//System.out.println("UP –RIGHT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle-3));
			child.setTileConfig(indexOfBlankTitle-3);
			child.setParent(currentState);
			child.setMovement(2,"UP-Right");
			childrens.add(child);
		}
		//Generate RIGHT
		if(indexOfBlankTitle!=3 && indexOfBlankTitle!=7 && indexOfBlankTitle!=11 )
		{
			//System.out.println("RIGHT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle+1));
			child.setTileConfig(indexOfBlankTitle+1);
			child.setParent(currentState);
			child.setMovement(3,"Right");
			childrens.add(child);
		}
		//Generate DOWN- RIGHT
		if(indexOfBlankTitle!=3 && indexOfBlankTitle!=7 && indexOfBlankTitle+5 < cs.length){
			//System.out.println("DOWN- RIGHT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle+5));
			child.setTileConfig(indexOfBlankTitle+5);
			child.setParent(currentState);
			child.setMovement(4,"DOWN-Right");
			childrens.add(child);
		}
		//Generate DOWN
		if(indexOfBlankTitle+4 < cs.length)
		{
			//System.out.println("DOWN");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle+4));
			child.setTileConfig(indexOfBlankTitle+4);
			child.setParent(currentState);
			child.setMovement(5,"DOWN");
			childrens.add(child);
		}
		//Generate DOWN –LEFT
		if(indexOfBlankTitle!=0 && indexOfBlankTitle!=4 && indexOfBlankTitle!=8 && indexOfBlankTitle+3 < cs.length)
		{
			//System.out.println("DOWN –LEFT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle+3));
			child.setTileConfig(indexOfBlankTitle+3);
			child.setParent(currentState);
			child.setMovement(6,"DOWN-Left");
			childrens.add(child);
		}
		
		//Generate LEFT
		if(indexOfBlankTitle!=0 && indexOfBlankTitle!=4 && indexOfBlankTitle!=8)
		{
			//System.out.println("LEFT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle-1));
			child.setTileConfig(indexOfBlankTitle-1);
			child.setParent(currentState);
			child.setMovement(7,"LEFT");
			childrens.add(child);
		}
		//Generate UP–LEFT
		if(indexOfBlankTitle!=4 && indexOfBlankTitle!=8 &&  indexOfBlankTitle-5 >0 )
		{
			//System.out.println("UP–LEFT");
			Node child=new Node();
			child.setState(swap(cs,indexOfBlankTitle,indexOfBlankTitle-5));
			child.setTileConfig(indexOfBlankTitle-5);
			child.setParent(currentState);
			child.setMovement(8,"UP-LEFT");
			childrens.add(child);
		}
		return childrens;
	}
	
	public static final int[] swap(int[] currentState,int i, int j)
	{
		int length=currentState.length;
		int[] newTemp=new int[length];
		for(int k=0; k<length;k++ )
		{
			newTemp[k]=currentState[k];
		}
		int temp=newTemp[i];
		newTemp[i]=newTemp[j];
		newTemp[j]=temp;
		return newTemp;
	}
}

//public ArrayList<int[]> generateChildren(int[] currentState)
//{
//	ArrayList<int[]> childrens=new ArrayList<int[]>(); 
//	int indexOfBlankTitle=-1;
//	for(int i=0; i< currentState.length;i++)
//	{
//		if(currentState[i]==0)
//		{
//			indexOfBlankTitle=i;
//		}
//		
//	}
//	if(indexOfBlankTitle==-1)
//	{
//		throw new RuntimeException("No blank title. How is this even possible?");
//	}
//	//Generate UP
//	if(indexOfBlankTitle-4>=0)
//	{
//		System.out.println("UP");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle-4));
//	}
//	//Generate UP –RIGHT
//	if(indexOfBlankTitle!=7 && indexOfBlankTitle!=11 &&  indexOfBlankTitle-3 >0 )
//	{
//		System.out.println("UP –RIGHT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle-3));
//	}
//	//Generate RIGHT
//	if(indexOfBlankTitle!=3 && indexOfBlankTitle!=7 && indexOfBlankTitle!=11 )
//	{
//		System.out.println("RIGHT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle+1));
//	}
//	//Generate DOWN- RIGHT
//	if(indexOfBlankTitle!=3 && indexOfBlankTitle!=7 && indexOfBlankTitle+5 < currentState.length){
//		System.out.println("DOWN- RIGHT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle+5));
//	}
//	//Generate DOWN
//	if(indexOfBlankTitle+4 < currentState.length)
//	{
//		System.out.println("DOWN");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle+4));
//	}
//	//Generate DOWN –LEFT
//	if(indexOfBlankTitle!=0 && indexOfBlankTitle!=4 && indexOfBlankTitle!=8 && indexOfBlankTitle+3 < currentState.length)
//	{
//		System.out.println("DOWN –LEFT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle+3));
//	}
//	
//	//Generate LEFT
//	if(indexOfBlankTitle!=0 && indexOfBlankTitle!=4 && indexOfBlankTitle!=8)
//	{
//		System.out.println("LEFT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle-1));
//	}
//	//Generate UP–LEFT
//	if(indexOfBlankTitle!=4 && indexOfBlankTitle!=8 &&  indexOfBlankTitle-5 >0 )
//	{
//		System.out.println("UP–LEFT");
//		childrens.add(swap(currentState,indexOfBlankTitle,indexOfBlankTitle-5));
//	}
//	return childrens;
//}
