package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.BaseFunction;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.UnaryFunction;

public class VariableFunction extends BaseFunction {
	private static final VariableFunction instance = new VariableFunction();
	
	public static Function create() {
		return instance;
	}
	
	private VariableFunction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double eval(double x) {
		return x;
	}
	
	@Override
	protected Function diff2() {
		return constant(1);
	}
	
	public String toString() {
		return String.format("x");
	}
	
	public int compareTo(Function other) {
		if (getClass() != other.getClass())
			return super.compareTo(other);
		VariableFunction o = (VariableFunction) other;
		return 0;
	}
	
	@Override
	public int hashCode() {
		return 17;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
