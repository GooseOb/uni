#pragma once
#include <iostream>
#include <string>

using namespace std;

class BaseTree {
public:
    BaseTree() : root(nullptr) {}

    virtual void insert(const string& key, int& ops) = 0;
    virtual void remove(const string& key, int& ops) = 0;
    virtual string search(const string& key, int& ops) = 0;

    int getDepth() const {
        return depth(root);
    }
    
    void displayInorder() const {
        inorderTraversal(root);
        cout << endl;
    }
protected:
    struct Node {
        string key;
        Node* left;
        Node* right;

        Node(const string& value) : key(value), left(nullptr), right(nullptr) {}
    };

    Node* root;
    
    int depth(Node* node) const {
        return node ? max(
            depth(node->left),
            depth(node->right)
        ) + 1 : 0;
    }

    void inorderTraversal(Node* node) const {
        if (node != nullptr) {
            inorderTraversal(node->left);
            cout << node->key << ' ';
            inorderTraversal(node->right);
        }
    }
};
