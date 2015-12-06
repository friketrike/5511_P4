/* COMP 5511 fall 2015 assignment 4 Programming part
 * Federico O'Reilly Regueiro Student 	ID 40012304
 * Generates the specified data set for the assignment
 * and also has a copy-constructor.
 */
import java.util.Random;

public class SortTestDataSet extends DataSetConstants{
	
	public Integer[][] d = new Integer[NUMSETS][];
	
	public SortTestDataSet(){
		allocateArrays();
		Random randomGen = new Random();
		for(int i=0; i<ASIZE; i++)
			d[A][i] = randomGen.nextInt();
		for(int i=0; i<BSIZE; i++)
			d[B][i] = randomGen.nextInt();
		for(int i=0; i<CSIZE; i++)
			d[C][i] = randomGen.nextInt();
		initDE();
	}
	
	/* Useful for copying a set with the same random values
	as opposed to using the same seed to call random several times*/
	public SortTestDataSet(SortTestDataSet toBeCopied){
		allocateArrays();
		copy(toBeCopied);
	}
	
	private void allocateArrays(){
		d[A] = new Integer[ASIZE];	
		d[B] = new Integer[BSIZE];	
		d[C] = new Integer[CSIZE];	
		d[D] = new Integer[DSIZE];	
		d[E] = new Integer[ESIZE];	
	}
	
	public void copy(SortTestDataSet toBeCopied)
	{
		for(int i=0; i<ASIZE; i++)
			d[A][i] = toBeCopied.d[A][i];
		for(int i=0; i<BSIZE; i++)
			d[B][i] = toBeCopied.d[B][i];
		for(int i=0; i<CSIZE; i++)
			d[C][i] = toBeCopied.d[C][i];
		initDE();
	}
	
	private void initDE(){
		/* these two arrays will always be the same, 
		set them up on allocation*/
		for(int i=0; i < DSIZE; i++)
			d[D][i] = (DSIZE - i);
			//d[D][i] = (i); // For debugging purposes...
		for(int i=0; i < ESIZE/2; i++)
		{
			d[E][i] = 2*(i) + 1;
			d[E][ESIZE-(i+1)] = ESIZE-(2*i);
		}
	}

}
