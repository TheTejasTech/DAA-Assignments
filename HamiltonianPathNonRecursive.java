import java.util.*;

public class HamiltonianPathNonRecursive {

	static class State {
		List<Integer> path;
		boolean[] visited;

		State(List<Integer> path, boolean[] visited) {
			this.path = new ArrayList<>(path);
			this.visited = Arrays.copyOf(visited, visited.length);
		}
	}

	public static List<Integer> findHamiltonianPath(int[][] graph) {
		int n = graph.length;

		Stack<State> stack = new Stack<>();

		// Try starting from each vertex
		for (int start = 0; start < n; start++) {

			boolean[] visited = new boolean[n];
			List<Integer> path = new ArrayList<>();

			path.add(start);
			visited[start] = true;

			stack.push(new State(path, visited));

			while (!stack.isEmpty()) {
				State current = stack.pop();

				// If path contains all vertices b solution found
				if (current.path.size() == n) {
					return current.path;
				}

				int last = current.path.get(current.path.size() - 1);

				// Try all possible next vertices
				for (int v = n - 1; v >= 0; v--) {
					if (!current.visited[v] && graph[last][v] == 1) {

						List<Integer> newPath = new ArrayList<>(current.path);
						boolean[] newVisited = Arrays.copyOf(current.visited, n);

						newPath.add(v);
						newVisited[v] = true;

						stack.push(new State(newPath, newVisited));
					}
				}
			}
		}

		// No Hamiltonian Path found
		return null;
	}
	static void printall(int[][] graph) {
		for(int i=0; i<graph.length; i++) {
			for(int j=0; j<graph[0].length; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int[][] graph = {
			{0, 1, 0, 1, 0},
			{1, 0, 1, 1, 1},
			{0, 1, 0, 0, 1},
			{1, 1, 0, 0, 1},
			{0, 1, 1, 1, 0}
		};

		List<Integer> path = findHamiltonianPath(graph);
		System.out.println("Adjcency matrix:  ");
		printall(graph);
		if (path == null) {
			System.out.println("Hamiltonian Path does not exist");
		} else {
			System.out.println("Hamiltonian Path:");
			for (int v : path) {
				System.out.print(v + " ");
			}
		}
	}
}