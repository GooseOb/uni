#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

struct Person {
    void parse(const string& source) {
        istringstream iss(source);
        string token;

        getline(iss, token, ';');
        name = token;

        getline(iss, token, ';');
        age = stoi(token);

        getline(iss, token);
        gender = token[0];
    };
    void save(const string& fileName) const {
        ofstream s(fileName, ios::app);
        s << name << ";" << age << ";" << gender << endl;
        s.close();
    }
    void print() const {
        cout
        << "name: " << name
        << " age: " << age
        << " gender: " << gender
        << endl;
    };
    void grow(int num = 1) {
        age += num;
    };
private:
    string name;
    unsigned int age;
    char gender;
};

int main() {
    char fileName[] = "people.dat";

    // p.parse("Ihho;4;F");
    // p.print();
    // p.save(fileName);
    ifstream ifs(fileName);
    if (!ifs.is_open()) throw runtime_error("Cannot open file");
    unsigned int c = 0;
    string line;
    while (getline(ifs, line)) ++c;
    Person* people = new Person[c];
    ifs.clear();
    ifs.seekg(0);
    for (int i=0; i < c; ++i) {
        getline(ifs, line);
        Person p;
        p.parse(line);
        people[i] = p;
    }
    ifs.clear();
    ifs.seekg(0);
    for (int i=0; i < c; ++i) {
        people[i].grow();
        people[i].save(fileName);
    }
    ifs.close();
    
    return 0;
}
