package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;

import java.util.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.BinaryFunction;
import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;

public class ProductFunction extends BinaryFunction {
	public static Function create(List<Function> functions) {
		List<Function> funcs = new ArrayList<Function>(functions);
		
		Map<Function, List<Function>> expMap = new HashMap<>();
		
		double constant = 1.0;
		for (int i = 0; i < funcs.size(); i++) {
			Function f = funcs.remove(i--);
			if (f.equals(ONE)) {
				//funcs.remove(i--);
			} else if (f instanceof ProductFunction) {
				//funcs.remove(i--);
				ProductFunction f2 = (ProductFunction) f;
				funcs.add(f2.f);
				funcs.add(f2.g);
			} else if (f instanceof ConstantFunction) {
				//funcs.remove(i--);
				constant = apply(constant, constValue(f));
			} else if (f instanceof NegativeFunction) {
				constant = -constant;
				funcs.add(negative(f));
			} else {
				//funcs.remove(i--);
				Function base = f;
				Function exp = ONE;
				if (base instanceof ExponentiationFunction) {
					ExponentiationFunction f2 = (ExponentiationFunction) base;
					base = f2.base();
					exp = f2.exponent();
				}
				List<Function> exps = expMap.get(base);
				if (exps == null)
					exps = new ArrayList<Function>();
				exps.add(exp);
				expMap.put(base, exps);
			}
		}
		
		for (Function base : expMap.keySet()) {
			List<Function> exps = expMap.get(base);
			Function totalExp = sum(exps);
			Function powerFunc = power(base, totalExp);
			funcs.add(powerFunc);
		}
		
		funcs.add(constant(constant));
		
		Collections.sort(funcs);
		
		Function res = ONE;
		for (Function f : funcs)
			res = create(res, f);
		
		if (DEBUG_PRINT_PRODUCTS_SUMS)
			System.out.printf("Product %s -> %s%n", funcs, res);
		
		return res;
	}
	
	private static Function create(Function f, Function g) {
		if (f.equals(ZERO) || g.equals(ZERO))
			return ZERO;
		if (f.equals(ONE))
			return g;
		if (g.equals(ONE))
			return f;
		if (f instanceof ConstantFunction && g instanceof ConstantFunction)
			return constant(apply(constValue(f), constValue(g)));
		
		return new ProductFunction(f, g);
	}
	
	private static double apply(double a, double b) {
		return a * b;
	}
	
	public ProductFunction(Function first, Function second) {
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
		
		return sum(product(fp, g), product(f, gp)); // Chain rule: (f*g)' = f'*g + f*g'
	}
	
	public String toString() {
		return String.format("%s*%s", getString(f), getString(g));
	}
	
	private String getString(Function f) {
		String fs = needsParens(f) ? "(" + f + ")" : f.toString();
		return fs;
	}
	
	private boolean needsParens(Function f) {
		return f instanceof SumFunction;
		//return true;
		//return !(f instanceof ConstantFunction || f instanceof VariableFunction);
	}
	
}

//String fs = getString(f);
//String gs = needsParens(f) ? "(" + g + ")" : g.toString();
/*if (f instanceof ExponentiationFunction) {
ExponentiationFunction f2 = (ExponentiationFunction) f;
Function base = f2.base();
Function exp = f2.exponent();
if (exp instanceof ConstantFunction) {
	double exp2 = constValue(exp);
	if (exp2 < 0) {
		fs = String.format("1/(%s)", power(base, -exp2));
	}
}
}*/
/* else if (f instanceof ExponentiationFunction) {
//funcs.remove(i--);
ExponentiationFunction f2 = (ExponentiationFunction) f;
Function base = f2.base();
Function exp = f2.exponent();
List<Function> exps = expMap.get(base);
if (exps == null)
	exps = new ArrayList<Function>();
exps.add(exp);
expMap.put(base, exps);
}*/

//return String.format("[P %s*%s]", fs, gs);

/*for (int i=0; i < funcs.size(); i++) {
	
}*/

//System.out.println("Product " + funcs);