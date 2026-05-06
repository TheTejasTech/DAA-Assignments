#include <climits>
#include <iostream>
#include <vector>

using namespace std;

struct Edge {
    int src;
    int dest;
    int weight;
};

void printPath(int vertex, const vector<int>& parent) {
    if (vertex == -1) {
        return;
    }
    printPath(parent[vertex], parent);
    cout << vertex << " ";
}

void bellmanFord(const vector<Edge>& edges, int V, int source) {
    vector<int> dist(V, INT_MAX);
    vector<int> parent(V, -1);

    dist[source] = 0;

    // Bottom-up iterative relaxation for V-1 passes.
    for (int i = 1; i <= V - 1; i++) {
        bool updated = false;
        for (const Edge& edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int w = edge.weight;

            if (dist[u] != INT_MAX && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                parent[v] = u;
                updated = true;
            }
        }
        if (!updated) {
            break;
        }
    }

    for (const Edge& edge : edges) {
        int u = edge.src;
        int v = edge.dest;
        int w = edge.weight;
        if (dist[u] != INT_MAX && dist[u] + w < dist[v]) {
            cout << "Graph contains negative weight cycle.\n";
            return;
        }
    }

    cout << "\nShortest path and minimum cost from source " << source << ":\n";
    for (int vertex = 0; vertex < V; vertex++) {
        cout << "Vertex " << vertex << ": ";
        if (dist[vertex] == INT_MAX) {
            cout << "No path available\n";
            continue;
        }

        cout << "Cost = " << dist[vertex] << ", Path = ";
        printPath(vertex, parent);
        cout << "\n";
    }

    cout << "\nTime Complexity: O(V*E)\n";
    cout << "Space Complexity: O(V)\n";
}

int main() {
    int V, E;

    cout << "Enter number of vertices: ";
    cin >> V;
    cout << "Enter number of edges: ";
    cin >> E;

    vector<Edge> edges(E);
    cout << "Enter edges as: source destination weight\n";
    for (int i = 0; i < E; i++) {
        cin >> edges[i].src >> edges[i].dest >> edges[i].weight;
    }

    int source;
    cout << "Enter source vertex: ";
    cin >> source;

    bellmanFord(edges, V, source);
    return 0;
}