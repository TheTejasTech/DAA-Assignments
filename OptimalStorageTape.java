import java.util.Scanner;

// public class OptimalStorageTape {

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter the number of files: ");
//         int n = sc.nextInt();
//         // directly sorting files using priority queue
//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter length of file " + (i+1) + ": ");
//             pq.add(sc.nextInt());
//         }

//         int ans = 0;
//         while (pq.size() > 1) {
//             int e1 = pq.poll();
//             int e2 = pq.poll();

//             int sum = e1 + e2;
//             ans += sum;
//             pq.add(sum);
//         }

//         System.out.println("Minimum cost of storage tape is: " + ans);
//         sc.close();

//     }
// }

import java.util.Arrays;

public class OptimalStorageTape {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the numberof files going ot be executed: ");
		int n = sc.nextInt();
		int[] files = new int[n];
		
		for(int i=0;i<n;i++){
		    System.out.print("Enter the length of programm no "+(int)(i+1)+" : ");
		    files[i] = sc.nextInt();
		}
		
		Arrays.sort(files);
		
		int totaltime  = 0;
		System.out.print("Files are going to be executed as: ");
		for(int i=0;i<n;i++){
		    System.out.print(" "+(files[i])+" ");
		    totaltime += files[i];
		}
		int rettime = totaltime / files.length;System.out.println("");
		System.out.println("Cummulative time required to execute all: "+totaltime);
		System.out.println("Mean retrival time: "+rettime);
		
		sc.close();
	}
}