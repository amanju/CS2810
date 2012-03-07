/*
 * A.Manjunath CS10B001
 * */
public interface IComplex {

	public double Re();

	public double Im();

	public double Abs();

	public double Arg();

	public IComplex conj();

	public IComplex log();

	public IComplex exp();

	public IComplex rotate(double radians);

	public IComplex stretch(double scale);

}
