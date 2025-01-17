#include <iostream>
#include <unordered_map>

using namespace std;

bool matchesAt(const string& str, int startI, const string& substr) {
	for (int i = 0; i < substr.length(); ++i) {
		if (substr[i] != str[startI+i]) return false;
	}
	return true;
}

void printSearchStart(const string& name, const string& str, const string& substr) {
	cout << "\n[" << name << "] Searching '" << substr << "' in '" << str << "'\n";
}
void report(int pos) {
	cout << "Found at " << pos << endl;
}

namespace naiwny {
	void search(const string& str, const string& substr) {
		printSearchStart("naiwny", str, substr);
		if (substr.length() < 1) return;
		int maxI = str.length() - substr.length();
		for (int i = 0; i <= maxI; ++i) {
			if (matchesAt(str, i, substr)) report(i);
		}
	}
}

namespace sunday {
	void search(const string& str, const string& substr) {
		printSearchStart("Sunday", str, substr);
		int substrLen = substr.length();
		if (substrLen < 1) return; 
		int strLen = str.length();
		
		int skipTable[256] {-1};
        for (int i = substrLen - 1; i >= 0; --i) {
            skipTable[substr[i]] = i;
        }

		int i = 0;
		int maxI = strLen - substrLen;
		while (i <= maxI) {
			if (matchesAt(str, i, substr)) report(i);
			i += substrLen;
			if (i < strLen) i -= skipTable[str[i]];
		}
	}

	void searchUsingPairs(const string& str, const string& substr) {
		printSearchStart("Sunday: pairs", str, substr);
		int substrLen = substr.length();
		if (substrLen < 1) return; 
		int strLen = str.length();

		unordered_map<string, int> skipTable;
        for (int i = 0; i < substrLen - 1; ++i) {
            string pair = substr.substr(i, 2);
            if (skipTable.find(pair) == skipTable.end()) {
                skipTable[pair] = substrLen - i - 1;
            }
        }

        int i = 0;
        int maxI = strLen - substrLen;
        while (i <= maxI) {
            if (matchesAt(str, i, substr)) report(i);
			string pair = str.substr(i + substrLen - 1, 2);
			i += skipTable.find(pair) == skipTable.end()
				? substrLen
				: skipTable[pair];
        }
	}
}

namespace kmp {
	void computeLPS(const string& substr, int* lps, int len) {
		int prevPrefixLen = 0;
		lps[0] = 0;

		int i = 1;
		while (i < len) {
			if (substr[i] == substr[prevPrefixLen]) {
				++prevPrefixLen;
				lps[i] = prevPrefixLen;
				++i;
			} else {
				if (prevPrefixLen == 0) {
					lps[i] = 0;
					++i;
				} else {
					prevPrefixLen = lps[prevPrefixLen - 1];
				}
			}
		}
	}

	void search(const string& str, const string& substr) {
		printSearchStart("Knuth–Morris–Pratt", str, substr);
		int substrLen = substr.length();
		if (substrLen < 1) return;
		int strLen = str.length();

		int lps[substrLen];
		computeLPS(substr, lps, substrLen);

		int strI = 0;
		int substrI = 0;
		do {
			if (str[strI] == substr[substrI]) {
				++strI;
				++substrI;
			}

			if (substrI == substrLen) {
				report(strI - substrI);
				substrI = lps[substrI - 1];
			} else if (strI < strLen && str[strI] != substr[substrI]) {
				if (substrI == 0) {
					++strI;
				} else {
					substrI = lps[substrI - 1];
				}
			}
		} while (strI < strLen);
	}
}

int main() {
	string str = "abaababcbcbababa";
	string substr = "aba";

	naiwny::search(str, substr);
	sunday::search(str, substr);
	sunday::searchUsingPairs(str, substr);
	kmp::search(str, substr);
	
	return 0;
}
