package org.swan.common.Test;

import java.util.logging.Logger;

import org.swan.algorithm.common.Solution;

public class TestRunner <I, O> {
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