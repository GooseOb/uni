#include <iostream>
#include <vector>

using namespace std;

const int INF = 1e9;

vector<vector<int>> floydWarshall(vector<vector<int>> graph) {
    int V = graph.size();

    for (int k = 0; k < V; ++k)
        for (int i = 0; i < V; ++i)
            for (int j = 0; j < V; ++j)
				if (graph[i][k] != INF &&
					graph[k][j] != INF &&
					graph[i][k] + graph[k][j] < graph[i][j]
				) graph[i][j] = graph[i][k] + graph[k][j];

	return graph;
}

void printGraph(const vector<vector<int>>& graph) {
    int V = graph.size();

	for (int i = 0; i < V; ++i) {
        for (int j = 0; j < V; ++j) {
            if (graph[i][j] == INF) {
                cout << "INF ";
			} else {
                cout << graph[i][j] << "\t";
			}
		}
        cout << endl;
    }
	cout << endl;
}

void expectToBeEqual(const vector<vector<int>>& recievedGraph, const vector<vector<int>>& expectedGraph) {
	int V = expectedGraph.size();

	for (int i = 0; i < V; ++i)
		for (int j = 0; j < V; ++j)
			if (recievedGraph[i][j] != expectedGraph[i][j]) {
				cout << "FAILED\nExpected:\n";
				printGraph(expectedGraph);
				cout << "Recieved:\n";
				printGraph(recievedGraph);
				return;
			}

	cout << "PASSED\n";
}

int main() {
	expectToBeEqual(
		floydWarshall({
			{0, 1, 43},
			{1, 0, 6},
			{-1, -1, 0},
		}), {
			{0, 1, 7},
			{1, 0, 6},
			{-1, -1, 0},
		}
	);

	expectToBeEqual(
		floydWarshall({
	        {0, 5, INF, INF},
	        {INF, 0, 5, 3},
			{-3, INF, 0, INF},
			{2, INF, -5, 0},
		}), {
	        {0, 5, 3, 8},
	        {-5, 0, -2, 3},
			{-3, 2, 0, 5},
			{-8, -3, -5, 0},
		}
	);

    return 0;
}

