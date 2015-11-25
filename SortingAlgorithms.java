
public class SortingAlgorithms {
	
	// SelectSort
	static <E extends Comparable<? super E>>
	void selectSort(E[] A, SortingCounter count) {
		for (int i=0; i<A.length-1; i++) { // Select i’th record
			int lowindex = i; // Remember its index
			for (int j=A.length-1; j>i; j--){ // Find the least value
				count.c++;
				if (A[j].compareTo(A[lowindex]) < 0)
						lowindex = j; // Put it in place
			}
			count.s++;
			DSutil.swap(A, i, lowindex);
		}

	}
	
	static <E extends Comparable<? super E>>
	void insSort(E[] A, SortingCounter count) {
	  for (int i=1; i<A.length; i++){ // Insert i'th record
		int temp = i;
	    for (int j=i; (j>0) && (A[j].compareTo(A[j-1])<0); j--, temp--){
	      count.c++;	
	      DSutil.swap(A, j, j-1);
	      count.s++;
	    }
	    if (temp > 0) count.c++;
	  }
	}
	
	static <E extends Comparable<? super E>>
	void heapSort(E[] A, SortingCounter count) {// The heap constructor invokes the buildheap method
		MaxHeap<E> H = new MaxHeap<E>(A, A.length, A.length, count);
		for (int i=0; i<A.length; i++){ // Now sort
			H.removemax(count); // Removemax places max at end of heap
		}

	}
	
	static <E extends Comparable<? super E>>

	void mergeSort(E[] A, E[] temp, int l, int r, SortingCounter count) {
		int mid = (l+r)/2; // Select midpoint
		if (l == r) return; // List has one element
		mergeSort(A, temp, l, mid, count); // Mergesort first half
		mergeSort(A, temp, mid+1, r, count); // Mergesort second half
		for (int i=l; i<=r; i++){ // Copy subarray to temp
			temp[i] = A[i];
			count.s++;// TODO does the copy count as a swap?
		}
		// Do the merge operation back to A
		int i1 = l; int i2 = mid + 1;
		for (int curr=l; curr<=r; curr++) {
			if (i1 == mid+1){ // Left sublist exhausted
				A[curr] = temp[i2++];
				count.s++;
			}
			else if (i2 > r){ // Right sublist exhausted
				A[curr] = temp[i1++];
				count.s++;
			}
			else if (temp[i1].compareTo(temp[i2])<0){ // Get smaller
				A[curr] = temp[i1++];
				count.c++;
			}
			else A[curr] = temp[i2++];
			count.s++;
			count.c++;
		}
	}
	
	static <E extends Comparable<? super E>>
	void qSort(E[] A, int i, int j, SortingCounter count) { // Quicksort
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		DSutil.swap(A, pivotindex, j); // Stick pivot at end
		count.s++;
		// k will be the first position in the right subarray
		int k = partition(A, i-1, j, A[j], count);
		DSutil.swap(A, k, j); // Put pivot in place
		count.s++;
		if ((k-i) > 1) qSort(A, i, k-1, count); // Sort left partition
		if ((j-k) > 1) qSort(A, k+1, j, count); // Sort right partition
	}
	
	static <E extends Comparable<? super E>>
	int findpivot(E[] A, int i, int j)
	{ return (i+j)/2; }
	
	static <E extends Comparable<? super E>>
	int partition(E[] A, int l, int r, E pivot, SortingCounter count) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot)<0){count.c++;};
			count.c++;
			while ((r!=0) && (A[--r].compareTo(pivot)>0)){count.c++;};
			count.c++;
			DSutil.swap(A, l, r); // Swap out-of-place values
			count.s++;
		} while (l < r); // Stop when they cross
		DSutil.swap(A, l, r); // Reverse last, wasted swap
		count.s++;
		return l; // Return first position in right partition
	}
	

}
