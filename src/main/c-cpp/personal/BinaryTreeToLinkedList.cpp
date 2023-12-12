#include <cstdio>

using namespace std;

struct node
{
    int data;
    node *left;
    node *right;
};

node *flattenBinaryTree(node *root)
{
    if (root == NULL)
    {
        return root;
    }

    if (root->left != NULL)
    {
        node *left = flattenBinaryTree(root->left);
        while (left->right != NULL)
        {
            left = left->right;
        }
        left->right = root;
        root->left = left;
    }

    if (root->right != NULL)
    {
        node *right = flattenBinaryTree(root->right);

        while (right->left != NULL)
        {
            right = right->left;
        }
        right->left = root;
        root->right = right;
    }

    return root;
}

node *bintree2list(node *root)
{
    if (root == NULL)
    {
        return root;
    }
    root = flattenBinaryTree(root);
    while (root->left != NULL)
    {
        root = root->left;
    }
    return root;
}

/* Helper function that allocates a new node with the
   given data and NULL left and right pointers. */
node *newNode(int data)
{
    node *new_node = new node;
    new_node->data = data;
    new_node->left = new_node->right = NULL;
    return (new_node);
}

/* Function to print nodes in a given doubly linked list */
void printList(node *node)
{
    while (node != NULL)
    {
        printf("%d ", node->data);
        node = node->right;
    }
}

/*
 * This is amazon's question
 */
void printEnlightenedNodes(node *node, int depth, int *depthSoFar)
{
    if (node == NULL)
        return;

    if (depth > (*depthSoFar))
    {
        // printf("depth: %d, depthSoFar: %d\n", depth, (*depthSoFar));
        (*depthSoFar) = depth;
        printf("%d ", node->data);
    }

    printEnlightenedNodes(node->right, depth + 1, depthSoFar);
    printEnlightenedNodes(node->left, depth + 1, depthSoFar);
}

/* Driver program to test above functions*/
int main()
{
    // Let us create the tree shown in above diagram
    node *root = newNode(10);
    root->left = newNode(12);
    root->right = newNode(15);
    root->left->left = newNode(25);
    root->left->left->right = newNode(41);
    root->left->left->right->left = newNode(42);
    root->left->right = newNode(30);
    root->left->right->left = newNode(40);
    root->right->left = newNode(36);

    printf("Print Enlightened Nodes -> ");
    int depthSoFar = 0;
    int depth = 1;
    printEnlightenedNodes(root, depth, &depthSoFar);
    printf("\n");

    // Convert to DLL
    node *head = bintree2list(root);

    // Print the converted list
    printf("Print DoublyLinked List -> ");
    printList(head);
    printf("\n");

    return 0;
}
