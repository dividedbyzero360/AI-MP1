import java.util.Arrays;

public class LinearConflictTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] goalState={1,2,3,4,5,6,7,8,9,10,11,0};
		new LinearConflict(goalState);
		int[] initialState={0,11,10,9,8,7,6,5,4,3,2,1};
		//int[] initialState={1,2,6,4,5,9,7,3,0,10,11,8};
		//int[] initialState={1,0,3,7,5,2,6,4,9,10,11,8};
		System.out.println(LinearConflict.linearConflict(initialState));

	}

}



class LinearConflict
{
	static int[] goalState;
	public static int noOfColumns = 4;
	
	public LinearConflict(int[] goalState)
	{
		LinearConflict.goalState=goalState;
	}
	
	public static final int linearConflict(int[] state)
	{
		int lc = 0;
		
		for(int i=0; i< state.length/noOfColumns;i++)
		{
			int[] rowOfState =getRowtuple(i,state);
			System.out.println("For state row "+i);
			for(int x=0; x< rowOfState.length;x++)
			{
				System.out.println(rowOfState[x]);
			}
			System.out.println("------------------------------------");
			int[] rowOfGoal =getRowtuple(i,goalState);
			System.out.println("For goal row "+i);
			for(int x=0; x< rowOfGoal.length;x++)
			{
				System.out.println(rowOfGoal[x]);
			}
			System.out.println("------------------------------------");
			lc+=inversions(rowOfState,rowOfGoal,noOfColumns);
		}
		
		for(int j=0;j<noOfColumns;j++)
		{
			int[] columnOfState =getColumntuple(j,state.length/noOfColumns,state);
			int[] columnOfGoal =getColumntuple(j,state.length/noOfColumns,goalState);
			lc+=inversions(columnOfState,columnOfGoal,state.length/noOfColumns);
		}
		
		System.out.println("Answer is "+lc);
		return lc;
		
	}
	
	public static int inversions(int[] statePart, int[] goalPart,int count)
	{
		int inversions = 0;
		for (int i = 1, iPos; i < count; i++) {
            if (statePart[i] != 0 && 0 <= (iPos = Arrays.binarySearch(goalPart, statePart[i]))) {
                for (int j = 0, jPos; j < i; j++) {
                    if (statePart[j] != 0 && 0 <= (jPos = Arrays.binarySearch(goalPart, statePart[j]))) {
                        if ((statePart[i] < statePart[j]) != (i < j)) {
                            inversions++;
                        }
                    }
                }
            }
        }
        return inversions;
	}
	
	private static int[] getRowtuple(int rowIndex,int[] state)
	{
		int[] result=new int[noOfColumns];
		
			System.arraycopy(state, rowIndex*noOfColumns, result, 0, noOfColumns);
			return result;
	}
	
	private static int[] getColumntuple(int columnIndex,int noOfRows,int[] state)
	{
		int[] result=new int[noOfRows];
		for (int i = 0, j = columnIndex; i < noOfRows; i++, j += noOfColumns) {
            result[i] = state[j];
        }
		return result;
	}
}
