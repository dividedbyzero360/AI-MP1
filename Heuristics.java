import java.util.*;

public class Heuristics {

	private static int[] goalState;

	private static boolean cached = false;
	private static HashMap<Integer, ArrayList<Integer>> numbersThatFollowANumberInGoalState = new HashMap<Integer, ArrayList<Integer>>();
	private static HeuristicsType whichHeristics;
	private static HashMap<Integer, Integer> mapOfTitleNumberToIndex = new HashMap<>();
	public static int noOfColumns = 4;

	public static final void setGoalState(int[] goalState) {
		Heuristics.cached = false;
		Heuristics.goalState = goalState;
	}

	public static final void setWhichHeristics(HeuristicsType whichHeristics) {
		Heuristics.whichHeristics = whichHeristics;
		Heuristics.cached = false;
	}

	public static final int getHerirsticValue(int[] state) {
		if (whichHeristics == HeuristicsType.MisplacedTiles) {
			return Heuristics.numberOfMisplacedTitlesSlow(state);
		} else if (whichHeristics == HeuristicsType.SUM_OF_PI) {
			return Heuristics.sumPermutationInversionSlow(state);
		} else if (whichHeristics == HeuristicsType.MANHATTAN_DISTANCE) {
			return Heuristics.manhattanDistance(state);
		} else if (whichHeristics == HeuristicsType.Cheby_Shev_Distance) {
			return Heuristics.chebyShevDistance(state);
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
				//System.out.println("For item " + item + " manhattanDistanceForTheTitle " + Math.max(rowDiff, colDiff));
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
	
	public static final int euclideanDistance(int[] state)
	{
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
			    ecd+=(int)Math.sqrt(Math.pow(rowOfItemInState-rowOfItemInGoal,2)+Math.pow(columnOfitemInState-columnOfItemInGoal,2));
			}
		}
		return ecd;
		
	}

}
