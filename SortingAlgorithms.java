/* COMP 5511 fall 2015 assignment 4 Programming part
 * Federico O'Reilly Regueiro Student 	ID 40012304
 * Collection of slightly modified sorting methods from :
 * "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer */

import java.util.Stack;

public class SortingAlgorithms {
	
	static <E extends Comparable<? super E>>
	void insSort(E[] A, SortingCounter count) {
	  for (int i=1; i<A.length; i++){ // Insert i'th record
		int temp = i;
	    for (int j=i; (j>0) && (A[j].compareTo(A[j-1])<0); j--, temp--){
	      count.c++;	
	      DSutil.swap(A, j, j-1);
	      count.s++;
	    }
	    // make sure we didn't break the loop because of j, 
	    // if so, we compared keys without entering the loop
	    if (temp > 0) count.c++;
	  }
	}
	
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
			/*count.s++; -- copying the data does not count as swap*/
		}
		// Do the merge operation back to A
		int i1 = l; int i2 = mid + 1;
		for (int curr=l; curr<=r; curr++) {
			count.s++; // at some point we'll inevitably assign in the block
			if (i1 == mid+1){ // Left sublist exhausted
				A[curr] = temp[i2++];
			}
			else if (i2 > r){ // Right sublist exhausted
				A[curr] = temp[i1++];
			}
			else if (temp[i1].compareTo(temp[i2])<0){ // Get smaller
				A[curr] = temp[i1++];
				count.c++;
			}
			else{
				A[curr] = temp[i2++];
				count.c++; // we compared in the previous block without entering
			}
		}
	}
	
	static <E extends Comparable<? super E>>
	void qSort(E[] A, SortingCounter count) { // Quicksort
		// I'm using a very old machine and openJDK, recursive method gets overflow 
		// with large enough datasets I moded it to turn it into non-recursive
		Stack<Integer> indexes = new Stack<>();  
		indexes.push(0);
		indexes.push(A.length-1);
		while (!indexes.isEmpty()){
			int j = indexes.pop();
			int i = indexes.pop();
			int pivot = (i+j)/2;
			DSutil.swap(A, pivot, j); // Stick pivot at end, pivot is first element
			count.s++;
			int k = partition(A, i-1, j, A[j], count);
			DSutil.swap(A, k, j); // Put pivot in place
			count.s++;
			if ((k-i) > 1){
				indexes.push(i);
				indexes.push(k-1);
			}
			if ((j-k) > 1){
				indexes.push(k+1);
				indexes.push(j);
			}	
		}
	}
	
	static <E extends Comparable<? super E>>
	int partition(E[] A, int l, int r, E pivot, SortingCounter count) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot)<0) count.c++;
			count.c++; // include the compare that broke the loop
			while ((r!=0) && (A[--r].compareTo(pivot)>0)) count.c++;
			if (r!=0) count.c++; // again an extra compare if first cond == true
			DSutil.swap(A, l, r); // Swap out-of-place values
			count.s++;
		} while (l < r); // Stop when they cross
		DSutil.swap(A, l, r); // Reverse last, wasted swap
		count.s++;
		return l; // Return first position in right partition
	}
}
