/*
 * A.Manjunath CS10B001
 * */
public class PolarComplex implements IComplex, Comparable<IComplex> {
	private double Re; 		// real part of the Complex number
	private double Im;		// imaginary part of the Complex number
	private double Arg;		// argument of the Complex number
	private double Abs;		// absolute value of the Complex number

	public PolarComplex(int abs, double arg) {
		Arg = arg;
		Abs = abs;
		Re = Abs * Math.cos(Arg);
		Im = Abs * Math.sin(Arg);
	}

	public PolarComplex(double abs, double arg) {
		Arg = arg;
		Abs = abs;
		Re = Abs * Math.cos(Arg);
		Im = Abs * Math.sin(Arg);

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
		PolarComplex exp = new PolarComplex(Math.exp(Math.cos(Arg)),
				Math.sin(Arg));
		return exp;
	}

	// returns the complex number rotated by 'radians' radians
	public IComplex rotate(double radians) {
		PolarComplex rotated = new PolarComplex((int) Abs, Arg + radians);
		return rotated;
	}

	// returns the complex number stretched by 'scale' length
	public IComplex stretch(double scale) {
		PolarComplex stretched = new PolarComplex((int) (2 * Abs), Arg);
		return stretched;
	}

	public int compareTo(IComplex x) {
		if (x.Abs() < Abs)
			return 1;
		else if (x.Abs() > Abs)
			return -1;
		else
			return 0;
	}

}
