import java.util.*;

class Node implements Comparable<Node> {
	int[][] reducedMatrix;
	int cost;
	int vertex;
	int level;
	List<Integer> path;

	Node(int[][] matrix, List<Integer> path, int level, int vertex) {
		this.reducedMatrix = matrix;
		this.path = new ArrayList<>(path);
		this.level = level;
		this.vertex = vertex;
		this.cost = 0;
	}

	@Override
	public int compareTo(Node other) {
		return this.cost - other.cost;
	}
}

public class TSPBranchAndBound {
	static final int INF = Integer.MAX_VALUE;
	static int reduceMatrix(int[][] matrix) {
		int reductionCost = 0;

		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			int rowMin = INF;
			for (int j = 0; j < n; j++) {
				rowMin = Math.min(rowMin, matrix[i][j]);
			}

			if (rowMin != INF && rowMin != 0) {
				reductionCost += rowMin;
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] != INF)
						matrix[i][j] -= rowMin;
				}
			}
		}

		for (int j = 0; j < n; j++) {
			int colMin = INF;
			for (int i = 0; i < n; i++) {
				colMin = Math.min(colMin, matrix[i][j]);
			}

			if (colMin != INF && colMin != 0) {
				reductionCost += colMin;
				for (int i = 0; i < n; i++) {
					if (matrix[i][j] != INF)
						matrix[i][j] -= colMin;
				}
			}
		}

		return reductionCost;
	}

	// Copy matrix
	static int[][] copyMatrix(int[][] matrix) {
		int n = matrix.length;
		int[][] newMatrix = new int[n][n];
		for (int i = 0; i < n; i++)
			newMatrix[i] = matrix[i].clone();
		return newMatrix;
	}

	public static void solveTSP(int[][] costMatrix) {
		int n = costMatrix.length;

		PriorityQueue<Node> pq = new PriorityQueue<>();

		// Root node
		List<Integer> path = new ArrayList<>();
		path.add(0);

		int[][] rootMatrix = copyMatrix(costMatrix);
		Node root = new Node(rootMatrix, path, 0, 0);
		root.cost = reduceMatrix(root.reducedMatrix);

		pq.add(root);

		int finalCost = 0;
		List<Integer> finalPath = new ArrayList<>();

		while (!pq.isEmpty()) {
			Node minNode = pq.poll();

			int i = minNode.vertex;

			if (minNode.level == n - 1) {
				minNode.path.add(0); // return to start
				finalPath = minNode.path;
				finalCost = minNode.cost;
				break;
			}

			for (int j = 0; j < n; j++) {
				if (minNode.reducedMatrix[i][j] != INF) {

					int[][] newMatrix = copyMatrix(minNode.reducedMatrix);
					for (int k = 0; k < n; k++) {
						newMatrix[i][k] = INF;
						newMatrix[k][j] = INF;
					}

					newMatrix[j][0] = INF;

					List<Integer> newPath = new ArrayList<>(minNode.path);
					newPath.add(j);

					Node child = new Node(newMatrix, newPath, minNode.level + 1, j);

					int cost = minNode.cost + minNode.reducedMatrix[i][j]
					           + reduceMatrix(child.reducedMatrix);

					child.cost = cost;

					pq.add(child);
				}
			}
		}

		System.out.println("Minimum Cost: " + finalCost);
		System.out.print("Path: ");
		for (int v : finalPath) {
			System.out.print(v + " ");
		}
	}

	public static void main(String[] args) {
		int[][] costMatrix = {
			{INF, 10, 15, 20},
			{10, INF, 35, 25},
			{15, 35, INF, 30},
			{20, 25, 30, INF}
		};
		for(int i=0; i<costMatrix.length; i++) {
			for(int j=0; j<costMatrix[0].length; j++) {
				if(2147483647 == costMatrix[i][j]) {
					System.out.print("INF ");
				} else {
					System.out.print(costMatrix[i][j] + " ");
				}
			}
			System.out.println();
		}
		solveTSP(costMatrix);
	}
}