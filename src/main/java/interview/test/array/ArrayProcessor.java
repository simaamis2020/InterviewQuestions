package interview.test.array;

import java.util.Arrays;

/**
 * Class contains method to process array of intetegers
 * 
 * @author annasimonenko
 *
 */
public class ArrayProcessor {

	public static void main(String[] args) {
		ArrayProcessor processor = new ArrayProcessor();
		int[] array = { 0, 4, 3, 2, 7, 6, 5, 8, 9, 1, 10, 20, -4, -3, -7 };
		int expectedSum = 7;
		processor.printDistinctIntPairs(array, expectedSum);
	}

	/**
	 * method that prints pairs that equal given sum. It sorts the array first and
	 * then starts moving through all numbers left-right and right-left to find the pairs.
	 * numbers can be positive or negative. printed values are distinct.
	 * 
	 * @param numberArray
	 * @param expectedSum
	 */
	public void printDistinctIntPairs(int numberArray[], int expectedSum) {
		Arrays.sort(numberArray);

		int start = 0;
		int end = numberArray.length - 1;
		while (start < end) {
			int sum = numberArray[start] + numberArray[end];

			if (sum == expectedSum) {
				System.out.println("(" + numberArray[start] + "," + numberArray[end] + ")");

				start++;
				end--;

			} else if (sum < expectedSum) {
				start++;

			} else if (sum > expectedSum) {
				end--;
			}
		}
	}

}
