import java.util.Random;
import java.util.EnumMap;

enum dataSets{ A, B, C, D, E }

public class SortTestDataSet {
		
	public EnumMap<dataSets, Integer[]> d = new EnumMap<>(dataSets.class);
	
	private final int ASIZE = 100;		// 100 random numbers
	private final int BSIZE = 1000;		// 1000 random numbers
	private final int CSIZE = 10000;	// 10000 random numbers
	private final int DSIZE = 10000;	// 10000 to 1 in desc order
	private final int ESIZE = 10000;	// 1:9999 ; 2:10000 both step 2
	
	public SortTestDataSet(){
		allocateArrays();
		Random randomGen = new Random();
		for(int i=0; i<ASIZE; i++)
			d.get(dataSets.A)[i] = randomGen.nextInt();
		for(int i=0; i<BSIZE; i++)
			d.get(dataSets.B)[i] = randomGen.nextInt();
		for(int i=0; i<CSIZE; i++)
			d.get(dataSets.C)[i] = randomGen.nextInt();
		resetDE();
	}
	
	/* Useful for copying a set with the same random values
	as opposed to using the same seed to call random several times*/
	public SortTestDataSet(SortTestDataSet toBeCopied){
		allocateArrays();
		clone(toBeCopied);
	}
	
	private void allocateArrays(){
		d.put(dataSets.A, new Integer[ASIZE]);	
		d.put(dataSets.B, new Integer[BSIZE]);	
		d.put(dataSets.C, new Integer[CSIZE]);	
		d.put(dataSets.D, new Integer[DSIZE]);	
		d.put(dataSets.E, new Integer[ESIZE]);	
	}
	
	public void clone(SortTestDataSet toBeCopied)
	{
		for(int i=0; i<ASIZE; i++)
			d.get(dataSets.A)[i] = toBeCopied.d.get(dataSets.A)[i];
		for(int i=0; i<BSIZE; i++)
			d.get(dataSets.B)[i] = toBeCopied.d.get(dataSets.B)[i];
		for(int i=0; i<CSIZE; i++)
			d.get(dataSets.C)[i] = toBeCopied.d.get(dataSets.C)[i];
		resetDE();
	}
	
	private void resetDE(){
		/* these two arrays will always be the same, 
		set them up on allocation*/
		for(int i=0; i < DSIZE; i++)
			d.get(dataSets.D)[i] = (DSIZE - i);
		
		for(int i=0; i < ESIZE/2; i++)
		{
			d.get(dataSets.E)[i] = 2*(i) + 1;
			d.get(dataSets.E)[ESIZE-(i+1)] = 2*(i) + 2;
		}
	}

}
