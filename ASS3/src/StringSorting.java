/*A.Manjunath,CS10B001			Date:22/1/2012
 *   This Program tests sorting algorithms in SortingFunctions.java by passing a list of Strings to those functions
 *   It then prints Average time taken by the function for a given input size over 10 cycles.
 * */
import java.util.*;

public class StringSorting {
	public static void main(String[] args) {
		RandomString randString = new RandomString(); // random string variable
		List<String> SList = new LinkedList<String>(); // List to store strings can be ArrayList or LinkedList

		int n = 10, N;//n is length of each string, N is size of the SList
		double[][] AveTime = new double[4][1];
		for (N = 50; N < 10000; N *= 2) {
			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < N; i++) {
					SList.add(randString.nextString(n));
				}
				
				/*
				 * this part calculates runtime of the function
				 */
				long start = System.nanoTime();
				SortingFunctions.InsertionSort(SList,0,N);
				long end = System.nanoTime();
				SList.clear();
				AveTime[2][0] += (end - start) / 1000000L;
		
				for (int i = 0; i < N; i++) {
					SList.add(randString.nextString(n));
				}
				start = System.nanoTime();
				SortingFunctions.MergeSort(SList, 0,N);
				end = System.nanoTime();
				SList.clear();
				AveTime [0][0]+= (end - start) / 1000000L;
				
				for (int i = 0; i < N; i++) {
					SList.add(randString.nextString(n));
				}
				start = System.nanoTime();
				SortingFunctions.BubbleSort(SList, 0,N);
				end = System.nanoTime();
				SList.clear();
				AveTime[3] [0]+= (end - start) / 1000000L;
				
				for (int i = 0; i < N; i++) {
					SList.add(randString.nextString(n));
				}
				start = System.nanoTime();
				SortingFunctions.MergeAndInsertionSort(SList, 0,N);
				end = System.nanoTime();
				SList.clear();
				AveTime[1][0] += (end - start) / 1000000L;
			}
			System.out.printf("%.3f",AveTime[0][0]
					/ 10);
			System.out.printf(" %.3f",AveTime[1][0]
					/ 10);
			System.out.printf(" %.3f", AveTime[2][0]
					/ 10);
			System.out.printf(" %.3f\n" , AveTime[3][0]
					/ 10);
		}
	}
}
