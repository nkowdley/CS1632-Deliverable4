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
	/* arrayNumis a global that sets the number of arrays we are testing.
	This number MUST be greater than 8, as there are 8 edge cases that are
	always generated */
	private static int arrayNum=100;
	/*divide is a global that divides the maximum int value (Integer.MAX_VALUE)
	This value changes the maximum size of any array generated.  This has
	a significant impact on performance */
	private static int divide=100000;
	private static ArrayList<int[]> allArrays=new ArrayList<int[]> ();  /*Create an ArrayList to hold our arrays*/

	/*Set up the Arrays Once*/
	@BeforeClass
	public static void createArrays() {
		if(arrayNum<8){ /*if arrayNum is <10, stop the program*/
			fail("You need to set arrayNum to a number bigger than 8!"); /*fail the test with an error message*/
			System.exit(-1); /*exit with an error code of -1*/
		}
		addEdgeCases();
		/*Create the arrays*/
		for (int i=0;i<arrayNum-8;i++) {
			int size=rand.nextInt(Integer.MAX_VALUE/divide); /*Arrays can be of any size that is a positive int, but this causes a memory out of bounds exception, so instead we divide by 500*/
			//int size=rand.nextInt(10);
			int[] currentArray= new int[size]; /*Create the Array*/
			/*fill the array*/
			for(int j=0;j<size;j++) {
				currentArray[j]=rand.nextInt(); /*We can add any number that is an int to the array*/
			}
			allArrays.add(currentArray); /*Add the arrays to the ArrayList*/
		}
	}

/* A wrapper to print Arrays*/
	public void printArray(int[] array) {
		for (int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}

	}
	/*This function generates the edge case arrays, and adds them to the arrayList */
	public void addEdgeCases(){
		Random rand = new Random(); /*Initialize a Random Number Generator*/

		/*Edge Case 1, array of 0 objects*/
		int[] edgeArray1= new int[0];

		/*Edge Case 2, array of 1 object*/
		int[] edgeArray2=new int[1];
		edgeArray[0]=rand.nextInt(); /*add a random int to the array*/

		/*Edge Case 3, array of all negative items */
		int size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray3 = new int[size]; /*Create an array with the random size*/
		for (int i=0;i<size;i++) {
			edgeArray3[i]=rand.nextInt(Integer.MAX_VALUE)*(-1); /*add a negative number to the array*/
		}

		/*Edge Case 4, array of arbitrary length with the same number*/
		size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray4 = new int[size]; /*Create an array with the random size*/
		int sameNumber=rand.nextInt(Integer.MAX_VALUE); /*Generate the number that will be added to the array*/
		for (int i=0;i<size;i++) {
			edgeArray4[i]=sameNumber;
		}

		/*Edge Case 5, array of already sorted elements*/
		size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray5 = new int[size]; /*Create an array with the random size*/
		/*Create a random number, and make sure that it is atleast 4000* (size+1) smaller
		than the Integer.MAX_VALUE to ensure that we can add
		random numbers from 0 to 4000 without reaching Integer.MAX_VALUE */
		int startNumber=rand.nextInt(Integer.MAX_VALUE)-(4000)*(size+1);
		for (int i=0;i<size;i++) {
			startNumber+=rand.nextInt(4000); /*Add some random value less than or equal to 4000 to the startNumber*/
			edgeArray5[i]=startNumber; /*add the increased number to the array*/
		}

		/*Edge Case 6, array of all Integer.MAX_VALUE s */
		size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray6 = new int[size]; /*Create an array with the random size*/
		Arrays.fill(edgeArray6, Integer.MAX_VALUE); /*fill the array with Integer.MAX_VALUE*/

		/*Edge Case 7, array of all 0's*/
		size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray7 = new int[size]; /*Create an array with the random size*/
		Arrays.fill(edgeArray7, 0); /*fill the array with 0s*/

		/*Edge Case 8, reverse sorted array*/
		size=rand.nextInt(Integer.MAX_VALUE/divide); /*Generate a size for the array*/
		int[] edgeArray8 = new int[size]; /*Create an array with the random size*/
		/*Create a random number, and make sure that it is atleast 4000* (size+1) to ensure that we can subtract
		random numbers from 0 to 4000 without reaching Integer.MAX_VALUE */
		int startNumber=rand.nextInt(Integer.MAX_VALUE)+(4000)*(size+1);
		for (int i=0;i<size;i++) {
			startNumber-=rand.nextInt(4000); /*Add some random value less than or equal to 4000 to the startNumber*/
			edgeArray8[i]=startNumber; /*add the increased number to the array*/
		}
		printArray(edgeArray1);
		printArray(edgeArray2);
		printArray(edgeArray3);
		printArray(edgeArray4);
		printArray(edgeArray5);
		printArray(edgeArray6);
		printArray(edgeArray7);
		printArray(edgeArray8);

		/*Add all the arrays to the array list*/
		allArrays.add(edgeArray1);
		allArrays.add(edgeArray2);
		allArrays.add(edgeArray3);
		allArrays.add(edgeArray4);
		allArrays.add(edgeArray5);
		allArrays.add(edgeArray6);
		allArrays.add(edgeArray7);
		allArrays.add(edgeArray8);
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
