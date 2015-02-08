package org.swan.algorithm.MaxRectangle;

import java.util.Arrays;
import java.util.Stack;

public class Problem {
	public static void main(String[] args) {
		char[][] matrix = new char[1][1];
		matrix[0][0] = '1';
		System.out.println(QuadraticTimeSolution.maximalRectangle(matrix));
	}
}

//O(n^4)
//Time Limit Exceeded
class Solution {
	public static int maxRectangle(char[][] matrix) {
		int maxArea = Integer.MIN_VALUE;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				int maxAreaHere = maxRectangleAt(matrix, i, j);
				if(maxAreaHere > maxArea) {
					maxArea = maxAreaHere;
				}
			}
		}
		return maxArea;
	}
	
	public static int maxRectangleAt(char[][] matrix, int row, int col){
		if(matrix[row][col] == '0') {
			return 0;
		}
		int minWidth = Integer.MAX_VALUE;
		int maxAreaAtHere = Integer.MIN_VALUE;
		for(int i = row; i < matrix.length; i++) {
			int width = 0;
			while (width < matrix[i].length && matrix[i][col + width] == '1') {
				width++;
			}
			if(minWidth > width){
				minWidth = width;
			}
			int area = width * (i - row + 1);
			if(area > maxAreaAtHere){
				maxAreaAtHere = area;
			}
		}
		
		return maxAreaAtHere;
	}
}

class QuadraticTimeSolution {
	public static int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0) { return 0;}
		//construct histograms in for each row
		int[][] hist = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == '0') {
					hist[i][j] = 0;
				}else{
					hist[i][j] = (i == 0) ? 1 : hist[i - 1][j] + 1;
				}
			}
		}
		//compute the max rectangle in the histogram in each row
		int maxArea = Integer.MIN_VALUE;
		for(int i = 0; i < matrix.length; i++) {
			int maxHistogramAreaAtRowI = maxHistogramArea(hist[i]);
			if(maxArea < maxHistogramAreaAtRowI) {
				maxArea = maxHistogramAreaAtRowI;
			}
		}
		
		return maxArea;
	}
	
	public static int maxHistogramArea(int[] histogram) {
		int size = histogram.length;
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
				maxArea = Math.max(maxArea, max_area_with_top_as_smallest_bar);
			}
		}
		
		return maxArea;
	}
}
