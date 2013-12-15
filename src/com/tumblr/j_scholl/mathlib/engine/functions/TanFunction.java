package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

public class TanFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new TanFunction(f);
	}
	
	private TanFunction(Function f) {
		super(f);
	}
	
	private static double apply(double x) {
		return Math.tan(x);
	}
	
	@Override
	public double eval(double x) {
		return apply(x);
	}
	
	@Override
	protected Function diff2() {
		return product(sec(f), sec(f), f.diff());
	}
	
	public String toString() {
		return String.format("tan(%s)", f);
	}
}
