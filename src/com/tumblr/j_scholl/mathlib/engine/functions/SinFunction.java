package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.UnaryFunction;

public class SinFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new SinFunction(f);
	}
	
	private SinFunction(Function f) {
		super(f);
	}
	
	private static double apply(double x) {
		return Math.sin(x);
	}
	
	@Override
	public double eval(double x) {
		return apply(x);
	}
	
	@Override
	protected Function diff2() {
		return product(cos(f), f.diff());
	}
	
	public String toString() {
		return String.format("sin(%s)", f);
	}
}
