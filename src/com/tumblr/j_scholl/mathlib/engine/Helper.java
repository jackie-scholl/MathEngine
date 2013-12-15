package com.tumblr.j_scholl.mathlib.engine;

import java.util.*;

import com.tumblr.j_scholl.mathlib.engine.abstract_functions.Function;
import com.tumblr.j_scholl.mathlib.engine.functions.*;

public class Helper {
	public static final boolean DEBUG_PRINT_DIFFERENTIALS = true;
	public static final boolean DEBUG_PRINT_PRODUCTS_SUMS = true;
	
	public static final Function E = constant(Math.E);
	public static final Function PI = constant(Math.PI);
	public static final Function ZERO = constant(0.0);
	public static final Function ONE = constant(1.0);
	public static final Function NEGATIVE_ONE = constant(-1.0);
	
	public static void main(String... args) {
		Function f = varFunction();
		System.out.println(f.eval(3.0));
		
		Function g = polynomial(1.0, -3.0, 2.0);
		System.out.println(g);
		System.out.println(g.eval(0.0));
		System.out.println(g.eval(1.0));
		System.out.println(g.diff());
	}
	
	public static Function constant(double x) {
		return ConstantFunction.create(x);
	}
	
	public static double constValue(Function f) {
		ConstantFunction f2 = (ConstantFunction) f;
		return f2.value();
	}
	
	public static Function varFunction() {
		return VariableFunction.create();
	}
	
	public static Function varPowFunction(double pow) {
		return power(varFunction(), constant(pow));
	}
	
	public static Function sum(Function... funcs) {
		return SumFunction.create(Arrays.asList(funcs));
	}
	
	public static Function sum(List<Function> funcs) {
		return SumFunction.create(funcs);
	}
	
	/*public static Function sum(Function f, Function g) {
		return SumFunction.create(f, g);
	}*/
	
	public static Function difference(Function f, Function g) {
		return sum(f, negative(g));
	}
	
	public static Function negative(Function f) {
		return NegativeFunction.create(f);
	}
	
	public static Function product(Function... funcs) {
		return ProductFunction.create(Arrays.asList(funcs));
	}
	
	/*public static Function product(Function f, Function g) {
		return ProductFunction.create(f, g);
	}*/
	
	public static Function quotient(Function f, Function g) {
		return product(f, inverse(g));
	}
	
	public static Function inverse(Function f) {
		return ExponentiationFunction.create(f, NEGATIVE_ONE);
	}
	
	public static Function power(Function f, Function g) {
		return ExponentiationFunction.create(f, g);
	}
	
	public static Function ln(Function f) {
		return NaturalLogFunction.create(f);
	}
	
	public static Function polynomial(double... coefficients) {
		int len = coefficients.length;
		Function[] funcs = new Function[len];
		for (int i = 0; i < len; i++) {
			funcs[i] = product(constant(coefficients[i]), varPowFunction(len - i - 1));
		}
		return sum(funcs);
	}
}
