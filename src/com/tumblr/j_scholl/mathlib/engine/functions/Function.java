package com.tumblr.j_scholl.mathlib.engine.functions;

public interface Function extends Comparable<Function> {
	public double eval(double x);
	
	public Function diff();
	
	public Function add(Function other);
	
	public int compareTo(Function other);
	
	public boolean equals(Object obj);
	
	public int hashCode();
}
