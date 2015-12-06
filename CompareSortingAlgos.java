/* COMP 5511 fall 2015 assignment 4 Programming part
 * Federico O'Reilly Regueiro Student 	ID 40012304
 * Driver for comparing sorting algorithms counts and
 * swaps*/
public class CompareSortingAlgos extends DataSetConstants{

	public static void main(String[] args) {
		
		final int NUMSORTS = 5;
		
		SortTestDataSet dataSet = new SortTestDataSet();
		
		SortTestDataSet dataSetCopy = new SortTestDataSet(dataSet);
		
		SortingCounter[] insSortCounters = new SortingCounter[NUMSETS];
		SortingCounter[] selectSortCounters = new SortingCounter[NUMSETS];
		SortingCounter[] heapSortCounters = new SortingCounter[NUMSETS];
		SortingCounter[] mergeSortCounters = new SortingCounter[NUMSETS];
		SortingCounter[] qSortCounters = new SortingCounter[NUMSETS];
		
		for(int i = 0; i < NUMSETS; i++)
		{
			insSortCounters[i] = new SortingCounter();
			selectSortCounters[i] =new SortingCounter();
			heapSortCounters[i] =new SortingCounter();
			mergeSortCounters[i] =new SortingCounter();
			qSortCounters[i] =new SortingCounter();
		}
		
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.insSort(dataSetCopy.d[i], insSortCounters[i]);
		}
		dataSetCopy.copy(dataSet);
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.selectSort(dataSetCopy.d[i], selectSortCounters[i]);
		}
		dataSetCopy.copy(dataSet);
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.heapSort(dataSetCopy.d[i], heapSortCounters[i]);
		}
		dataSetCopy.copy(dataSet);
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.qSort(dataSetCopy.d[i], qSortCounters[i]);
		}
		dataSetCopy.copy(dataSet);
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.mergeSort(dataSetCopy.d[i], dataSet.d[i], 0, 
					dataSetCopy.d[i].length-1, mergeSortCounters[i]);
			mergeSortCounters[i].s /= 3;// for merge, suppose 3 assigns = 1 swap
		}
		
		// nice formatting idea from 
		// http://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
		final Object[][] table = new String[12][];
		table[0] = new String[]{"", "A", "B", "C", "D", "E"};
		table[1] = new String[]{"", "c/s", "c/s", "c/s", "c/s", "c/s"};
		for(int i = 2; i < 2*NUMSORTS+2; i++)
			table[i] = new String[6];
		table[2][0] = "Ins";
		table[3][0] = "";
		table[4][0] = "Sel";
		table[5][0] = "";
		table[6][0] = "Heap";
		table[7][0] = "";
		table[8][0] = "Mrge";
		table[9][0] = "";
		table[10][0] = "Quik";
		table[11][0] = "";
		
		for(int i = 0; i < NUMSETS; i++)
		{
			table[2][i+1] = insSortCounters[i].c.toString();
			table[3][i+1] = insSortCounters[i].s.toString();
			table[4][i+1] = selectSortCounters[i].c.toString();
			table[5][i+1] = selectSortCounters[i].s.toString();
			table[6][i+1] = heapSortCounters[i].c.toString();
			table[7][i+1] = heapSortCounters[i].s.toString();
			table[8][i+1] = mergeSortCounters[i].c.toString();
			table[9][i+1] = mergeSortCounters[i].s.toString();
			table[10][i+1] = qSortCounters[i].c.toString();
			table[11][i+1] = qSortCounters[i].s.toString();
		}
		
		for (final Object[] row : table) {
		    System.out.format("%-8s%-8s%-10s%-12s%-12s%-12s\n", row);
		}
	}
	

}
