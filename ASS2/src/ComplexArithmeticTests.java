/*
 * A.Manjunath CS10B001
 * */
import static org.junit.Assert.*;
import java.lang.Math;

import org.junit.Test;


public class ComplexArithmeticTests {

	@Test
	public void testRe() {
		Complex z = new Complex(5,4);
		assertEquals( z.Re(), 5.0, 0.01 );
	}

	@Test
	public void testIm() {
		Complex z = new Complex(5,4);
		assertEquals( z.Im(), 4.0, 0.01 );
	}

	@Test
	public void testAbs() {
		Complex z = new Complex(3,4);
		assertEquals( z.Abs(), 5.0, 0.01 );
	}

	@Test
	public void testArg() {
		Complex z = new Complex(3,4);
		assertEquals( z.Arg(), Math.acos(3.0/5.0), 0.01 );
	}

	@Test
	public void testConj() {
		Complex z = new Complex(3,4);
		IComplex y = z.conj();
		assertEquals( y.Re(), z.Re(), 0.01 );
		assertEquals( y.Im(), -z.Im(), 0.01 );
	}

	@Test
	public void testLog() {
		Complex z = new Complex(Math.cos(0.3), Math.sin(0.3));
		IComplex y = z.log();
		assertEquals( y.Re(), 0, 0.01 );
		assertEquals( y.Im(), 0.3, 0.01 );
	}

	@Test
	public void testExp() {
		Complex z = new Complex(0,0.3);
		IComplex y = z.exp();
		assertEquals(y.Abs(), 1, 0.01);
		assertEquals( y.Arg(), 0.3, 0.01 );
	}

	@Test
	public void testRotate() {
		Complex z = new Complex(3,4);
		IComplex y = z.rotate(0.2);
		assertEquals( y.Abs(), z.Abs(), 0.01 );
		assertEquals( y.Arg(), z.Arg() + 0.2, 0.01 );
	}

	@Test
	public void testStretch() {
		Complex z = new Complex(3,4);
		IComplex y = z.stretch(2);
		assertEquals( y.Abs(), 2*z.Abs(), 0.01 );
		assertEquals( y.Arg(), z.Arg(), 0.01 );
	}

	@Test
	public void testCompareTo() {
		Complex z = new Complex(3,4);
		Complex y = new Complex(5,6);
		IComplex x = new Complex(3,-4).conj();
		assertTrue( y.compareTo(z) == 1 );
		assertTrue( z.compareTo(y) == -1 );
		assertTrue( z.compareTo(x) == 0 );
	}
}
