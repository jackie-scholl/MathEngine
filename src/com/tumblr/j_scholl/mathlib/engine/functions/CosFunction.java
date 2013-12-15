package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.UnaryFunction;

public class CosFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new CosFunction(f);
	}
	
	private CosFunction(Function f) {
		super(f);
	}
	
	private static double apply(double x) {
		return Math.cos(x);
	}
	
	@Override
	public double eval(double x) {
		return apply(x);
	}
	
	@Override
	protected Function diff2() {
		return product(negative(sin(f)), f.diff());
	}
	
	public String toString() {
		return String.format("cos(%s)", f);
	}
}
