#include <iostream>

using namespace std;

struct node
{
    int val;
    node *left;
    node *right;
    int ht;
};

node *create(int val)
{
    node *n;
    n = new node;
    n->val = val;
    n->ht = 1;
    return n;
}

node *insert(node *n, int val)
{
    if (n == NULL) 
    {
        return create(val);
    }

    // First perform BST insert
    node *newNode;
    if (val < n->val)
    {
        // insert left if val < cur node
        newNode = insert(n->left, val);
        n->left = newNode;
    }
    else
    {
        // insert right if val >= cur node
        newNode = insert(n->right, val);
        n->right = newNode;
    }

    // update parent while coming out of recursion call stack
    if (newNode->ht == n->ht)
    {
        n->ht = n->ht + 1;
    }

    int lht = n->left == NULL ? 0 : n->left->ht;
    int rht = n->right == NULL ? 0 : n->right->ht;

    if (lht - rht > 1) // means left heavy
    {

        // 1. Left right case
        node *l = n->left;

        int llht = l->left == NULL ? 0 : l->left->ht;
        int lrht = l->right == NULL ? 0 : l->right->ht;
        int lt = llht - lrht;

        if (lt == -1) // determines left right case
        { 
            // Convert it to left left case
            node *rt = l->right;
            l->right = rt->left;
            rt->left = l;
            n->left = rt;

            n->left->left->ht = n->ht - 2;
            n->left->ht = n->ht - 1;
        }

        // 2. Left Left case
        l = n->left;
        n->left = l->right;
        l->right = n;
        n->ht = n->ht - 2;
        return l;
    }
    
    if ((lht - rht) < -1) // means right heavy
    {
        // 1. right left case
        node *r = n->right;

        int rlht = r->left == NULL ? 0 : r->left->ht;
        int rrht = r->right == NULL ? 0 : r->right->ht;
        int rt = rlht - rrht;

        if (rt == 1) // determines right left case
        { 
            // Convert it to right right case
            node *lt = r->left;
            r->left = lt->right;
            lt->right = r;
            n->right = lt;

            n->right->right->ht = n->ht - 2;
            n->right->ht = n->ht - 1;
        }
        // 2. right right case
        r = n->right;
        n->right = r->left;
        r->left = n;
        n->ht = n->ht - 2;
        return r;
    }

    return n;
}

void inorder(node *node)
{

    if (node == NULL)
        return;
    inorder(node->left);
    int lht = node->left == NULL ? 0 : node->left->ht;
    int rht = node->right == NULL ? 0 : node->right->ht;
    cout << node->val << " ";
    inorder(node->right);
}

void preorder(node *node)
{

    if (node == NULL)
        return;

    int lht = node->left == NULL ? 0 : node->left->ht;
    int rht = node->right == NULL ? 0 : node->right->ht;
    cout << node->val << " ";

    preorder(node->left);
    preorder(node->right);
}

int main()
{
    cout << "Starting AVL Tree" << endl;
    node *root = create(6);

    root = insert(root, 3);
    root = insert(root, 2);
    root = insert(root, 4);
    root = insert(root, 5);
    root = insert(root, 10);
    root = insert(root, 11);
    inorder(root);
    cout << endl;
    preorder(root);
    cout << endl;
    return 0;
}
