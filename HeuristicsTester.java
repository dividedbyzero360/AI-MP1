
public class HeuristicsTester {

	 static Heuristics h=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();
		for(int i=0; i< 479001600;i++)
		{
			//sumOfPermutationInversiontestFor8puzzzle();	
			//noOfMisplaceTitlesFor11puzzle();
		}
//		sumOfPermutationInversiontestFor8puzzzle();
//		sumOfPermutationInversiontestFor11puzzzle();
		
		noOfMisplaceTitlesFor11puzzle();
		noOfMisplaceTitlesFor8puzzle();
		long endTime=System.currentTimeMillis();
		System.out.println("Total Time Taken " +(endTime-startTime));
	}
	
	public static void sumOfPermutationInversiontestFor8puzzzle()
	{
		
		int[] goalState={1,2,3,4,5,6,7,8,0};
		int[] state={5,0,8,4,2,1,7,3,6};
		h=new Heuristics(goalState,1);
		int output1=Heuristics.sumPermutationInversionSlow(state);
		if(output1!=16)
		{
			throw new RuntimeException("Error in 1: sumOfPermutationInversiontestFor8puzzzle output1");
		}
		//System.out.println(output1); // Should return 16
		int[] state1={4,0,8,5,2,1,7,3,6};
		output1=Heuristics.sumPermutationInversionSlow(state1);
		if(output1!=15)
		{
			throw new RuntimeException("Error in 2: sumOfPermutationInversiontestFor8puzzzle output1");
		}
		//System.out.println(Heuristics.sumPermutationInversionSlow(state1)); // Should return 15
		
	}
	
	public static void sumOfPermutationInversiontestFor11puzzzle()
	{
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		int[] state={1,2,6,4,5,9,7,3,0,10,11,8};
		h=new Heuristics(goalState,1);
		int output1=Heuristics.sumPermutationInversionSlow(state);
		if(output1!=11)
		{
			throw new RuntimeException("Error in 1: sumOfPermutationInversiontestFor11puzzzle output1");
		}
		//System.out.println(output1); // Should return 11
		int[] state1={1, 0, 3, 7, 5, 2, 6, 4, 9, 10, 11, 8};
		output1=Heuristics.sumPermutationInversionSlow(state1);
		if(output1!=11)
		{
			throw new RuntimeException("Error in 2: sumOfPermutationInversiontestFor11puzzzle output1");
		}
		//System.out.println(Heuristics.sumPermutationInversionSlow(state1)); // Should return 11
		
	}
	
	public static void noOfMisplaceTitlesFor11puzzle()
	{
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		int[] state={1,2,6,4,5,9,7,3,0,10,11,8};
		h=new Heuristics(goalState,1);
		int output1=Heuristics.numberOfMisplacedTitlesSlow(state);
		//System.out.println(output1); // should return 4
		if(output1!=4)
		{
			throw new RuntimeException("Error in 1: noOfMisplaceTitlesFor11puzzle output1");
		}
		int[] state1={1, 0, 3, 7, 5, 2, 6, 4, 9, 10, 11, 8};
		output1=Heuristics.numberOfMisplacedTitlesSlow(state1);
		//System.out.println(output1);// should return 5
		if(output1!=5)
		{
			throw new RuntimeException("Error in 2: noOfMisplaceTitlesFor11puzzle output1");
		}
	}
	
	public static void noOfMisplaceTitlesFor8puzzle()
	{
		int[] goalState={1,2,3,8,0,4,7,6,5};
		h=new Heuristics(goalState,1);
		int[] state1={2,8,3,1,6,4,7,0,5};
		int[] state2={2,8,3,1,6,4,0,7,5};
		int[] state3={2,8,3,1,0,4,7,6,5};
		int[] state4={2,8,3,1,6,4,7,5,0};
		int output1=Heuristics.numberOfMisplacedTitlesSlow(state1);
		int output2=Heuristics.numberOfMisplacedTitlesSlow(state2);
		int output3=Heuristics.numberOfMisplacedTitlesSlow(state3);
		int output4=Heuristics.numberOfMisplacedTitlesSlow(state4);
		System.out.println(output1);
		System.out.println(output2);
		System.out.println(output3);
		System.out.println(output4);
		if(output1!=4)
		{
			throw new RuntimeException("Error in 1: noOfMisplaceTitlesFor8puzzle output1");
		}
		if(output2!=5)
		{
			throw new RuntimeException("Error in 2: noOfMisplaceTitlesFor8puzzle output2");
		}
		if(output3!=3)
		{
			throw new RuntimeException("Error in 3: noOfMisplaceTitlesFor8puzzle output3");
		}
		if(output4!=5)
		{
			throw new RuntimeException("Error in 4: noOfMisplaceTitlesFor8puzzle output4");
		}
	}
	
	
	
	

}
