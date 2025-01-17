#include <iostream>

#define MAX(a, b) ((a) > (b) ? (a) : (b))

using namespace std;

void LCS(const string& X, const string& Y) {
    int m = X.length();
    int n = Y.length();

	int** L = new int*[m + 1];

    for (int i = 0; i <= m; ++i)
        L[i] = new int[n + 1] {0};
    
	for (int i = 1; i <= m; ++i)
        for (int j = 1; j <= n; ++j)
			L[i][j] = X[i - 1] == Y[j - 1]
				? L[i - 1][j - 1] + 1
				: MAX(L[i - 1][j], L[i][j - 1]);

    int length = L[m][n];
    cout << "Długość najdłuższego wspólnego podciągu: " << length << endl;

    string lcs;
    while (m > 0 && n > 0) {
        if (X[m - 1] == Y[n - 1]) {
			lcs.insert(0, 1, X[m - 1]);
            --m;
            --n;
        } else if (L[m - 1][n] > L[m][n - 1]) {
            --m;
        } else {
            --n;
        }
    }

    cout << "Najdłuższy wspólny podciąg: " << lcs << endl;
}

int main() {
    string X = "abaabbaaa";
    string Y = "babab";

    LCS(X, Y);

    return 0;
}

