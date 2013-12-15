package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

public class InverseFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof InverseFunction)
			return ((InverseFunction) f).f;
		
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new InverseFunction(f);
	}
	
	private InverseFunction(Function f) {
		super(f);
	}
	
	private static double apply(double x) {
		if (x == 0)
			throw new ArithmeticException("Division by 0");
		return 1 / x;
	}
	
	@Override
	public double eval(double x) {
		return apply(f.eval(x));
	}
	
	@Override
	protected Function diff2() {
		Function thisDiff = negative(product(this, this));
		return product(thisDiff, f.diff());
	}
	
	public String toString() {
		return String.format("1/(%s)", f);
	}
	
}