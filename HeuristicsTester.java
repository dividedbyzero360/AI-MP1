
public class HeuristicsTester {


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
//		
//		noOfMisplaceTitlesFor11puzzle();
//		noOfMisplaceTitlesFor8puzzle();
		
		//sumOfPermutationInversiontestFor11puzzzleRT();
		//manhattanTestFor8Puzzle();
		manhattanTestFor11Puzzle();
		long endTime=System.currentTimeMillis();
		System.out.println("Total Time Taken " +(endTime-startTime));
	}
	
	public static void sumOfPermutationInversiontestFor8puzzzle()
	{
		
		int[] goalState={1,2,3,4,5,6,7,8,0};
		int[] state={5,0,8,4,2,1,7,3,6};
		Heuristics.setGoalState(goalState);
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
		Heuristics.setGoalState(goalState);
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
		Heuristics.setGoalState(goalState);
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
		Heuristics.setGoalState(goalState);
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
	
	public static void sumOfPermutationInversiontestFor11puzzzleRT()
	{
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		int[] state={7,2,1,0,8,3,9,10,4,11,6,5};
		Heuristics.setGoalState(goalState);
		int output1=Heuristics.sumPermutationInversionSlow(state);
//		if(output1!=11)
//		{
//			throw new RuntimeException("Error in 1: sumOfPermutationInversiontestFor11puzzzle output1");
//		}
		System.out.println(output1); // Should return 11
		int[] state1={7,2,1,9,8,3,5,10,4,11,6,0};
		output1=Heuristics.sumPermutationInversionSlow(state1);
//		if(output1!=11)
//		{
//			throw new RuntimeException("Error in 2: sumOfPermutationInversiontestFor11puzzzle output1");
//		}
		System.out.println(output1); // Should return 11
		int state2[]={2,1,4,5,3,6,0,9,7,8,11,10}; //Mine Right
		output1=Heuristics.sumPermutationInversionSlow(state2);
		System.out.println(output1);
		int state3[]={2,1,4,5,3,7,6,9,0,8,11,10};//Sujay Down-Left
		output1=Heuristics.sumPermutationInversionSlow(state3);
		System.out.println(output1);
	}
	
	
	
	public static void manhattanTestFor8Puzzle()
	{
		int[] goalState = {1, 2, 3, 8, 0, 4, 7, 6, 5};
		int[] state={7, 2, 4, 5, 0, 6, 8, 3, 1};
		Heuristics.setGoalState(goalState);
		Heuristics.noOfColumns=3;
		int output1=Heuristics.manhattanDistance(state);
		System.out.println(output1);
		if(output1!=16)
		{
			throw new RuntimeException("Wrong output at manhattanTestFor8Puzzle- 1");
		}
		
		int[] goalState1 = {1, 2, 3, 4,5,6,7,8,0};
		int[] state1={7, 2, 4, 5, 0, 6, 8, 3, 1};
		Heuristics.setGoalState(goalState1);
		Heuristics.noOfColumns=3;
		int output2=Heuristics.manhattanDistance(state1);
		if(output2!=14)
		{
			throw new RuntimeException("Wrong output at manhattanTestFor8Puzzle- 2");
		}
		System.out.println(output2);
		
		int[] goalState2 = {1, 2, 3, 4,5,6,7,8,0};
		int[] state2={5,0,8,4,2,1,7,3,6};
		Heuristics.setGoalState(goalState2);
		Heuristics.noOfColumns=3;
		int output3=Heuristics.manhattanDistance(state2);
		if(output3!=13)
		{
			throw new RuntimeException("Wrong output at manhattanTestFor8Puzzle- 3");
		}
		System.out.println(output3);
		
		
		
	}
	
	
	public static void manhattanTestFor11Puzzle()
	{
		int[] goalState = {1,2,3,4,5,6,7,8,9,10,11,0};
		int[] state={1, 0, 3, 7, 5, 2, 6, 4, 9, 10, 11, 8};
		int[] state1={1,2,6,4,5,9,7,3,0,10,11,8};
		Heuristics.setGoalState(goalState);
		Heuristics.noOfColumns=4;
		int output1=Heuristics.manhattanDistance(state);
		int output2=Heuristics.manhattanDistance(state1);
		System.out.println(output1);
		System.out.println(output2);
		
	}

}
