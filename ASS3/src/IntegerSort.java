/*A.Manjunath,CS10B001			Date:22/1/2012
 *   This Program tests sorting algorithms in SortingFunctions.java by passing a list of Integers to those functions
 *   It then prints Average time taken by the function for a given input size over 10 cycles.
 * */
import java.util.*;

public class IntegerSort {
	public static void main(String[] args) {
		Random rand = new Random();//Random number variable
		List<Integer> IList = new LinkedList<Integer>();//List to store Randomly generated integers 
		int N;//size of IList
		double[][] AveTime= new double[4][1];
		for(N=50;N<10000;N*=2){
			for(int i=0;i<10;i++){
				for (int j = 0;j < N; j++)
					IList.add(j, rand.nextInt(Integer.MAX_VALUE));
				double start,end;
				/*
				 *this part calculates runtime of the function
				 * */
				for (int j = 0; j < N; j++) {
					IList.add(rand.nextInt(Integer.MAX_VALUE));
				}
				
				/*
				 * this part calculates runtime of the function
				 */
				start = System.nanoTime();
				SortingFunctions.InsertionSort(IList,0,N);
				end = System.nanoTime();
				IList.clear();
				AveTime[2][0] += (end - start) / 1000000L;
		
				for (int j = 0; j < N; j++) {
					IList.add(rand.nextInt(Integer.MAX_VALUE));
				}
				start = System.nanoTime();
				SortingFunctions.MergeSort(IList, 0,N);
				end = System.nanoTime();
				IList.clear();
				AveTime [0][0]+= (end - start) / 1000000L;
				
				for (int j = 0; j < N;j++) {
					IList.add(rand.nextInt(Integer.MAX_VALUE));
				}
				start = System.nanoTime();
				SortingFunctions.BubbleSort(IList, 0,N);
				end = System.nanoTime();
				IList.clear();
				AveTime[3] [0]+= (end - start) / 1000000L;
				
				for (int j = 0; j < N; j++) {
					IList.add(rand.nextInt(Integer.MAX_VALUE));
				}
				start = System.nanoTime();
				SortingFunctions.MergeAndInsertionSort(IList, 0,N);
				end = System.nanoTime();
				IList.clear();
				AveTime[1][0] += (end - start) / 1000000L;
			}
			System.out.printf("%.3f ",AveTime[0][0]
					/ 10);
			System.out.printf(" %.3f ",AveTime[1][0]
					/ 10);
			System.out.printf(" %.3f ", AveTime[2][0]
					/ 10);
			System.out.printf(" %.3f\n" , AveTime[3][0]
					/ 10);
/*				start = System.nanoTime();
				SortingFunctions.InsertionSort(IList,0,N);
				end = System.nanoTime();
		
				start = System.nanoTime();
				SortingFunctions.MergeSort(IList, 0,N);
				end = System.nanoTime();
				
				start = System.nanoTime();
				SortingFunctions.BubbleSort(IList, 0,N);
				end = System.nanoTime();
				
				start = System.nanoTime();
				SortingFunctions.MergeAndInsertionSort(IList, 0,N);
				end = System.nanoTime();
				
				IList.clear();
				AveTime+=((end-start)/1000000);
			}
			System.out.printf("%.3f\n",AveTime/10);
*/		}
	}
}
