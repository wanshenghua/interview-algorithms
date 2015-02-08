package org.swan.algorithm.LongestValidParentheses;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Logger;

import org.swan.common.Test.TestCase;

public class Problem {
	public static Logger logger = Logger.getLogger("org.swan.algorithm.common.LongestPalindromicSubstring.Problem");
	
	public static ArrayList<TestCase<String, Integer>> problemTestSuites = new ArrayList<>();
	
	public static void main(String[] arg){
		logger.info("creating test suites");
		createTestSuites();
		TestSuites<String, Integer> testSuites = new TestSuites<>(problemTestSuites);
		TestRunner<String, Integer> testRunner = new TestRunner<>();
		
/*		LinearTimeDPSolution linearTimeDPSolution = new LinearTimeDPSolution();
		if (testRunner.testSolution(linearTimeDPSolution, testSuites)) {
			logger.info("Linear Time DP Solution Test Cases : PASSED");
		} else {
			logger.info("Linear Time DP Solution Test Cases : FAILED");
		}*/
		
		LinearTimeStackSolution linearTimeStackSolution = new LinearTimeStackSolution();
		if (testRunner.testSolution(linearTimeStackSolution, testSuites)) {
			logger.info("Linear Time Stack Solution Test Cases : PASSED");
		} else {
			logger.info("Linear Time Stack Solution Test Cases : FAILED");
		}
	}
	
	private static void createTestSuites(){
		TestCase<String, Integer> c = new TestCase<>(null, 0);
		problemTestSuites.add(c);
		TestCase<String, Integer> c0 = new TestCase<>("", 0);
		problemTestSuites.add(c0);
		TestCase<String, Integer> c1 = new TestCase<>("(", 0);
		problemTestSuites.add(c1);
		TestCase<String, Integer> c2 = new TestCase<>("()", 1*2);
		problemTestSuites.add(c2);
		TestCase<String, Integer> c3 = new TestCase<>("(())", 2*2);
		problemTestSuites.add(c3);
		TestCase<String, Integer> c4 = new TestCase<>("(())()()", 4*2);
		problemTestSuites.add(c4);		
		TestCase<String, Integer> c5 = new TestCase<>("(())()(())", 5*2);
		problemTestSuites.add(c5);
		TestCase<String, Integer> c6 = new TestCase<>("(()", 2);
		problemTestSuites.add(c6);	
	}
}

class LinearTimeDPSolution implements Solution<String, Integer> {
	public Logger logger;
	
	public LinearTimeDPSolution(){
		logger = Logger.getLogger(this.getClass().toString());
	}
	
	/**
	 * DP O(n) time, O(n) space
	 */
	@Override
	public Integer solve(String s) {
		if(s == null || s.length() == 0) { //boilerplate
			return Integer.valueOf(0);
		}
		
		int maxLength = 0;
		int[] d = new int[s.length()]; //d[i] is length of longest valid parentheses in s[i ... len-1]
		d[s.length() - 1] = 0;
		for(int i = s.length() - 2; i >= 0; i--){
			if(s.charAt(i) == ')') {
				d[i] = 0;
			}else if(s.charAt(i) == '(') {
				int j = i + d[i + 1] + 1;
				if(j < s.length() && s.charAt(j) == ')'){
					d[i] = d[i + 1] + 2;
					if(j + 1 < s.length()){//more trailing valid parentheses after s[i...j]
						d[i] += d[j + 1];
					}
				}					
			}
			maxLength = (maxLength < d[i]) ? d[i] : maxLength;
		}
		return Integer.valueOf(maxLength);
	}
}

class LinearTimeStackSolution implements Solution<String, Integer> {
	public Logger logger;
	
	public LinearTimeStackSolution(){
		logger = Logger.getLogger(this.getClass().toString());
	}
	
	/**
	 * Stack O(n) time, O(n) space
	 */
	@Override
	public Integer solve(String s) {
		if(s == null || s.length() == 0) { //boilerplate
			return Integer.valueOf(0);
		}
		int last = -1; //previous ')'
		int maxLength = 0;
		Stack<Integer> stack = new Stack<>(); //maintain index for leftmost '('
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				stack.push(i);
			}else {
				if(stack.isEmpty()){
					last = i; //when the stack is empty, a record for the previous ')' is kept.
				}else {
					stack.pop();
					if(stack.isEmpty()) {
						//empty stack, current length of valid parentheses is i - last, starting from the char after last ')'
						maxLength = Math.max(i - last, maxLength);
					}else {
						//non-empty stack, current length of valid parentheses is i - stack.peek()
						maxLength = Math.max(i - stack.peek(), maxLength);
					}
				}
			}			
		}
		return Integer.valueOf(maxLength);
	}
}


/**
 * Boiler plate code
 * @author SHENGHUA
 *
 * @param <I>
 * @param <O>
 */
class TestRunner <I, O> {
	public static Logger logger = Logger.getLogger("org.swan.algorithm.common.TestRunner");
	
	public boolean testSolution(Solution<I, O> sol, TestSuites<I, O> testSuites){
		for(TestCase<I, O> testCase : testSuites.getTestSuites()){
			long startTime = System.nanoTime();
			O output = sol.solve(testCase.getInput());
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

interface Solution <I, O>{
	O solve(I s);
}

class TestSuites <I, O>{
	private ArrayList<TestCase<I, O>> testSuites = new ArrayList<>();	
	
	public TestSuites(ArrayList<TestCase<I, O>> aTestSuites){
		testSuites = aTestSuites;
	}
	
	public ArrayList<TestCase<I, O>> getTestSuites() {
		return testSuites;
	}
}