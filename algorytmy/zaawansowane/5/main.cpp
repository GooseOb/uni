#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = 1e9;

void primMST(const vector<vector<pair<int, int>>> &adjList, int startVertex = 0) {
	int V = adjList.size();

    priority_queue<
		pair<int, int>,
		vector<pair<int, int>>,
		greater<pair<int, int>>
	> pq;

    vector<bool> visited(V, false);
    vector<int> parent(V, -1);
    vector<int> weights(V, INF);

    pq.push({0, startVertex});
    weights[startVertex] = 0;

	while (!pq.empty()) {
        int currVertex = pq.top().second;
        pq.pop();

        if (visited[currVertex]) continue;
        visited[currVertex] = true;

        for (auto [adjVertex, weight] : adjList[currVertex]) {
            if (!visited[adjVertex] && weight < weights[adjVertex]) {
                weights[adjVertex] = weight;
                parent[adjVertex] = currVertex;
                pq.push({weight, adjVertex});
            }
        }
    }

    cout << "Edges of Minimum Spanning Tree:\n";
    for (int i = 1; i < V; ++i) {
		cout << parent[i] << " - " << i << " (" << weights[i] << ")\n";
    }
}

int main() {
    primMST({
		// {{adjVertex, weight}, ...}
        {{1, 6}, {2, 1}, {3, 5}},
        {{0, 6}, {2, 5}, {4, 3}},
        {{0, 1}, {1, 5}, {3, 5}, {4, 6}, {5, 4}},
        {{0, 5}, {2, 5}, {5, 2}},
        {{1, 3}, {2, 6}, {5, 6}},
        {{2, 4}, {3, 2}, {4, 6}}
    });

	primMST({
        {{2, 3}},
        {{2, 10}, {3, 4}},
        {{0, 3}, {1, 10}, {3, 2}, {4, 6}},
        {{1, 4}, {2, 2}, {4, 1}},
        {{2, 6}, {3, 1}}
    });

    return 0;
}
