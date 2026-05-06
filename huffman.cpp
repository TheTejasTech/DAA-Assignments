#include <iostream>
#include <vector>
#include <queue>
#include <string>

using namespace std;

// A Huffman tree node
struct Node {
    char data;
    unsigned freq;
    Node *left, *right;

    Node(char data, unsigned freq) {
        left = right = NULL;
        this->data = data;
        this->freq = freq; 
    }
};

// Comparison object to be used to order the heap
struct compare {
    bool operator()(Node* l, Node* r) {
        return (l->freq > r->freq);
    }
};

// Print Huffman codes from the root of the Huffman Tree
void printCodes(struct Node* root, string str) {
    if (!root)
        return;

    if (root->data != '$') {
        cout << root->data << "\t" << (int)root->data << "\t" << str << "\n";
    }

    printCodes(root->left, str + "0");
    printCodes(root->right, str + "1");
}

// Main function to build Huffman Tree and print codes
void buildHuffmanTree(vector<char>& data, vector<int>& freq, int size) {
    struct Node *left, *right, *top;
    
    // Create a min heap & inserts all characters of data[]
    priority_queue<Node*, vector<Node*>, compare> minHeap;

    for (int i = 0; i < size; ++i)
        minHeap.push(new Node(data[i], freq[i]));

    // Iterate while size of heap doesn't become 1
    while (minHeap.size() != 1) {
        // Extract the two minimum freq items from min heap
        left = minHeap.top();
        minHeap.pop();

        right = minHeap.top();
        minHeap.pop();

        // Create a new internal node with frequency equal to the sum of the
        // two nodes frequencies. '$' is a special value for internal nodes.
        top = new Node('$', left->freq + right->freq);
        top->left = left;
        top->right = right;
        minHeap.push(top);
    }

    // Print the generated codes by traversing the tree
    printCodes(minHeap.top(), "");
}

int main() {
    int n;
    cout << "Enter number of characters: ";
    cin >> n;

    vector<char> characters(n);
    cout << "Enter characters: ";
    for (int i = 0; i < n; i++) {
        cin >> characters[i];
    }

    vector<int> frequencies(n);
    cout << "Enter frequency of characters: ";
    for (int i = 0; i < n; i++) {
        cin >> frequencies[i];
    }

    cout << "\nCharacters and their frequencies are:\n";
    for (int i = 0; i < n; i++) {
        cout << characters[i] << " " << frequencies[i] << "\n";
    }

    cout << "\nCharacters, their ASCII values and their Huffman Codes are:\n";
    buildHuffmanTree(characters, frequencies, n);

    return 0;
}


