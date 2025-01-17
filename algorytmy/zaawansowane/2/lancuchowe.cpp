#include <iostream>
#include <list>
#include <vector>

using namespace std;

template<typename Key, typename Value>
class ChainingHashTable {
private:
    vector<list<pair<Key, Value>>> table;
    size_t capacity;

	list<pair<Key, Value>>& getList(const Key& key) {
		size_t index = hash<Key>{}(key) % capacity;
        return table[index];
	}

public:
    ChainingHashTable(size_t capacity) : capacity(capacity) {
        table.resize(capacity);
    }

    void insert(const Key& key, const Value& value) {
		auto& list = getList(key);
        for (const auto& kv : list)
            if (kv.first == key) return;
		list.emplace_back(key, value);
    }

    bool find(const Key& key, Value& value) {
        for (const auto& kv : getList(key))
            if (kv.first == key) {
                value = kv.second;
                return true;
            }
        return false;
    }

    void remove(const Key& key) {
        auto& list = getList(key);
        for (auto it = list.begin(); it != list.end(); ++it)
            if (it->first == key) {
                list.erase(it);
                return;
            }
    }
};

int main() {
	int value;

	ChainingHashTable<string, int> chainTable(10);
    chainTable.insert("apple", 10);
    chainTable.insert("banana", 20);
    chainTable.insert("orange", 30);

    if (chainTable.find("banana", value)) {
        cout << "Found banana: " << value << endl;
    } else {
        cout << "Banana not found" << endl;
    }

    chainTable.remove("banana");

	if (chainTable.find("banana", value)) {
        cout << "Found banana: " << value << endl;
    } else {
        cout << "Banana not found" << endl;
	}

    return 0;
}

