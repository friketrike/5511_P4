public class CompareSortingAlgos extends DataSetConstants{

	/**
	 * @param args
	 */
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
			SortingAlgorithms.qSort(dataSetCopy.d[i], 0, dataSetCopy.d[i].length-1, qSortCounters[i]);
		}
		dataSetCopy.copy(dataSet);
		for(int i = 0; i < NUMSETS; i++){
			SortingAlgorithms.mergeSort(dataSetCopy.d[i], dataSet.d[i], 0, 
					dataSetCopy.d[i].length-1, mergeSortCounters[i]);
		}
		
		// nice formatting idea from 
		// http://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
		final Object[][] table = new String[6][];
		table[0] = new String[]{"", "A", "B", "C", "D", "E"};
		for(int i = 1; i <= NUMSORTS; i++)
			table[i] = new String[6];
		table[1][0] = "Insert";
		table[2][0] = "Select";
		table[3][0] = "Heap";
		table[4][0] = "Merge";
		table[5][0] = "Quick";
		
		for(int i = 0; i < NUMSETS; i++)
		{
			table[1][i+1] = insSortCounters[i].toString();
			table[2][i+1] = selectSortCounters[i].toString();
			table[3][i+1] = heapSortCounters[i].toString();
			table[4][i+1] = mergeSortCounters[i].toString();
			table[5][i+1] = qSortCounters[i].toString();
		}
		
		for (final Object[] row : table) {
		    System.out.format("%-7s%-11s%-15s%-19s%-19s%-19s\n", row);
		}
	}
	

}
