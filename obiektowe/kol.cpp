#include <iostream>
#include <vector>

using namespace std;

template <typename T, int Num>
class Gatunek {
public:
    Gatunek() : z(new T) {}
    print() {
        cout << Num << ' ' << z->getGatunek() << endl;
    }
private:
    T* z;
};

class Zwierzeta {
public:
    virtual string getGatunek() = 0;
};

class Zyrafy : public Zwierzeta {
public:
    string getGatunek() {
        return "zyrafy";
    }
};
class Slonie : public Zwierzeta {
public:
    string getGatunek() {
        return "slonie";
    }
};
class Lwy : public Zwierzeta {
public:
    string getGatunek() {
        return "lwy";
    }
};
class Malpy : public Zwierzeta {
public:
    string getGatunek() {
        return "malpy";
    }
};

class Wagon {
public:
    Wagon() {};
    void dodaj(Zwierzeta* z) {
        if (v.size()) {
            if (z->getGatunek() == gatunek) {
                v.push_back(z);
            } else {
                throw logic_error("Gatunek zwierza jest niepoprawny");
            }
        } else {
            gatunek = z->getGatunek();
            v.push_back(z);
        }
    };
    void print() const {
        if (v.size()) {
            cout << '[' << gatunek << ", " << v.size() << ']';
        } else {
            cout << "[pusty wagon]";
        }
    }
private:
    vector<Zwierzeta*> v;
    string gatunek;
};

class Pociag {
public:
    Pociag() {};

    void dodaj(Wagon w) {
        s.push_back(w);
    }

    Wagon& odepnij() {
        Wagon& w = s.back();
        s.pop_back();
        return w;
    }

    void pokaz() {
        for (Wagon& w : s) {
            w.print();
            cout << "<->";
        }
        cout << endl;
    }

    void coNajwyzej(int n) {
        int itemsToDelete = s.size() - n;
        if (itemsToDelete > 0)
            s.resize(s.size() - itemsToDelete);
    }
private:
    vector<Wagon> s;
};

int main() {
    Pociag p;
    Wagon w1;
    Wagon w2;
    Lwy l;
    w1.dodaj(&l);
    w1.dodaj(&l);
    w1.dodaj(&l);
    p.dodaj(w1);
    p.dodaj(w1);
    p.dodaj(w1);
    p.dodaj(w1);
    p.dodaj(w2);
    p.pokaz();
    p.coNajwyzej(3);
    p.pokaz();

    Gatunek<Zyrafy, 2> g;
    g.print();

    return 0;
}