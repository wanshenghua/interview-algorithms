package org.swan.common.Test;

public class TestCase<K, V> {
	K input;
	V answer;
	
	public TestCase(K in, V out){
		input = in;
		answer = out;
	}
	
	public K getInput(){
		return input;
	}
	
	public V getAnswer(){
		return answer;
	}
}
