package org.swan.algorithm.Subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class SubsetsII {
	public static void main(String[] args) {
		SolutionSubsetsII sol = new SolutionSubsetsII();
		int[] S = new int[] { 4, 1, 0 };
		List<List<Integer>> res = sol.subsetsWithDup(S);
	}
}

class SolutionSubsetsII {
	List<List<Integer>> res = new LinkedList<List<Integer>>();
	HashMap<Integer, Integer> num2times = new HashMap<>();

	public List<List<Integer>> subsetsWithDup(int[] S) {
		for (int i : S) {
			if (num2times.containsKey(i)) {
				num2times.put(i, num2times.get(i) + 1);
			} else {
				num2times.put(i, 1);
			}
		}
		ArrayList<Integer> T = new ArrayList<>(num2times.keySet());

		Collections.sort(T);
		LinkedList<Integer> path = new LinkedList<>();
		res.add(path);
		DFSHelper(T, 0, T.size(), path);
		return res;
	}

	private void DFSHelper(ArrayList<Integer> S, int beg, int end,
			LinkedList<Integer> path) {
		if (beg == end) {
			return;
		}
		for (int i = beg; i < end; i++) {
			int times = num2times.get(S.get(i));
			for (int j = 0; j < times; j++) {
				path.addLast(S.get(i));
				res.add(new LinkedList<Integer>(path));
				DFSHelper(S, i + 1, end, path);
			}
			for (int j = 0; j < num2times.get(S.get(i)); j++) {
				path.removeLast();
			}
		}
	}
}