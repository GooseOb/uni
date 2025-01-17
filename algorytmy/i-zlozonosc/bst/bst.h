#pragma once
#include "tree.h"

class BST : public BaseTree {
public:
    BST() : BaseTree() {}

    void insert(const string& key, int& ops) {
        root = insert(root, key, ops);
    }

    void remove(const string& key, int& ops) {
        root = remove(root, key, ops);
    }

    string search(const string& key, int& ops) {
        Node* result = search(root, key, ops);
        return result ? result->key : "Not found";
    }
private:
    Node* insert(Node* node, const string& key, int& ops) {
        ++ops;
        if (node == nullptr)
            return new Node(key);

        if (key <= node->key) {
            node->left = insert(node->left, key, ops);
        } else {
            node->right = insert(node->right, key, ops);
        }

        return node;
    }

    Node* search(Node* node, const string& key, int& ops) const {
        ++ops;
        if (node == nullptr || node->key == key)
            return node;

        return search(key < node->key
            ? node->left
            : node->right
        , key, ops);
    }

    Node* remove(Node* node, const string& key, int& ops) {
        ++ops;
        if (node == nullptr)
            return nullptr;

        if (key < node->key) {
            node->left = remove(node->left, key, ops);
        } else if (key > node->key) {
            node->right = remove(node->right, key, ops);
        } else {
            if (node->left == nullptr) {
                Node* temp = node->right;
                delete node;
                return temp;
            } else if (node->right == nullptr) {
                Node* temp = node->left;
                delete node;
                return temp;
            }

            Node* temp = findMin(node->right, ops);

            node->key = temp->key;

            node->right = remove(node->right, temp->key, ops);
        }

        return node;
    }

    Node* findMin(Node* node, int& ops) const {
        while (node->left != nullptr) {
            ++ops;
            node = node->left;
        }
        return node;
    }
};