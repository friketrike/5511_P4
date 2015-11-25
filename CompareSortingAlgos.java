import java.util.EnumMap;

public class CompareSortingAlgos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortTestDataSet dataSet = new SortTestDataSet();
		
		SortTestDataSet dataSetCopy = new SortTestDataSet(dataSet);
		
		EnumMap<dataSets, SortingCounter> selectSortCounter = new EnumMap<>(dataSets.class);
		for(dataSets set : dataSets.values())
			selectSortCounter.put(set,new SortingCounter());
		EnumMap<dataSets, SortingCounter> insSortCounter = new EnumMap<>(dataSets.class);
		for(dataSets set : dataSets.values())
			insSortCounter.put(set,new SortingCounter());
		EnumMap<dataSets, SortingCounter> heapSortCounter = new EnumMap<>(dataSets.class);
		for(dataSets set : dataSets.values())
			heapSortCounter.put(set,new SortingCounter());
		EnumMap<dataSets, SortingCounter> mergeSortCounter = new EnumMap<>(dataSets.class);
		for(dataSets set : dataSets.values())
			mergeSortCounter.put(set,new SortingCounter());
		EnumMap<dataSets, SortingCounter> qSortCounter = new EnumMap<>(dataSets.class);
		for(dataSets set : dataSets.values())
			qSortCounter.put(set,new SortingCounter());
		
		for(dataSets set : dataSets.values())
			SortingAlgorithms.selectSort(dataSetCopy.d.get(set), selectSortCounter.get(set));
		dataSetCopy.clone(dataSet);
		for(dataSets set : dataSets.values())
			SortingAlgorithms.insSort(dataSetCopy.d.get(set), insSortCounter.get(set));
		dataSetCopy.clone(dataSet);
		for(dataSets set : dataSets.values())
			SortingAlgorithms.heapSort(dataSetCopy.d.get(set), heapSortCounter.get(set));
		dataSetCopy.clone(dataSet);
		for(dataSets set : dataSets.values())
			SortingAlgorithms.qSort(dataSetCopy.d.get(set), 0, dataSetCopy.d.get(set).length-1, 
					qSortCounter.get(set));
		dataSetCopy.clone(dataSet);
		for(dataSets set : dataSets.values())
			SortingAlgorithms.mergeSort(dataSetCopy.d.get(set), dataSet.d.get(set), 
					0, dataSetCopy.d.get(set).length-1, mergeSortCounter.get(set));
		// nice formatting idea from 
		// http://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
		final Object[][] table = new String[6][];
		table[0] = new String[]{"", "A", "B", "C", "D", "E"};
		for(int i = 1; i < 6; i++)
			table[i] = new String[6];
		table[1][0] = "Insert";
		table[2][0] = "Select";
		table[3][0] = "Heap";
		table[4][0] = "Merge";
		table[5][0] = "Quick";
		int i = 0;
		for (dataSets set : dataSets.values())
		{
			++i;
			table[1][i] = insSortCounter.get(set).toString();
			table[2][i] = selectSortCounter.get(set).toString();
			table[3][i] = heapSortCounter.get(set).toString();
			table[4][i] = mergeSortCounter.get(set).toString();
			table[5][i] = qSortCounter.get(set).toString();
		}
		for (final Object[] row : table) {
		    System.out.format("%-7s%-11s%-15s%-19s%-19s%-19s\n", row);
		}
	}
	

}
