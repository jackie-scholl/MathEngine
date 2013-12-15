package com.tumblr.j_scholl.mathlib.engine;

import java.util.Arrays;
import java.util.List;

import com.tumblr.j_scholl.mathlib.engine.functions.*;

public class Helper {
	public static final boolean DEBUG_PRINT_DIFFERENTIALS = false;
	public static final boolean DEBUG_PRINT_PRODUCTS_SUMS = false;
	
	public static final Function E = constant(Math.E);
	public static final Function PI = constant(Math.PI);
	public static final Function ZERO = constant(0.0);
	public static final Function ONE = constant(1.0);
	public static final Function NEGATIVE_ONE = constant(-1.0);
	
	public static void main(String... args) {
		Function f = var();
		System.out.println(f.eval(3.0));
		
		Function g = polynomial(1.0, -3.0, 2.0);
		System.out.println(g);
		System.out.println(g.eval(0.0));
		System.out.println(g.eval(1.0));
		System.out.println(g.diff());
		
		System.out.println("-------------------------");
		
		//Function h = quotient(polynomial(1.0, -3.0, 2.0), polynomial(2, 4));
		/*
		 * f(x) = (x-3)/(x-2)
		 * f'(x) = [(x-2) - (x-3)]/(x-2)^2
		 * 		 = 1/(x-2)^2
		 */
		Function h = quotient(polynomial(1.0, -3.0), polynomial(1, -2));
		System.out.println(h);
		System.out.println(h.eval(0.0));
		System.out.println(h.eval(-0.5));
		System.out.println(h.diff());
		
		Function i = power(var(), var());
		System.out.println(i);
		System.out.println(i.diff());
		
		System.out.println("-------------------------");
		
		Function a = tan(var());
		System.out.println(a);
		System.out.println(a.diff());
		
		System.out.println("-------------------------");
		
		Function b = ln(tan(product(var, constant(2))));
		System.out.println(b);
		System.out.println(b.diff());
		
		System.out.println("-------------------------");
		
		Function c = tan(product(var, constant(2)));
		System.out.println(c);
		System.out.println(c.diff());
	}
	
	public static Function constant(double x) {
		return ConstantFunction.create(x);
	}
	
	public static double constValue(Function f) {
		ConstantFunction f2 = (ConstantFunction) f;
		return f2.value();
	}
	
	private static final Function var = VariableFunction.create();
	
	public static Function var() {
		return var;
	}
	
	public static Function varPowFunction(double pow) {
		return power(var(), constant(pow));
	}
	
	public static Function sum(Function... funcs) {
		return SumFunction.create(Arrays.asList(funcs));
	}
	
	public static Function sum(List<Function> funcs) {
		return SumFunction.create(funcs);
	}
	
	public static Function difference(Function f, Function g) {
		return sum(f, negative(g));
	}
	
	public static Function negative(Function f) {
		return NegativeFunction.create(f);
	}
	
	public static Function product(Function... funcs) {
		return ProductFunction.create(Arrays.asList(funcs));
	}
	
	public static Function quotient(Function f, Function g) {
		return product(f, inverse(g));
	}
	
	public static Function inverse(Function f) {
		return ExponentiationFunction.create(f, NEGATIVE_ONE);
	}
	
	public static Function power(Function base, Function exp) {
		return ExponentiationFunction.create(base, exp);
	}
	
	public static Function power(Function base, double exp) {
		return power(base, constant(exp));
	}
	
	public static Function ln(Function f) {
		return NaturalLogFunction.create(f);
	}
	
	public static Function polynomial(double... coefficients) {
		int len = coefficients.length;
		Function[] funcs = new Function[len];
		for (int i = 0; i < len; i++) {
			int exp = len - i - 1;
			funcs[i] = product(constant(coefficients[i]), power(var(), exp));
		}
		return sum(funcs);
	}
	
	public static Function sin(Function f) {
		return SinFunction.create(f);
	}
	
	public static Function cos(Function f) {
		return CosFunction.create(f);
	}
	
	public static Function tan(Function f) {
		return quotient(sin(f), cos(f));
		//return TanFunction.create(f);
	}
	
	public static Function sec(Function f) {
		return inverse(cos(f));
	}
	
	public static Function csc(Function f) {
		return inverse(sin(f));
	}
}
