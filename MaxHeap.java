/* COMP 5511 fall 2015 assignment 4 Programming part
 * Federico O'Reilly Regueiro Student 	ID 40012304
 * Modified version of:
 * Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.lang.Comparable;

/** Max-heap implementation */
public class MaxHeap<E extends Comparable<? super E>> {
	private E[] Heap;   // Pointer to the heap array
	private int size;   // Maximum size of the heap
	private int n;      // Number of things in heap

	/** Constructor supporting preloading of heap contents */
	public MaxHeap(E[] h, int num, int max, SortingCounter count)
	{ Heap = h;  n = num;  size = max;  buildheap(count); }
	
	/** @return Current size of the heap */
	public int heapsize() { return n; }
	
	/** @return True if pos a leaf position, false otherwise */
	public boolean isLeaf(int pos)
	{ return (pos >= n/2) && (pos < n); }
	
	/** @return Position for left child of pos */
	public int leftchild(int pos) {
	  assert pos < n/2 : "Position has no left child";
	  return 2*pos + 1;
	}
	
	/** @return Position for right child of pos */
	public int rightchild(int pos) {
	  assert pos < (n-1)/2 : "Position has no right child";
	  return 2*pos + 2;
	}
	
	/** @return Position for parent */
	public int parent(int pos) {
	  assert pos > 0 : "Position has no parent";
	  return (pos-1)/2;
	}
	
	/** Insert val into heap */
	public void insert(E val, SortingCounter count) {
	  assert n < size : "Heap is full";
	  int curr = n++;
	  Heap[curr] = val;             // Start at end of heap
	  // Now sift up until curr's parent's key > curr's key
	  while ((curr != 0)  &&
	         (Heap[curr].compareTo(Heap[parent(curr)]) > 0)) {
		count.c++;
	    DSutil.swap(Heap, curr, parent(curr));
	    count.s++;
	    curr = parent(curr);
	  }
	if (curr != 0) count.c++;
	}
	/** Heapify contents of Heap */
	public void buildheap(SortingCounter count)
	  { for (int i=n/2-1; i>=0; i--) siftdown(i, count); }
	
	/** Put element in its correct place */
	private void siftdown(int pos, SortingCounter count) {
	  assert (pos >= 0) && (pos < n) : "Illegal heap position";
	  while (!isLeaf(pos)) {
	    int j = leftchild(pos);
	    if (j<(n-1)) count.c++;
	    if ((j<(n-1)) && (Heap[j].compareTo(Heap[j+1]) < 0)) 
	      j++; // j is now index of child with greater value
	    count.c++;
	    if (Heap[pos].compareTo(Heap[j]) >= 0) return;
	    DSutil.swap(Heap, pos, j);
	    if ( pos != j) count.s++;
	    pos = j;  // Move down
	  }
	}
	
	/** Remove and return maximum value */
	public E removemax(SortingCounter count) {
	  assert n > 0 : "Removing from empty heap";
	  DSutil.swap(Heap, 0, --n); // Swap maximum with last value
	  count.s++;
	  if (n != 0)      // Not on last element
	    siftdown(0, count);   // Put new heap root val in correct place
	  return Heap[n];
	}
	
	/** Remove and return element at specified position */
	public E remove(int pos, SortingCounter count) {
	  assert (pos >= 0) && (pos < n) : "Illegal heap position";
	  if (pos == (n-1)) n--; // Last element, no work to be done
	  else
	  {
	    DSutil.swap(Heap, pos, --n); // Swap with last value
	    // If we just swapped in a big value, push it up
	    count.s++;
	    while ((pos > 0) &&
	           (Heap[pos].compareTo(Heap[parent(pos)]) > 0)) {
	      count.c++;
	      DSutil.swap(Heap, pos, parent(pos));
	      count.s++;
	      pos = parent(pos);
	    }
	    if (pos > 0) count.c++;
	    if (n != 0) siftdown(pos, count); // If it is little, push down
	  }
	  return Heap[n];
	}
}
