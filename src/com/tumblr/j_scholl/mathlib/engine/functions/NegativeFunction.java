package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.UnaryFunction;

public class NegativeFunction extends UnaryFunction {
	public static Function create(Function f) {
		if (f instanceof NegativeFunction)
			return ((NegativeFunction) f).f;
		
		if (f instanceof ConstantFunction)
			return constant(apply(constValue(f)));
		return new NegativeFunction(f);
	}
	
	private NegativeFunction(Function f) {
		super(f);
	}
	
	private static double apply(double x) {
		return -x;
	}
	
	@Override
	public double eval(double x) {
		return apply(f.eval(x));
	}
	
	@Override
	protected Function diff2() {
		return create(f.diff());
	}
	
	public String toString() {
		return String.format("-(%s)", f);
	}
}
