#include "bst.h"
#include "splay.h"
#include <fstream>
#include <vector>
#include <time.h>
#define STEP 100
#define MAX_SIZE 10000
#define REPEAT_AVERAGE 80

template<typename Tree = BaseTree>
void measure(const vector<string>& words, int num, ostream& output) {
    Tree tree;
    int ops = 0;
    int wordsSize = words.size();
    string* actualWords = new string[num];
    for (int i = 0; i < num; i++) {
        string word = words[rand() % wordsSize];
        tree.insert(word, ops);
        actualWords[i] = word;
    }

    output << tree.getDepth() << ';';
    output << (ops / num) << ';';
    ops = 0;
    for (int i = 0; i < REPEAT_AVERAGE; i++)
        tree.search(actualWords[rand() % num], ops);
    output << (ops / REPEAT_AVERAGE) << ';';
    ops = 0;
    for (int i = 0; i < REPEAT_AVERAGE; i++)
        tree.remove(actualWords[rand() % num], ops);
    output << (ops / REPEAT_AVERAGE) << ';';
    output << endl;
}

int main() {
    srand(time(NULL));
    vector<string> basicWords {"ALA", "OLA", "KOS", "PIES"};
    vector<string> allWords;
    vector<string> words4;

    string word;
    ifstream wordFile("slowa.txt");
    while (getline(wordFile, word)) {
        allWords.push_back(word);
        if (word.length() < 4)
            words4.push_back(word);
    }
    wordFile.close();

    ofstream outputFile("drzewa.csv");
    outputFile << "sep=;\nwysokosc;srednia wstawiania;srednia wyszukiwania;srednia usuwania\n";
    for (int i = STEP; i <= MAX_SIZE; i += STEP) {
        outputFile << i << endl;
        measure<BST>(basicWords, i, outputFile);
        measure<BST>(words4, i, outputFile);
        measure<BST>(allWords, i, outputFile);
        measure<SplayTree>(allWords, i, outputFile);
    }

    return 0;
}