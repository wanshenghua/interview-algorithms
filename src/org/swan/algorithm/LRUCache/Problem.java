package org.swan.algorithm.LRUCache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.swan.common.Test.TestRunner;
import org.swan.common.Test.TestSuites;
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
		
/*		LinearTimeStackSolution linearTimeStackSolution = new LinearTimeStackSolution();
		if (testRunner.testSolution(linearTimeStackSolution, testSuites)) {
			logger.info("Linear Time Stack Solution Test Cases : PASSED");
		} else {
			logger.info("Linear Time Stack Solution Test Cases : FAILED");
		}*/
	}
	
	private static void createTestSuites(){

	}
}

class LRUCache extends LinkedHashMap<Integer, Integer> {
	private int capacity;
	
    public LRUCache(int capacity) {
    	super(16, 0.75f, true);
    	this.capacity = capacity; 
    }
    
    public int get(int key) {
        if(this.containsKey(key)){
        	return this.get(Integer.valueOf(key));
        }
        return -1;
    }
    
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
    	return this.size() > this.capacity;
    }
    
    public void set(int key, int value) {
    	if(this.containsKey(key)){
    		this.remove(key);
    		this.put(key, value);
    	}else{
    		this.put(key, value);
    	}
    }
}

