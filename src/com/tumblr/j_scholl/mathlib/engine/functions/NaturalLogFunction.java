package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

public class NaturalLogFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new NaturalLogFunction(f);
	}
	
	private static double apply(double x) {
		if (x <= 0.0)
			throw new ArithmeticException("You can't take ln of x <= 0: " + x);
		return Math.log(x);
	}
	
	private NaturalLogFunction(Function f) {
		super(f);
	}
	
	@Override
	public double eval(double x) {
		return apply(x);
	}
	
	@Override
	protected Function diff2() {
		return quotient(f.diff(), f);
	}
	
	public String toString() {
		return String.format("ln(%s)", f);
	}
	
}
