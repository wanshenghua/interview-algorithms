package org.swan.algorithm.MaxPointsOnALine;

import java.util.HashMap;
import java.util.Map;

public class Problem {
	public static void main(String[] args){
	/*	Point p1 = new Point(84, 250);
		Point p2 = new Point(0, 0);
		Point p3 = new Point(1, 0);
		Point p4 = new Point(0, -70);
		Point p5 = new Point(0, -70);
		Point p6 = new Point(1, -1);
		Point p7 = new Point(21, 10);
		Point p8 = new Point(42, 90);
		Point p9 = new Point(-42, -230);*/
		
		//Point[] points = new Point[] {p1, p2, p3, p4, p5, p6, p7, p8, p9};
		
		Point p1 = new Point(2, 3);
		Point p2 = new Point(3, 3);
		Point p3 = new Point(-5, 3);
		
		Point[] points = new Point[] {p1, p2, p3};
		System.out.println(Solution.maxPoints(points));
	}
}

class Point{
	int x;
	int y;
	Point() {x = 0; y = 0;}
	Point(int a, int b) {x = a; y = b;}
}

class LineNormal {
	Point normal;
	
	public LineNormal(Point p0, Point p1) {
		normal = new Point();
		normal.x = p0.y - p1.y;
		normal.y = p1.x - p0.x;
	}
	
	Point getNormal() { return normal; }
	
	@Override
	public boolean equals(Object aLineNormal) {
		if(aLineNormal == null && this != null) { return false; }
		if(aLineNormal.getClass() != this.getClass()) { return false; }
		
		LineNormal lineNormal = (LineNormal) aLineNormal;
		if( - normal.y * lineNormal.getNormal().x + normal.x * lineNormal.getNormal().y == 0 ){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(normal.x == 0) {
			return Integer.MAX_VALUE;
		}
		return Double.valueOf(normal.y / normal.x).hashCode();
	}
}

class Solution {
	public static int maxPoints(Point[] points) {
		
		int maxNumberOfColinearPoints = 0;
		for(int i = 0; i < points.length; i++){
			Map<LineNormal, Integer> colinearPointsCenteredAtI = new HashMap<>();
			int numberOfDuplicatePointsI = 0;
			int maxNumberOfColinearPointsCenteredAtI = 1;

			for(int j = i + 1; j < points.length; j++) {
				if(points[i].x == points[j].x && points[i].y == points[j].y) {
					numberOfDuplicatePointsI++;
					continue;
				}
				LineNormal normal = new LineNormal(points[i], points[j]);

								
				if(!colinearPointsCenteredAtI.containsKey(normal)) {
					colinearPointsCenteredAtI.put(normal, 2);
				}else{
					Integer newNum = colinearPointsCenteredAtI.get(normal) + 1;
					colinearPointsCenteredAtI.put(normal, newNum);
				}
				
				if(maxNumberOfColinearPointsCenteredAtI < colinearPointsCenteredAtI.get(normal)) {
					maxNumberOfColinearPointsCenteredAtI = colinearPointsCenteredAtI.get(normal);
				}
			}
			
			int maxtNumberOfCollinearPointsCenteredAtI = maxNumberOfColinearPointsCenteredAtI + numberOfDuplicatePointsI;
			if(maxNumberOfColinearPoints < maxtNumberOfCollinearPointsCenteredAtI){
				maxNumberOfColinearPoints = maxtNumberOfCollinearPointsCenteredAtI;
			}
		}
		
		return maxNumberOfColinearPoints;
	}
}
