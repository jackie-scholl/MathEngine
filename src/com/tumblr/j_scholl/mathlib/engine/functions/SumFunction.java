package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.BinaryFunction;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;

public class SumFunction extends BinaryFunction {
	public static Function create(List<Function> functions) {
		if (functions.size() == 1) {
			return functions.get(0);
		}
		
		List<Function> funcs = new ArrayList<Function>(functions);
		double constant = 0.0;
		for (int i = 0; i < funcs.size(); i++) {
			Function f = funcs.get(i);
			if (f.equals(ZERO)) {
				funcs.remove(i--);
			} else if (f instanceof SumFunction) {
				funcs.remove(i--);
				SumFunction f2 = (SumFunction) f;
				funcs.add(f2.f);
				funcs.add(f2.g);
			} else if (f instanceof ConstantFunction) {
				funcs.remove(i--);
				constant = apply(constant, constValue(f));
			}
		}
		
		funcs.add(constant(constant));
		
		Collections.sort(funcs);
		
		Function res = ZERO;
		for (Function f : funcs)
			res = create(res, f);
		
		if (DEBUG_PRINT_PRODUCTS_SUMS)
			System.out.printf("Sum %s -> %s%n", funcs, res);
		
		return res;
	}
	
	private static Function create(Function f, Function g) {
		if (f.equals(ZERO))
			return g;
		if (g.equals(ZERO))
			return f;
		if (f instanceof ConstantFunction && g instanceof ConstantFunction)
			return constant(apply(constValue(f), constValue(g)));
		
		return new SumFunction(f, g);
	}
	
	private static double apply(double a, double b) {
		return a + b;
	}
	
	private SumFunction(Function first, Function second) {
		super(first, second);
	}
	
	@Override
	public double eval(double x) {
		return apply(f.eval(x), g.eval(x));
	}
	
	@Override
	protected Function diff2() {
		printDiff();
		Function fp = f.diff();
		Function gp = g.diff();
		return sum(fp, gp);
	}
	
	public String toString() {
		String fs = needsParens(f) ? "(" + f + ")" : f.toString();
		String gs = needsParens(f) ? "(" + g + ")" : g.toString();
		return String.format("%s+%s", fs, gs);
		//return String.format("[%s+%s]", fs, gs);
	}
	
	private boolean needsParens(Function f) {
		//if (f instanceof NegativeFunction)
		//	return true;
		/*if (f instanceof ConstantFunction) {
			ConstantFunction f2 = (ConstantFunction) f;
			return f2.value() < 0.0;
		}*/
		return false;
	}
}

/*public int compareTo(SumFunction o) {
	int a = f.compareTo(o.f);
	if (a != 0)
		return a;
	return g.compareTo(o.g);
}*/
/*Comparator<Function> sumComp = new Comparator<Function>() {
	public int compare(Function a, Function b) {
		if (a instanceof ConstantFunction && b instanceof ConstantFunction)
			return Double.compare(constValue(a), constValue(b));
		if (a instanceof ConstantFunction)
			return -1;
		if (b instanceof ConstantFunction)
			return 1;
		if (a instanceof VariableFunction && b instanceof VariableFunction)
			
		
		return 0;
	}
};*/

/*Function f2 = f.simplify();
Function g2 = g.simplify();

if (f2.equals(ZERO))
	return g2;
if (g2.equals(ZERO))
	return f2;
if (f2 instanceof ConstantFunction && g2 instanceof ConstantFunction)
	return constant(eval(0));

if (f.equals(f2) && g.equals(g2))
	return this;
else
	return sum(f2, g2);*/
