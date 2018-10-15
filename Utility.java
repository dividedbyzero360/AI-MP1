import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Utility {

	public static final void printMatrix(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == 4 || i == 8) {
				System.out.println();
			}
			if ((a[i] + "").length() == 2) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(a[i] + "  ");
			}

		}
		System.out.println("\n");
	}

	public static final void printMatrix(int[] a, String direction, int hericticsValue) {
		System.out.println(direction + " " + hericticsValue);
		for (int i = 0; i < a.length; i++) {
			if (i == 4 || i == 8) {
				System.out.println();
			}
			if ((a[i] + "").length() == 2) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(a[i] + "  ");
			}

		}
		System.out.println("\n");

	}

	public static final void writeGoalTraceToScreen(Node goalNode) {
		if (goalNode != null) {
			ArrayList<String> goalStateToRootTrace = new ArrayList<String>();
			while (goalNode != null) {
				goalStateToRootTrace.add(goalNode.getPrintableInfo() + goalNode.getMovement().getDirectionName());
				goalNode = goalNode.getParent();
			}

			for (int i = goalStateToRootTrace.size() - 1; i >= 0; i--) {
				System.out.println(goalStateToRootTrace.get(i));
			}

			System.out.println("Moves: " + (goalStateToRootTrace.size()-1));
		}

	}

	public static final void writeGoalTraceToFile(Node goalNode, AlgorithmName name, HeuristicsType h) {
		String fileName = "";
		if (name == AlgorithmName.DFS) {
			fileName = "puzzleDFS.txt";
		} else if (name == AlgorithmName.ITERATIVE_DFS) {
			fileName = "puzzleIterativeDFS.txt";
		} else {
			fileName = "puzzle" + name.toString() + "-" + h.toString()+".txt";
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		BufferedWriter bw = new BufferedWriter(fw);
		if (goalNode != null) {
			ArrayList<String> goalStateToRootTrace = new ArrayList<String>();
			while (goalNode != null) {
				goalStateToRootTrace.add(goalNode.getPrintableInfo() + goalNode.getMovement().getDirectionName());
				goalNode = goalNode.getParent();
			}
			try {

				for (int i = goalStateToRootTrace.size() - 1; i >= 0; i--) {

					bw.write(goalStateToRootTrace.get(i));
					bw.newLine();

				}
			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				try {
					bw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (bw != null)
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				if (fw != null)
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			System.out.println("Path cost "+ goalStateToRootTrace.size());
		}
		
	}

}