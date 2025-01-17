#include <iostream>
#include <vector>

using namespace std;

#define DELETED_KEY "[DELETED]"
template<typename Key, typename Value>
class LinearProbingHashTable {
private:
    vector<pair<Key, Value>> table;
    size_t capacity;
	size_t getIndex(const Key& key) const {
		return hash<Key>{}(key) % capacity;
	}

public:
    LinearProbingHashTable(size_t capacity) : capacity(capacity) {
        table.resize(capacity);
    }

    void insert(const Key& key, const Value& value) {
        size_t index = getIndex(key);
        size_t originalIndex = index;
        while (!(table[index].first.empty() || table[index].first == DELETED_KEY)) {
			index = (index + 1) % capacity;
			if (index == originalIndex) throw runtime_error("Table is full");
		}
		table[index].first = key;
		table[index].second = value;
    }

    bool find(const Key& key, Value& value) const {
        size_t index = getIndex(key);
        size_t originalIndex = index;
        do {
            if (table[index].first == key) {
                value = table[index].second;
                return true;
            }
            index = (index + 1) % capacity;
        } while (index != originalIndex && !table[index].first.empty());
        return false;
    }

    void remove(const Key& key) {
        size_t index = getIndex(key);
        size_t originalIndex = index;
        do {
            if (table[index].first == key) {
                table[index].first = DELETED_KEY;
                return;
            }
            index = (index + 1) % capacity;
        } while (index != originalIndex && !table[index].first.empty());
    }
};

int main() {
    int value;

    LinearProbingHashTable<string, int> linearTable(10);
    linearTable.insert("apple", 10);
    linearTable.insert("banana", 20);
    linearTable.insert("orange", 30);

    if (linearTable.find("banana", value)) {
        cout << "Found banana: " << value << endl;
    } else {
        cout << "Banana not found" << endl;
    }

    linearTable.remove("banana");

    if (linearTable.find("banana", value)) {
        cout << "Found banana: " << value << endl;
    } else {
        cout << "Banana not found" << endl;
    }

    return 0;
}

