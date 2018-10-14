import java.util.*;

public final class Heuristics {

	private static int[] goalState;

	private static int cachedHeuristicValue = 0;
	private static boolean cached = false;
	private static boolean cachedLinearConflict = false;
	private static HashMap<Integer, ArrayList<Integer>> numbersThatFollowANumberInGoalState = new HashMap<Integer, ArrayList<Integer>>();
	private static HeuristicsType whichHeristics;
	private static HashMap<Integer, Integer> mapOfTitleNumberToIndex = new HashMap<>();
	public static int noOfColumns = 4;
	private static HashMap<Integer, int[]> rowNumberWithRowItems = new HashMap<Integer, int[]>();
	private static HashMap<Integer, int[]> columnNumberWithColumnItems = new HashMap<Integer, int[]>();

	public static final void setGoalState(int[] goalState) {
		Heuristics.cached = false;
		Heuristics.goalState = goalState;
	}

	public static final void setWhichHeristics(HeuristicsType whichHeristics) {
		Heuristics.whichHeristics = whichHeristics;
		Heuristics.cached = false;
	}

	private Heuristics() {
	}

	// public static final int getCalculatedHeuristicValue(){
	// return cachedHeuristicValue;
	// }

	public static final int getHerirsticValue(int[] state) {
		if (whichHeristics == HeuristicsType.MisplacedTiles) {
			return Heuristics.numberOfMisplacedTitlesSlow(state);
		} else if (whichHeristics == HeuristicsType.SUM_OF_PI) {
			return Heuristics.sumPermutationInversionSlow(state);
		} else if (whichHeristics == HeuristicsType.SUM_OF_PI_WITH_SOLVABILITY_TEST) {

			return Heuristics.sumPermutationInversionSlowWithCheckForUnSolvability(state);
		} else if (whichHeristics == HeuristicsType.MANHATTAN_DISTANCE) {
			Heuristics.manhattanDistance(state);
			return Heuristics.manhattanDistance(state);
		} else if (whichHeristics == HeuristicsType.Euclidian_Distance) {

			return Heuristics.euclideanDistance(state);
		} else if (whichHeristics == HeuristicsType.Cheby_Shev_Distance) {
			return Heuristics.chebyShevDistance(state);
		} else if (whichHeristics == HeuristicsType.Linear_Conflict) {
			
			int manhattanDistance=Heuristics.manhattanDistance(state);
			int linearConflictDistance=Heuristics.linearConflict(state);
			int ChebyShevDistance=Heuristics.chebyShevDistance(state);
//			int eculidianDistance=Heuristics.euclideanDistance(state);
//			int ChebyShevDistance2=Heuristics.chebyShevDistanceV2(state);
			int misplacedTiles=Heuristics.numberOfMisplacedTitlesSlow(state);
			//int sumOfPI=Heuristics.sumPermutationInversionSlow(state);
			System.out.println("---------------------------------------");
			System.out.println("Manhattan Distance is "+ manhattanDistance);
			System.out.println("linearConflictDistance  is "+ linearConflictDistance);
			System.out.println("ChebyShevDistance  is "+ ChebyShevDistance);
			//System.out.println("ChebyShevDistance2  is "+ ChebyShevDistance2);
			//System.out.println("eculidianDistance2  is "+ eculidianDistance);
			System.out.println("misplacedTiles  is "+ misplacedTiles);
//			/System.out.println("sumOfPI  is "+ sumOfPI);
			System.out.println("---------------------------------------");
			//return Math.min(manhattanDistance, linearConflictDistance) ;
			return ChebyShevDistance+linearConflictDistance;
		} else

			return Heuristics.chebyShevDistanceV2(state);

	}

	// h1
	public static final int numberOfMisplacedTitlesSlow(int[] state) {
		int noOfMisplacedTitles = 0;
		for (int i = 0; i < state.length; i++) {
			if (goalState[i] != 0 && goalState[i] != state[i]) {
				noOfMisplacedTitles++;
			}
		}
		return noOfMisplacedTitles;
	}

	// h2
	public static final int sumPermutationInversionSlow(int[] state) {
		if (!cached) {
			System.out.println("Runs once only");
			int goalStateLength = goalState.length;
			for (int i = goalStateLength - 1; i >= 0; i--) {
				if (goalState[i] != 0) {
					numbersThatFollowANumberInGoalState.put(goalState[i], new ArrayList<Integer>());
					for (int j = i - 1; j >= 0; j--) {
						if (goalState[j] != 0) {
							numbersThatFollowANumberInGoalState.get(goalState[i]).add(goalState[j]);
						}
					}

				}
			}
			cached = true;
		}
		int sumOfPerInv = 0;
		for (int i = 0; i < state.length - 1; i++) {
			if (state[i] != 0) {
				ArrayList<Integer> shouldBeOnItsLeft = numbersThatFollowANumberInGoalState.get(state[i]);
				int count = 0;
				for (int j = i + 1; j < state.length; j++) {
					if (shouldBeOnItsLeft.contains(state[j])) {
						count++;
					}
				}
				sumOfPerInv += count;
			}
		}
		return sumOfPerInv;
	}

