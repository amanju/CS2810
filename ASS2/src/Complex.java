/*
 * A.Manjunath CS10B001
 * */

public class Complex implements IComplex, Comparable<IComplex> {
	private double Re; 		// real part of the Complex number
	private double Im;		// imaginary part of the Complex number
	private double Arg;		// argument of the Complex number
	private double Abs;		// absolute value of the Complex number

	public Complex(int i, int j) {
		Re = i;
		Im = j;
		Abs = Math.sqrt(Re * Re + Im * Im);
		Arg = Math.asin(Im / Abs);
	}

	public Complex(double a, double b) {
		Re = a;
		Im = b;
		Abs = Math.sqrt(Re * Re + Im * Im);
		Arg = Math.asin(Im / Abs);
	}

	// Returns Real part of the complex number
	public double Re() {
		return Re;
	}

	// Returns Imaginary part of the complex number
	public double Im() {
		return Im;
	}

	// returns absolute value of the complex number
	public double Abs() {
		return Abs;
	}

	// returns argument of the complex number
	public double Arg() {
		return Arg;
	}

	// returns complex complex conjugate of the complex number
	public IComplex conj() {
		Complex conj = new Complex(Re, -Im);
		return conj;
	}

	// returns log of the complex number
	public IComplex log() {
		Complex log = new Complex(Math.log(Abs), Arg);
		return log;
	}

	// returns exponential power of the complex number
	public IComplex exp() {
		Complex exp = new Complex(Math.exp(Re) * Math.cos(Im), Math.exp(Re)
				* Math.sin(Im));
		return exp;
	}

	// returns the complex number rotated by 'radians' radians
	public IComplex rotate(double radians) {
		Complex rotated = new Complex(Abs * Math.cos(Arg + radians), Abs
				* Math.sin(Arg + radians));
		return rotated;
	}

	// returns the complex number stretched by 'scale' length
	public IComplex stretch(double scale) {
		Complex stretched = new Complex(scale * Re, scale * Im);
		return stretched;
	}

	public int compareTo(IComplex x) {
		if (Abs == x.Abs())
			return 0;
		else if (Abs < x.Abs())
			return -1;
		else
			return 1;
	}

}
