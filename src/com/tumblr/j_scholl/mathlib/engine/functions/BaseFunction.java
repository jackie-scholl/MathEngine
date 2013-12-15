package com.tumblr.j_scholl.mathlib.engine.functions;

import java.util.*;

import static com.tumblr.j_scholl.mathlib.engine.Helper.*;
import com.tumblr.j_scholl.mathlib.engine.functions.*;

public abstract class BaseFunction implements Function {
	protected Function derivative = null;
	
	public abstract double eval(double x);
	
	public Function diff() {
		if (derivative == null)
			derivative = diff2();
		if (DEBUG_PRINT_DIFFERENTIALS)
			System.out.printf("Differentiated; type=%s, f=(%s), f'=(%s)%n", this.getClass().getSimpleName(), this,
					derivative);
		return derivative;
	}
	
	protected abstract Function diff2();
	
	public Function add(Function other) {
		return sum(this, other);
	}
	
	public Function multiply(Function other) {
		return product(this, other);
	}
	
	public int compareTo(Function other) {
		if (this.getClass() == other.getClass()) {
			String str = String.format(
					"Should be calling other method; this is \"%s\" of class %s and other is \"%s\" of class %s", this,
					this.getClass().toString(), other, other.getClass().toString());
			throw new RuntimeException(str);
		}
		return FunctionComparator2.funcCompare(this, other);
	}
}

class FunctionComparator2 {
	private static Map<Class<? extends Function>, Integer> classOrder;
	
	static {
		List<Class<? extends Function>> list = new ArrayList<>();
		list.add(ConstantFunction.class);
		list.add(VariableFunction.class);
		list.add(SinFunction.class);
		list.add(CosFunction.class);
		list.add(TanFunction.class);
		list.add(ExponentiationFunction.class);
		list.add(NaturalLogFunction.class);
		list.add(InverseFunction.class);
		list.add(ProductFunction.class);
		list.add(NegativeFunction.class);
		list.add(SumFunction.class);
		
		classOrder = new HashMap<>();
		for (int i = 0; i < list.size(); i++)
			classOrder.put(list.get(i), i);
	}
	
	public static int funcCompare(Function a, Function b) {
		if (a.getClass() == b.getClass())
			throw new RuntimeException("Should be calling other method");
		//System.out.printf("a is \"%s\" of class %s and b is \"%s\" of class %s%n", a, a.getClass().getSimpleName(), b,
		//		b.getClass().getSimpleName());
		int ao = classOrder.get(a.getClass());
		int bo = classOrder.get(b.getClass());
		int c = Integer.compare(ao, bo);
		/*System.out.printf("a is \"%s\" of class %s and b is \"%s\" of class %s; a %c b%n", a, a.getClass()
				.getSimpleName(), b,
				b.getClass().getSimpleName(), c < 0 ? '<' : c == 0 ? '=' : '>');*/
		return c;
	}
}

/*public Function simplify() {
	return this;
}*/
