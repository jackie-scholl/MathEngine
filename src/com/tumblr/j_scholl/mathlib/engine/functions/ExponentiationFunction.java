package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.BinaryFunction;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;

public class ExponentiationFunction extends BinaryFunction {
	public static Function create(Function base, Function exp) {
		if (base.equals(ZERO))
			return ZERO;
		if (base.equals(ONE))
			return ONE;
		if (exp.equals(ZERO))
			return ONE;
		if (exp.equals(ONE))
			return base;
		if (base instanceof ConstantFunction && exp instanceof ConstantFunction)
			return constant(apply(constValue(base), constValue(exp)));
		
		return new ExponentiationFunction(base, exp);
	}
	
	private static double apply(double a, double b) {
		return Math.pow(a, b);
	}
	
	private ExponentiationFunction(Function base, Function exponent) {
		super(base, exponent);
	}
	
	protected Function base() {
		return f;
	}
	
	protected Function exponent() {
		return g;
	}
	
	@Override
	public double eval(double x) {
		return apply(f.eval(x), g.eval(x));
	}
	
	@Override
	/*
	 * a(x) = f(x)^g(x)
	 * ln(a(x)) = g(x) * ln f(x)
	 * a'(x) / a(x) = g'(x)*ln(f(x)) + g(x)*f'(x)/f(x)
	 * 
	 * a'(x) = a(x)*g'(x)ln(f(x)) + a(x)*g(x)*f'(x)/f(x)
	 */
	protected Function diff2() {
		printDiff();
		//Function fp = f.diff();
		//Function gp = g.diff();
		//printDiff(fp, gp);
		
		Function pt1 = product(this, g.diff(), ln(f));
		Function pt2 = product(this, g, f.diff(), inverse(f));
		return sum(pt1, pt2);
	}
	
	public String toString() {
		String fs = needsParens(f) ? "(" + f + ")" : f.toString();
		String gs = needsParens(f) ? "(" + g + ")" : g.toString();
		return String.format("%s^%s", fs, gs);
	}
	
	private boolean needsParens(Function f) {
		return !(f instanceof ConstantFunction || f instanceof VariableFunction);
	}
	
	/*if (f2.equals(ZERO))
		return ZERO;
	if (f2.equals(ONE))
		return ONE;
	if (g2.equals(ZERO))
		return ONE;
	if (g2.equals(ONE))
		return f2;
	
	if (f2 instanceof ConstantFunction && g2 instanceof ConstantFunction)
		return constant(this.eval(0));*/
}
