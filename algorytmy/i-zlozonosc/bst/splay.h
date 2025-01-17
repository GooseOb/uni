#pragma once
#include "tree.h"

class SplayTree : public BaseTree {
public:
    SplayTree() : BaseTree() {}

    void insert(const string& key, int& ops) {
        root = insert(root, key, ops);
    }

    void remove(const string& key, int& ops) {
        root = remove(root, key, ops);
    }

    string search(const string& key, int& ops) {
        root = splay(root, key, ops);
        return (root && root->key == key) ? key : "Not found";
    }
private:
    Node* splay(Node* node, const string& key, int& ops) {
        ++ops;
        if (node == nullptr || node->key == key)
            return node;

        if (key < node->key) {
            if (node->left == nullptr)
                return node;

            if (key < node->left->key) {
                // Zig-zig (left-left)
                node->left->left = splay(node->left->left, key, ops);
                node = rotateRight(node);
            } else if (key > node->left->key) {
                // Zig-zag (left-right)
                node->left->right = splay(node->left->right, key, ops);
                if (node->left->right != nullptr)
                    node->left = rotateLeft(node->left);
            }

            return (node->left == nullptr)
                ? node
                : rotateRight(node);
        } else {
            if (node->right == nullptr)
                return node;

            if (key < node->right->key) {
                // Zag-zig (right-left)
                node->right->left = splay(node->right->left, key, ops);
                if (node->right->left != nullptr)
                    node->right = rotateRight(node->right);
            } else if (key > node->right->key) {
                // Zag-zag (right-right)
                node->right->right = splay(node->right->right, key, ops);
                node = rotateLeft(node);
            }

            return (node->right == nullptr)
                ? node
                : rotateLeft(node);
        }
    }

    Node* insert(Node* node, const string& key, int& ops) {
        ++ops;
        if (node == nullptr)
            return new Node(key);

        node = splay(node, key, ops);

        Node* newRoot = new Node(key);
        if (key <= node->key) {
            newRoot->left = node->left;
            newRoot->right = node;
            node->left = nullptr;
        } else {
            newRoot->right = node->right;
            newRoot->left = node;
            node->right = nullptr;
        }
        return newRoot;
    }

    Node* remove(Node* node, const string& key, int& ops) {
        ++ops;
        if (node == nullptr)
            return nullptr;

        node = splay(node, key, ops);

        if (key == node->key) {
            Node* leftSubtree = node->left;
            Node* rightSubtree = node->right;
            delete node;

            if (leftSubtree == nullptr) {
                return rightSubtree;
            } else {
                Node* maxNode = findMax(leftSubtree, ops);
                maxNode->right = rightSubtree;
                return leftSubtree;
            }
        }

        return node;
    }

    Node* rotateRight(Node* node) {
        Node* newRoot = node->left;
        node->left = newRoot->right;
        newRoot->right = node;
        return newRoot;
    }

    Node* rotateLeft(Node* node) {
        Node* newRoot = node->right;
        node->right = newRoot->left;
        newRoot->left = node;
        return newRoot;
    }

    Node* findMax(Node* node, int& ops) const {
        while (node->right != nullptr) {
            ++ops;
            node = node->right;
        }
        return node;
    }
};
