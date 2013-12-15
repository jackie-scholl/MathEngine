package com.tumblr.j_scholl.mathlib.engine.functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.constant;

public class ConstantFunction extends BaseFunction {
	private final double value;
	
	public static Function create(double x) {
		return new ConstantFunction(x);
	}
	
	private ConstantFunction(double x) {
		if (Double.isNaN(x))
			throw new ArithmeticException("NaN: " + x);
		if (Double.isInfinite(x))
			throw new ArithmeticException("Infinite: " + x);
		value = x;
	}
	
	public double value() {
		if (Double.isInfinite(value) || Double.isNaN(value))
			throw new ArithmeticException(Double.toString(value));
		return value;
	}
	
	@Override
	public double eval(double x) {
		return value;
	}
	
	@Override
	protected Function diff2() {
		return constant(0);
	}
	
	public String toString() {
		String res = String.format("%.2f", value);
		double distFromInt = Math.abs(value - Math.rint(value));
		if (distFromInt < 0.0000001) {
			long l = Math.round(value);
			res = String.format("%d", l);
		}
		if (value < 0)
			res = "(" + res + ")";
		return res;
	}
	
	public int compareTo(Function other) {
		if (getClass() != other.getClass())
			return super.compareTo(other);
		ConstantFunction o = (ConstantFunction) other;
		return Double.compare(this.value, o.value);
	}
	
	/*if (o instanceof ConstantFunction)
	return Double.compare(constValue(this), constValue(o));
	return -1;*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstantFunction other = (ConstantFunction) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
}
