import java.util.*;

public class BellmanFord {

	public static void bellman(int v, int[][] edges, int src) {
		int[] dist = new int[v + 1];
		int[] parent = new int[v + 1];

		Arrays.fill(dist, (int) 1e8);
		Arrays.fill(parent, -1);

		dist[src] = 0;

		// Relax edges V-1 times
		for (int i = 1; i <= v - 1; i++) {
			for (int[] e : edges) {
				int u = e[0];
				int vr = e[1];
				int wt = e[2];

				if (dist[u] != 1e8 && dist[vr] > dist[u] + wt) {
					dist[vr] = dist[u] + wt;
					parent[vr] = u;
				}
			}
		}

		// Check negative cycle
		for (int[] e : edges) {
			int u = e[0];
			int vr = e[1];
			int wt = e[2];

			if (dist[u] != 1e8 && dist[vr] > dist[u] + wt) {
				System.out.println("Negative weight cycle detected!");
				return;
			}
		}

		// Print results clearly
		System.out.println("Vertex\tDistance\tPath");
		for (int i = 1; i <= v; i++) {
			if (dist[i] == (int) 1e8) {
				System.out.println(src + " -> " + i + "\tINF\t\tNo Path");
			} else {
				System.out.print(src + " -> " + i + "\t" + dist[i] + "\t\t");
				printPath(i, parent);
				System.out.println();
			}
		}
	}

	// Function to print path
	public static void printPath(int v, int[] parent) {
		if (v == -1) return;
		printPath(parent[v], parent);
		System.out.print(v + " ");
	}

	public static void main(String[] args) {

		// -------- Test Case 1 --------
		int[][] edges1 = {
			{1, 2, 3},
			{2, 3, -2},
			{3, 4, -1},
			{4, 5, 4},
			{2, 4, -1},
			{5, 3, -1},
		};

		System.out.println("Test Case 1:");
		bellman(5, edges1, 1);

		// -------- Test Case 2 --------
		int[][] edges2 = {
			{1, 2, 4},
			{1, 3, 5},
			{2, 3, -10}
		};

		System.out.println("\nTest Case 2:");
		bellman(3, edges2, 1);

		// -------- Test Case 3 --------
		int[][] edges3 = {
			{1, 2, 4},
			{2, 3, 3},
			{4, 5, -2},
			{5, 4, -3}
		};

		System.out.println("\nTest Case 3:");
		bellman(5, edges3, 1);
	}
}