	// h2
	// Does not work when diagonal moves are allowed
	public static final int sumPermutationInversionSlowWithCheckForUnSolvability(int[] state) {
		if (!cached) {
			System.out.println("Runs once only");
			int goalStateLength = goalState.length;
			for (int i = goalStateLength - 1; i >= 0; i--) {
				if (goalState[i] != 0) {
					numbersThatFollowANumberInGoalState.put(goalState[i], new ArrayList<Integer>());
					for (int j = i - 1; j >= 0; j--) {
						if (goalState[j] != 0) {
							numbersThatFollowANumberInGoalState.get(goalState[i]).add(goalState[j]);
						}
					}

				}
			}
			cached = true;
		}
		int sumOfPerInv = 0;
		// TODO check if the state is solvable or not
		int indexOfBlankTitleInState = -1;
		for (int i = 0; i < state.length - 1; i++) {
			if (state[i] != 0) {
				ArrayList<Integer> shouldBeOnItsLeft = numbersThatFollowANumberInGoalState.get(state[i]);
				int count = 0;
				for (int j = i + 1; j < state.length; j++) {
					if (shouldBeOnItsLeft.contains(state[j])) {
						count++;
					}
				}
				sumOfPerInv += count;
			} else {
				indexOfBlankTitleInState = i;
			}
		}
		// If the width is odd, then every solvable state has an even number of
		// inversions.
		if (noOfColumns % 2 != 0 && sumOfPerInv % 2 != 0) {
			return 100000;
		}
		// If the width is even, then every solvable state has
		else if (noOfColumns % 2 == 0) {

			int d = getRowCountFromBottom(indexOfBlankTitleInState, state.length);
			if ((d % 2 != 0 && sumOfPerInv % 2 != 0) || (d % 2 == 0 && sumOfPerInv % 2 == 0)) {
				// System.out.println("HERE d=" +d + " sumOfPerInv
				// "+sumOfPerInv);
				// return 100000;
				Utility.printMatrix(state);
				System.out.println("sumOfPerInv " + sumOfPerInv + " row no from bottom " + d);
				throw new RuntimeException("HERE IS THE UNSOLVABLE STATE");
			}
		}

		return sumOfPerInv;
	}

	public static final int getRowCountFromBottom(int indexOfBlankTitleInState, int noOfElementsInState) {
		int rowOfBlankInState = indexOfBlankTitleInState / noOfColumns;
		int noOfRowsInTheRecord = noOfElementsInState / noOfColumns;
		return noOfRowsInTheRecord - rowOfBlankInState;
	}

