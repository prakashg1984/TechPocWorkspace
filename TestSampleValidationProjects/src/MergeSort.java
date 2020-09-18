import java.util.ArrayList;
import java.util.Arrays;


public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] actual = { 5, 1, 6, 2, 3, 4 };
		mergeSort(actual, actual.length);
		System.out.println(new ArrayList<Integer>(Arrays.asList(actual)));
	}

	public static void mergeSort(Integer[] a, int n) {
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		Integer[] l = new Integer[mid];
		Integer[] r = new Integer[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);
	}
	
	public static void merge(Integer[] a, Integer[] l, Integer[] r, int left, int right) {

		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
	}

}
