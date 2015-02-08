package org.swan.algorithm.LargestRectangleHistogramArea;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Largest Rectangle Histogram Area: Given n non-negative integers representing
 * the histogram's bar height where the width of each bar is 1, find the area of
 * largest rectangle in the histogram.
 * 
 * more precise definition: max area of CONTINUOUS rectangle in the histogram
 * 
 * @author SHENGHUA
 * 
 */
public class Problem {
	public static Logger logger = Logger
			.getLogger("org.swan.algorithm.LargestRectangleHistogramArea.Problem");

	public static void main(String[] args) {
		if (testNaiveSolution()) {
			logger.info("Naive Solution Test Cases : PASSED");
		} else {
			logger.info("Naive Solution Test Cases : FAILED");
		}
		
		if (testLinearTimeSolution()) {
			logger.info("Linear-Time Solution Test Cases : PASSED");
		} else {
			logger.info("Linear-Time Solution Test Cases : FAILED");
		}

	}

	public static int[][] createTestSuites() {
		// solution is in the end of the arrays.
		int[] testCase0 = { 1, 2, 3, 4, 5, 6, 12 };
		int[] testCase1 = { 2, 1, 6, 5, 3, 10 };
		int[] testCase2 = { 6, 2, 5, 4, 5, 1, 6, 12 };
		int[] testCase3 = { 0, 9 , 9 };
		int[][] testSuites = new int[][] { testCase0, testCase1, testCase2, testCase3 };
		return testSuites;
	}

	public static boolean testNaiveSolution() {
		int[][] testSuites = createTestSuites();
		logger.info("TestSuites:");
		for (int[] testCase : testSuites) {
			int sol = NaiveSolution.solve(testCase);
			logger.info("Test Case: " + Arrays.toString(Arrays.copyOfRange(testCase, 0, testCase.length -  1))
					+ ", Max Rectangle Histogram Area: " + sol 
					+ ", Suppose to be " + testCase[testCase.length - 1]);
			if (sol != testCase[testCase.length - 1]) {
				return false;
			}
			assert (sol == testCase[testCase.length - 1]);
		}
		return true;
	}
	
	public static boolean testLinearTimeSolution() {
		int[][] testSuites = createTestSuites();
		logger.info("TestSuites:");
		for (int[] testCase : testSuites) {
			int sol = LinearTimeSolution.solve(testCase);
			logger.info("Test Case: " + Arrays.toString(Arrays.copyOfRange(testCase, 0, testCase.length -  1))
					+ ", Max Rectangle Histogram Area: " + sol 
					+ ", Suppose to be " + testCase[testCase.length - 1]);
			if (sol != testCase[testCase.length - 1]) {
				return false;
			}
			assert (sol == testCase[testCase.length - 1]);
		}
		return true;
	}
}

class NaiveSolution {
	public static Logger logger = Logger
			.getLogger("org.swan.algorithm.LargestRectangleHistogramArea.NaiveSolution");

	/**
	 * Naive solution: 1. Given bounds o<=i<j<n, the max area of continuous
	 * rectangle is min{a_k}*(j-i+1) where i<=k<=j. 
	 * 2. The solution is find max area of continuous 
	 * rectangle for all possible bounds i,j.
	 * @param histogram
	 * @return
	 */
	public static int solve(int histogram[]) {
		int size = histogram.length - 1;
		int maxArea = 0;
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				int area = MaxContinuousRectangleArea(histogram, i, j);
				maxArea = (maxArea < area) ? area : maxArea;
			}
		}
		return maxArea;
	}

	public static int MaxContinuousRectangleArea(int[] histogram, int i, int j) {
		if (i > j) {
			return 0;
		}
		assert (i <= j);
		int[] selectedRange = Arrays.copyOfRange(histogram, i, j + 1);
		List<Integer> b = Arrays.asList(ArrayUtils.toObject(selectedRange));
		int area = Collections.min(b) * (j - i + 1);
		logger.info("Pair candidate: " + Arrays.toString(selectedRange) + "--> area: " + area);
		return area;
	}
}

class LinearTimeSolution {
	public static Logger logger = Logger
			.getLogger("org.swan.algorithm.LargestRectangleHistogramArea.LinearTimeSolution");
	
	/**
	 * 1. find the max rectangle area with histogram[i] as smallest bar
	 * 2. for all i, find the max of 1.
	 * Use a stack containing the indices to  track 1, 
	 * push stack if stack is empty or histogram[i] => top of the stack
	 * pop stack if otherwise
	 * @param histogram
	 * @return
	 */
	public static int solve(int[] histogram) {
		int size = histogram.length - 1;
		Stack<Integer> s = new Stack<>();
		int i = 0;
		int maxArea = 0;
		int[] h = Arrays.copyOf(histogram, size + 1); //making last element to be 0, so that stack can be emptied.
		h[size] = 0;
		while (i < h.length) {
			if(s.empty() || h[s.peek()] <= h[i]){
				s.push(i);
				i++;
			}else{
				int tp = s.pop();
				int width = (s.empty()) ? i : (i - s.peek() + 1 - 2); //exclude elements of i, s.peek()
				int max_area_with_top_as_smallest_bar = h[tp] * width;
				logger.info("area: " +  max_area_with_top_as_smallest_bar);
				maxArea = Math.max(maxArea, max_area_with_top_as_smallest_bar);
			}
		}
		
		return maxArea;
	}
}