	// h3 Manhattan_Distance is not admissible, when diagonal steps are allowed,
	// because it overestimate the distance from current state to goal.
	// https://stackoverflow.com/questions/20547400/why-allowing-diagonal-movement-would-make-the-a-and-manhattan-distance-inadmiss
	public static final int manhattanDistance(int[] state) {
		if (!cached) {
			for (int i = 0; i < goalState.length; i++) {
				mapOfTitleNumberToIndex.put(goalState[i], i);

			}
			cached = true;
		}

		int md = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i] != 0) {
				int item = state[i];
				int indexOfitemInState = i;
				int indexOfItemInGoal = mapOfTitleNumberToIndex.get(item);
				int rowOfItemInState = indexOfitemInState / noOfColumns;
				int columnOfitemInState = indexOfitemInState % noOfColumns;
				int rowOfItemInGoal = indexOfItemInGoal / noOfColumns;
				int columnOfItemInGoal = indexOfItemInGoal % noOfColumns;
				int manhattanDistanceForTheTitle = Math.abs(rowOfItemInState - rowOfItemInGoal)
						+ Math.abs(columnOfitemInState - columnOfItemInGoal);
				// System.out.println("For item "+item +"
				// manhattanDistanceForTheTitle "+manhattanDistanceForTheTitle);
				md += manhattanDistanceForTheTitle;
			}
		}
		return md;
	}

	public static final int chebyShevDistance(int[] state) {
		int csd = 0;
		if (!cached) {
			for (int i = 0; i < goalState.length; i++) {
				mapOfTitleNumberToIndex.put(goalState[i], i);

			}
			cached = true;
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i] != 0) {
				int item = state[i];
				int indexOfitemInState = i;
				int indexOfItemInGoal = mapOfTitleNumberToIndex.get(item);
				int rowOfItemInState = indexOfitemInState / noOfColumns;
				int columnOfitemInState = indexOfitemInState % noOfColumns;
				int rowOfItemInGoal = indexOfItemInGoal / noOfColumns;
				int columnOfItemInGoal = indexOfItemInGoal % noOfColumns;
				int rowDiff = Math.abs(rowOfItemInState - rowOfItemInGoal);
				int colDiff = Math.abs(columnOfitemInState - columnOfItemInGoal);
				// System.out.println("For item " + item + "
				// manhattanDistanceForTheTitle " + Math.max(rowDiff, colDiff));
				csd += Math.max(rowDiff, colDiff);
			}

		}
		return csd;

	}

	public static final int chebyShevDistanceV2(int[] state) {
		int csd = 0;
		if (!cached) {
			for (int i = 0; i < goalState.length; i++) {
				mapOfTitleNumberToIndex.put(goalState[i], i);

			}
			cached = true;
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i] != 0) {
				int item = state[i];
				int indexOfitemInState = i;
				int indexOfItemInGoal = mapOfTitleNumberToIndex.get(item);
				int rowOfItemInState = indexOfitemInState / noOfColumns;
				int columnOfitemInState = indexOfitemInState % noOfColumns;
				int rowOfItemInGoal = indexOfItemInGoal / noOfColumns;
				int columnOfItemInGoal = indexOfItemInGoal % noOfColumns;
				int rowDiff = Math.abs(rowOfItemInState - rowOfItemInGoal);
				int colDiff = Math.abs(columnOfitemInState - columnOfItemInGoal);
				csd += (Math.min(rowDiff, colDiff) * (int) Math.sqrt(2)) + Math.abs(rowDiff - colDiff);
			}

		}
		return csd;

	}

	public static final int euclideanDistance(int[] state) {
		int ecd = 0;
		if (!cached) {
			for (int i = 0; i < goalState.length; i++) {
				mapOfTitleNumberToIndex.put(goalState[i], i);

			}
			cached = true;
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i] != 0) {
				int item = state[i];
				int indexOfitemInState = i;
				int indexOfItemInGoal = mapOfTitleNumberToIndex.get(item);
				int rowOfItemInState = indexOfitemInState / noOfColumns;
				int columnOfitemInState = indexOfitemInState % noOfColumns;
				int rowOfItemInGoal = indexOfItemInGoal / noOfColumns;
				int columnOfItemInGoal = indexOfItemInGoal % noOfColumns;
				ecd += (int) Math.sqrt(Math.pow(rowOfItemInState - rowOfItemInGoal, 2)
						+ Math.pow(columnOfitemInState - columnOfItemInGoal, 2));
			}
		}
		return ecd;

	}

	public static final int linearConflict(int[] state) {

		int lc = 0;
		if (!cachedLinearConflict) {
            System.out.println("called once");
			for (int i = 0; i < state.length / noOfColumns; i++) {
				int[] rowOfGoal = getRowtuple(i, goalState);
				rowNumberWithRowItems.put(i, rowOfGoal);
			}
			for (int j = 0; j < noOfColumns; j++) {
				int[] columnOfGoal = getColumntuple(j, state.length / noOfColumns, goalState);
				columnNumberWithColumnItems.put(j, columnOfGoal);
			}
			cachedLinearConflict = true;
		}
		for (int i = 0; i < state.length / noOfColumns; i++) {
			int[] rowOfState = getRowtuple(i, state);
			// int[] rowOfGoal =getRowtuple(i,goalState);
			lc += inversions(rowOfState, rowNumberWithRowItems.get(i), noOfColumns);
		}

		for (int j = 0; j < noOfColumns; j++) {
			int[] columnOfState = getColumntuple(j, state.length / noOfColumns, state);
			// int[] columnOfGoal
			// =getColumntuple(j,state.length/noOfColumns,goalState);
			// lc+=inversions(columnOfState,columnOfGoal,state.length/noOfColumns);
			lc += inversions(columnOfState, columnNumberWithColumnItems.get(j), state.length / noOfColumns);
		}
		return lc;

	}

	public static int inversions(int[] statePart, int[] goalPart, int count) {
		int inversions = 0;
		for (int i = 1, l; i < count; i++) {
			if (statePart[i] != 0 && 0 <= (l = Arrays.binarySearch(goalPart, statePart[i]))) {
				for (int j = 0, m; j < i; j++) {
					if (statePart[j] != 0 && 0 <= (m = Arrays.binarySearch(goalPart, statePart[j]))) {
						if ((statePart[i] < statePart[j]) != (i < j)) {
							inversions++;
						}
					}
				}
			}
		}
		return inversions;
	}

	private static int[] getRowtuple(int rowIndex, int[] state) {
		int[] result = new int[noOfColumns];

		System.arraycopy(state, rowIndex * noOfColumns, result, 0, noOfColumns);
		return result;
	}

	private static int[] getColumntuple(int columnIndex, int noOfRows, int[] state) {
		int[] result = new int[noOfRows];
		for (int i = 0, j = columnIndex; i < noOfRows; i++, j += noOfColumns) {
			result[i] = state[j];
		}
		return result;
	}

}
