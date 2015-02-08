package org.swan.common.Test;

import java.util.ArrayList;

public class TestSuites <I, O>{
	private ArrayList<TestCase<I, O>> testSuites = new ArrayList<>();	
	
	public TestSuites(ArrayList<TestCase<I, O>> aTestSuites){
		testSuites = aTestSuites;
	}
	
	public ArrayList<TestCase<I, O>> getTestSuites() {
		return testSuites;
	}
}
