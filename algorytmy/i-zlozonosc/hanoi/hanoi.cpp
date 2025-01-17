#include <iostream>
#include <fstream>
#include <stack>
#include <vector>

#define OUTPUT_FILE_NAME "hanoi.txt"
#define TABLE_FILE_NAME "hanoi.csv"

using namespace std;

stack<int> to132(stack<int> s, int s1, int s2, int s3) {
    s.push(s2);
    s.push(s3);
    s.push(s1);

    return s;
}

stack<int> to12(stack<int> s, int s1, int s2) {
    s.push(s2);
    s.push(s1);

    return s;
}

stack<int> to321(stack<int> s, int s1, int s2, int s3) {
    s.push(s1);
    s.push(s2);
    s.push(s3);

    return s;
}

vector<int*> hanoiHelp(stack<int> s, int n, int size) {
    int s1 = s.top();
    s.pop();
    int s2 = s.top();
    s.pop();

    if (n == 1)
        return vector<int*>{new int[2] {s1, s2}};

    int s3 = s.top();
    s.pop();

    int k = (size == 3) ? n - 1 : n / 2;

    vector<int*> left = hanoiHelp(to132(s, s1, s2, s3), k, size);
    vector<int*> center = hanoiHelp(to12(s, s1, s2), n - k, size - 1);
    vector<int*> right = hanoiHelp(to321(s, s1, s2, s3), k, size);

    left.insert(left.end(), center.begin(), center.end());
    left.insert(left.end(), right.begin(), right.end());

    return left;
}

vector<int*> hanoi(stack<int> s, int n, int size) {
    return n == 0
        ? vector<int*>{}
        : hanoiHelp(s, n, size);
}

void generateTable(int n = 20, int k = 7)
{
    ofstream file(TABLE_FILE_NAME);

    file << "sep=;\n";
    for (int i = 0; i <= n; ++i)
        file << ';' << i;
    file << endl;

    for (int i = 3; i <= k; ++i) {
        file << i;
        stack<int> s;
        for (int j = i; j > 0; --j)
            s.push(i);

        for (int j = 0; j <= n; ++j)
            file << ';' << hanoi(s, j, i).size();
        file << endl;
    }

    file.close();
}

int main() {
    int k, n;
    stack<int> s;

    cout << "liczba kolumn: ";
    cin >> k;
    cout << "liczba krazkow: ";
    cin >> n;

    for (int i = k; i > 0; --i)
        s.push(i);

    ofstream file(OUTPUT_FILE_NAME);

    file << n << endl << k << endl;

    int steps = 0;
    for (int* r : hanoi(s, n, k)) {
        ++steps;
        file << r[0] << ' ' << r[1] << endl;
    }

    file.close();
    cout
    << "rozwiazanie zostalo zapisane do " << OUTPUT_FILE_NAME << endl
    << "ilosc krokow: " << steps << endl;

    return 0;
}