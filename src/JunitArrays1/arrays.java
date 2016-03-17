package JunitArrays1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;


public class arrays {
	private static int arrayNum=100; /*This is a global that sets the number of arrays we are testing*/
	private static ArrayList<int[]> allArrays=new ArrayList<int[]> ();  /*Create an ArrayList to hold our arrays*/

	@BeforeClass
	public static void createArrays() {
		Random rand = new Random(); /*Initialize a Random Number Generator*/
		/*Create the arrays*/
		for (int i=0;i<arrayNum;i++) {
			int size=rand.nextInt(Integer.MAX_VALUE/500); /*Arrays can be of any size that is a positive int, but this causes a memory out of bounds exception, so instead we divide by 500*/
			int[] currentArray= new int[size]; /*Create the Array*/
			/*fill the array*/
			for(int j=0;j<size;j++) {
				currentArray[j]=rand.nextInt(); /*We can add any number that is an int to the array*/
			}
			allArrays.add(currentArray); /*Add the arrays to the ArrayList*/
		}
	}

	/*Verify that the Array does not change size when it gets sorted*/
	@Test
	public void testCorrectSize() {
		/*Loop through our list of arrays and test them each*/
		for (int i=0;i<arrayNum;i++){
			int[] currentArray=allArrays.get(i); /*Get the current array*/
			int origLength=currentArray.length; /*Save the pre-sorted length*/
			Arrays.sort(currentArray); /*Sort the array*/
			int newLength=currentArray.length; /*Get the new length*/
			assertEquals(origLength,newLength); /*Assert that the sizes are equal*/
		}
	}

	/*Verify that if any array is sorted twice, the array remains the same */
	@Test
	public void testVerifySameSort() {
		/*Loop through our list of arrays and test them each*/
		for (int i=0;i<arrayNum;i++){
			int[] currentArray=allArrays.get(i); /*Get the current array*/
			int[] currentArrayCopy=currentArray; /*Make a copy of the current array*/
			/*Sort both arrays*/
			Arrays.sort(currentArray);
			Arrays.sort(currentArrayCopy);
			assertArrayEquals(currentArray,currentArrayCopy); /*Assert the Arrays are equal*/
		}
	}
	/*Verify that the unsorted and sorted arrays both contain the same elements*/
	@Test
	public void testSameElements() {
		for (int i=0;i<arrayNum;i++){
			int[] currentArray=allArrays.get(i); /*Get the current array*/
			int[] currentArrayCopy=currentArray; /*copy the current array so that we can compare the sorted to the unsorted*/
			Arrays.sort(currentArray);
			//for (int j=0;j<currentArray.length;j++)
			//{
				//if
			//}

		}
	}
	/*Verify that the Array is actually sorted*/
	@Test
	public void testActuallySorted() {
		fail("Not implemented");
	}

}
