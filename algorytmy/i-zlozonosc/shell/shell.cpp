#include <iostream>
#include <fstream>
#include <cmath>
#include <time.h>
#include <chrono>

#define STEPS 50
#define STEP 10000

using namespace std;

void shellHelp(int* arr, int len, int gap, int offset)
{
    for (int i = offset; i < len; i += gap)
    {
        int key = arr[i];
        int j = i-gap;
        while (j >= 0 && arr[j] > key)
        {
            arr[j+gap] = arr[j];
            j -= gap;
        }
        arr[j+gap] = key;
    }
}

void shellSort(int* t, int n, int* h, int k)
{
    for (int i = 0; i < k; ++i)
    {
        int gap = h[i];
        for (int j = 0; j < gap; ++j)
            shellHelp(t, n, gap, j);
    }
}

class ShellBenchmarker
{
public:
    ShellBenchmarker(ostream& os = cout, char sep = ' ')
        :os(os), sep(sep) {};
    void benchmark(
        const string& label,
        int (*getSequenceItem)(int n, int k)
    ) {
        os << label << endl;
        int sequence[100000];
        for (int i = STEP; i <= STEPS*STEP; i += STEP)
        {
            int iterations = 0;
            do {
                sequence[iterations] = getSequenceItem(i, iterations);
            } while (sequence[iterations++] > 1);

            int* arr = new int[i];
            for (int j = 0; j < i; ++j)
                arr[j] = rand();

            long long startTimestamp = getTime();
            shellSort(arr, i, sequence, iterations);
            long long finalTimestamp = getTime();

            // for (int j = 0; j < i; ++j)
            //     cout << arr[j] << ' ';
            // cout << endl;

            os << i << sep << (finalTimestamp - startTimestamp) << endl;
            delete[] arr;
        }
    }
private:
    long long getTime()
    {
        using namespace chrono;
        return duration_cast<microseconds>(
            system_clock::now().time_since_epoch()
        ).count();
    }
    ostream& os;
    char sep;
};

int main()
{
    srand(time(NULL));

    // ofstream csv("shell.csv");
    // csv << "sep=;\n";

    ShellBenchmarker shell;

    shell.benchmark("Insert sort", [](int n, int k) {
        return 1;
    });

    shell.benchmark("Shell", [](int n, int k) {
        return (int)(n/pow(2, k));
    });

    shell.benchmark("Frank & Lazarus", [](int n, int k) {
        return 2 * (int)(n/pow(2, k+1)) + 1;
    });

    shell.benchmark("Custom #1", [](int n, int k) {
        return max((int)(n - pow(2, k-1)), 1);
    });

    shell.benchmark("Custom #2", [](int n, int k) {
        return (int)((2*n)/pow(2, k+1));
    });

    shell.benchmark("Custom #3", [](int n, int k) {
        return (int)pow(2, (int)log2(n) - k);
    });

    return 0;
}
