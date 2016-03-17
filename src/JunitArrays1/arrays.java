package JunitArrays1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Object;
import org.apache.commons.lang3.ArrayUtils;

import org.junit.BeforeClass;
import org.junit.Test;


public class arrays {
	private static int arrayNum=100; /*This is a global that sets the number of arrays we are testing*/
	private static ArrayList<int[]> allArrays=new ArrayList<int[]> ();  /*Create an ArrayList to hold our arrays*/

	/*Set up the Arrays Once*/
	@BeforeClass
	public static void createArrays() {
		Random rand = new Random(); /*Initialize a Random Number Generator*/
		/*Create the arrays*/
		for (int i=0;i<arrayNum;i++) {
			int size=rand.nextInt(Integer.MAX_VALUE/100000); /*Arrays can be of any size that is a positive int, but this causes a memory out of bounds exception, so instead we divide by 500*/
			//int size=rand.nextInt(10);
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
			Arrays.sort(currentArray); /*Sort one of the arrays*/
			/*Verify that the sorted and unsorted elements have the same array*/
			for (int j=0;j<currentArray.length;j++) {
				/*if the element is in both arrays*/
				if( ArrayUtils.contains(currentArrayCopy,currentArray[j]) ) {
					currentArrayCopy=ArrayUtils.removeElement(currentArrayCopy, currentArray[j]); /*remove the element from one array*/
				}
			}
			assertEquals(currentArrayCopy.length,0); /*all of the elements should have been found and removed, so the length of the array should be zero*/
		}
	}

	/*Verify that the Array is actually sorted*/
	@Test
	public void testActuallySorted() {
		for (int i=0;i<arrayNum;i++){
			int[] currentArray=allArrays.get(i); /*Get the current array*/
			Arrays.sort(currentArray); /*Sort the Array*/
			/*Make sure that each element in the array is bigger than the previous element*/
			for (int j=1;j<currentArray.length;j++) {
				assertTrue(currentArray[j]>=currentArray[j-1]); 
			}
		}

	}

}
