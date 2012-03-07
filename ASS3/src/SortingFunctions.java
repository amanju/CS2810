/*A.Manjunath,CS10B001			Date:22/1/2012
 *This class consists of sorting algorithms namely BubbleSort,MergeSort,InsertionSort,MergeAndInsertionSort
 *Each of them can accept list of objects of type T(Generic data type, it can be Integers,Strings,Double etc) which are Comparable 
 */
import java.util.*;

public class SortingFunctions {
	public static <T extends Comparable<T>> void MergeSort(List<T> lst,
			int begin_index, int end_index) {
		int n = (end_index + begin_index) / 2;
		if (begin_index < (end_index - 1)) {
			MergeSort(lst, begin_index, n);
			MergeSort(lst, n, end_index);
			Merge(lst, begin_index, n, end_index);
		}
	}

	public static <T extends Comparable<T>> void Merge(List<T> lst,
			int begin_index, int mid_index, int end_index) {
		int k = begin_index, i = 0, j = 0;

		List<T> LeftList = new ArrayList<T>();
		List<T> RightList = new ArrayList<T>();

		for (i = 0; k < mid_index; k++, i++)
			LeftList.add(i, lst.get(k));
		LeftList.add(null);

		for (; k < end_index; k++, j++)
			RightList.add(j, lst.get(k));
		RightList.add(null);

		k = begin_index;
		i = 0;
		j = 0;
		while (k < end_index) {
			if (LeftList.get(i) != null) {
				if (RightList.get(j) != null) {
					if (LeftList.get(i).compareTo(RightList.get(j)) <= 0) {
						lst.set(k, LeftList.get(i));
						k++;
						i++;
					} else {
						lst.set(k, RightList.get(j));
						k++;
						j++;
					}
				} else {
					lst.set(k, LeftList.get(i));
					k++;
					i++;
				}
			} else {
				lst.set(k, RightList.get(j));
				k++;
				j++;
			}

		}
	}

	public static <T extends Comparable<T>> void InsertionSort(List<T> lst,
			int begin_index, int end_index) {
		int i, j;
		T key;
		for (j = (begin_index + 1); j < end_index; j++) {
			key = lst.get(j);
			i = j - 1;
			while (i >= 0 && lst.get(i).compareTo(key) > 0) {
				lst.set(i + 1, lst.get(i));
				i--;
			}
			lst.set(i + 1, key);
		}
		return;
	}

	public static <T extends Comparable<T>> void MergeAndInsertionSort(
			List<T> lst, int begin_index, int end_index) {
		int N = (begin_index + end_index) / 2;
		if ((end_index - begin_index) > 100) {
			MergeAndInsertionSort(lst, begin_index, N);
			MergeAndInsertionSort(lst, N, end_index);
			Merge(lst, begin_index, N, end_index);
		} else {
			InsertionSort(lst, begin_index, end_index);
		}
		return;
	}

	public static <T extends Comparable<T>> void BubbleSort(List<T> lst,
			int begin_index, int end_index) {
		int i, j;
		int N = end_index - begin_index;
		T temp;
		while (N > 0) {
			for (i = begin_index, j = i + 1; j < N; i++, j++) {
				if (lst.get(i).compareTo(lst.get(j)) > 0) {
					temp = lst.get(i);
					lst.set(i, lst.get(j));
					lst.set(j, temp);
				}
			}
			N--;
		}
	}

}
