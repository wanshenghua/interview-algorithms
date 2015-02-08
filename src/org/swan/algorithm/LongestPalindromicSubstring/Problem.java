package org.swan.algorithm.LongestPalindromicSubstring;

import org.swan.common.Test.TestCase;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Given a string S, find the longest palindromic substring in S.
 *  You may assume that the maximum length of S is 1000, and 
 *  there exists one unique longest palindromic substring.
 * @author SHENGHUA
 *
 */
public class Problem {

	public static Logger logger = Logger.getLogger("org.swan.algorithm.common.LongestPalindromicSubstring.Problem");
	
	public static void main(String[] arg){
		logger.info("creating test suites");
	
		TestSuites testSuites = new TestSuites();
		
		NaiveSolution naiveSolution = new NaiveSolution();		
		logger.info("start to test naive solution");		
		if (TestRunner.testSolution(naiveSolution, testSuites)) {
			logger.info("Naive Solution Test Cases : PASSED");
		} else {
			logger.info("Naive Solution Test Cases : FAILED");
		}
		
		QuadraticTimeSolution quadraticTimeSolution = new QuadraticTimeSolution();
		if (TestRunner.testSolution(quadraticTimeSolution, testSuites)) {
			logger.info("Quadratic Time Solution Test Cases : PASSED");
		} else {
			logger.info("Quadratic Time Solution Test Cases : FAILED");
		}
		
		LinearTimeSolution linearTimeSolution = new LinearTimeSolution();
		if (TestRunner.testSolution(linearTimeSolution, testSuites)) {
			logger.info("Linear Time Solution Test Cases : PASSED");
		} else {
			logger.info("Linear Time Solution Test Cases : FAILED");
		}
	}
}

class TestSuites {
	private ArrayList<TestCase<String, String>> testSuites = new ArrayList<>();	
	
	public TestSuites(){
		TestCase<String, String> c0 = new TestCase<>("", "");
		testSuites.add(c0);
		
		TestCase<String, String> c1 = new TestCase<>("a", "a");
		testSuites.add(c1);
		
		TestCase<String, String> c2 = new TestCase<>("ab", "a");
		testSuites.add(c2);
		
		TestCase<String, String> c3 = new TestCase<>("aba", "aba");
		testSuites.add(c3);

		TestCase<String, String> c4 = new TestCase<>("aaaa", "aaaa");
		testSuites.add(c4);		
		
		TestCase<String, String> c5 = new TestCase<>("cabcbabcbabcba", "abcbabcbabcba");
		testSuites.add(c5);	
		
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String t = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		TestCase<String, String> c6 = new TestCase<>(s, t);
		testSuites.add(c6);
	}
	
	public ArrayList<TestCase<String, String>> getTestSuites() {
		return testSuites;
	}
}

class TestRunner {
	public static Logger logger = Logger.getLogger("org.swan.algorithm.common.TestRunner");
	
	public static boolean testSolution(Solution sol, TestSuites testSuites){
		for(TestCase<String, String> testCase : testSuites.getTestSuites()){
			long startTime = System.nanoTime();
			String output = sol.solve(testCase.getInput());
			if(!output.equals(testCase.getAnswer())){
				logger.warning(sol.getClass().toString() + "solution FAILED with test case input: " + testCase.getInput() + 
						" ouput: " + output +
						" while answer should be " + testCase.getAnswer());
				return false;
			}
			long endTime = System.nanoTime();
			logger.info(sol.getClass().toString() + "solution PASSED with test case input: " + 
							testCase.getInput() + 
							" ouput: " + testCase.getAnswer() +
							" in " + (endTime - startTime) / 1e6 + " ms");
		}
		return true;
	}
}

interface Solution {
	String solve(String s);
}

class NaiveSolution implements Solution {
	/**
	 * try all possible substring(i, j) to see whether they are 
	 * palindromes or not. O(n^3)
	 * @param s
	 * @return longest palindromic substring
	 */
	public String solve(String s){
		String res = "";
		for (int i = 0; i < s.length() ; i++){
			for (int j = i + 1; j <= s.length(); j++){
				if( j - i > res.length() && isPalindrome(s, i, j)){
					res = s.substring(i, j);
				}
			}
		}
		return res;
	}
	
	public static boolean isPalindrome(String s, int beg, int end){
		int i = beg;
		int j = end - 1;
		while(i < j){
			if(s.charAt(i) != s.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}

class QuadraticTimeSolution implements Solution {
	/**
	 * try all possible (2n-1) centers for palindromic substring. O(n^2)
	 * @param s
	 * @return longest palindromic substring
	 */
	@Override
	public String solve(String s){
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			String centeredAtChar = getLongestPalindromeCenteredAt(s, i, i);
			if(res.length() < centeredAtChar.length()){
				res = centeredAtChar;
			}
			
			if(i + 1 < s.length()) {
				String centeredAtSpace = getLongestPalindromeCenteredAt(s, i, i + 1);
				if(res.length() < centeredAtSpace.length()){
					res = centeredAtSpace;
				}
			}
		}
		return res;
	}
	
	public static String getLongestPalindromeCenteredAt(String s, int leftCenter, int rightCenter) {
		String longestPalindrome = "";
		int i = leftCenter;
		int j = rightCenter;
		while(i >= 0 && j < s.length() && i <= j) {
			if(s.charAt(i) != s.charAt(j)) {
				break;
			}
			i--;
			j++;
		}
		longestPalindrome = s.substring(i + 1, j);
		return longestPalindrome;
	}
}

class LinearTimeSolution implements Solution {
	public static Logger logger = Logger.getLogger("org.swan.algorithm.common.LinearTimeSolution");
	
	@Override
	public String solve(String s) {
		String t = preprocess(s);
		
		int[] P = new int[t.length()]; //length of palindrome in s
		int C = 0, R = 0;
		for(int i = 1; i < t.length() - 1; i++){ //exclude ^ and $
			int i_mirror = 2 * C - i; //C-i_mirror = i - C
			P[i] = (R > i) ? Math.min(R - i, P[i_mirror]) : 0;
			
			//try to expand palindrome centered at i
			int rBound = i + P[i];
			int lBound = i - P[i];
			while(t.charAt(rBound + 1) == t.charAt(lBound - 1)) {
				P[i]++;
				rBound = i + P[i];
				lBound = i - P[i];				
			}
			
			//if palindrome centered at i expands past R, adjust 
			//center basedon expanded palindrome
			if(rBound > R){
				C = i;
				R = rBound;
			}
		}
		//find max in P
		int maxLength = 0; //max length of palindrome in s
		int centerIndex = 0; //center in t
		for(int i = 1; i < t.length() - 1; i++){
			if(P[i] > maxLength) {
				maxLength = P[i];
				centerIndex = i;
			}
		}
		
		int beginIndex = (centerIndex - 1 - maxLength) / 2;
		int endIndex = (centerIndex - 3 + maxLength) / 2;
		String res =  s.substring(beginIndex, endIndex + 1);
		return res;
	}
	
	private static String preprocess(String s){
		logger.info("input: " + s);
		if(s.length() == 0) {
			return "^$";
		}
		String res = "^";
		for (int i = 0; i < s.length(); i++) {
			res += "#";
			res += s.charAt(i);
		}
		res += "#$";
		logger.info("output: " + res);
		return res;
	}
}
