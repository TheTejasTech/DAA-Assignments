import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {

	// Node class
	static class Node {
		char c;
		int freq;
		Node left, right;

		Node(char c, int freq) {
			this.c = c;
			this.freq = freq;
			left = right = null;
		}

		Node(int freq) {
			this.freq = freq;
			left = right = null;
		}
	}

	// Preorder traversal to generate Huffman Codes
	public static void generateCodes(Node root, ArrayList<String> result, String code) {
		if (root == null) return;

		// Leaf node (character found)
		if (root.left == null && root.right == null) {
			result.add(root.c + "   " + code);
		}

		generateCodes(root.left, result, code + "0");
		generateCodes(root.right, result, code + "1");
	}

	// Huffman Coding logic
	public static ArrayList<String> huffmanCodes(char[] chars, int[] freq) {

		// Min Heap (Priority Queue)
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

		// Add all characters to queue
		for (int i = 0; i < chars.length; i++) {
			pq.add(new Node(chars[i], freq[i]));
		}

		// Build Huffman Tree
		while (pq.size() > 1) {
			Node left = pq.poll();
			Node right = pq.poll();

			Node newNode = new Node(left.freq + right.freq);
			newNode.left = left;
			newNode.right = right;

			pq.add(newNode);
		}

		Node root = pq.poll();

		// Generate codes
		ArrayList<String> result = new ArrayList<>();
		generateCodes(root, result, "");

		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of characters: ");
		int n = sc.nextInt();

		char[] chars = new char[n];
		System.out.print("Enter characters: ");
		for (int i = 0; i < n; i++) {
			chars[i] = sc.next().charAt(0);
		}

		int[] freq = new int[n];
		System.out.print("Enter frequencies: ");
		for (int i = 0; i < n; i++) {
			freq[i] = sc.nextInt();
		}

		System.out.println("\nCharacters and their frequencies:");
		for (int i = 0; i < n; i++) {
			System.out.println(chars[i] + " : " + freq[i]);
		}

		ArrayList<String> codes = huffmanCodes(chars, freq);

		System.out.println("\nHuffman Codes:");
		for (String s : codes) {
			System.out.println(s);
		}

		sc.close();
	}
}