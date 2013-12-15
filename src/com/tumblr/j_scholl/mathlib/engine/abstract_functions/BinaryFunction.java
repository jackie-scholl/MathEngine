package com.tumblr.j_scholl.mathlib.engine.abstract_functions;

import static com.tumblr.j_scholl.mathlib.engine.Helper.DEBUG_PRINT_DIFFERENTIALS;

public abstract class BinaryFunction extends BaseFunction {
	protected final Function f;
	protected final Function g;
	
	protected BinaryFunction(Function first, Function second) {
		this.f = first;
		this.g = second;
	}
	
	public abstract double eval(double x);
	
	protected abstract Function diff2();
	
	protected void printDiff() {
		if (DEBUG_PRINT_DIFFERENTIALS)
			System.out.printf("Differentiating; type=%s, f=(%s), g=(%s)%n", this.getClass().getSimpleName(), f, g);
	}
	
	public int compareTo(Function other) {
		if (getClass() != other.getClass())
			return super.compareTo(other);
		BinaryFunction o = (BinaryFunction) other;
		int a = f.compareTo(o.f);
		if (a != 0)
			return a;
		return g.compareTo(o.g);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
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
		BinaryFunction other = (BinaryFunction) obj;
		if (f == null) {
			if (other.f != null)
				return false;
		} else if (!f.equals(other.f))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		return true;
	}
	
}
