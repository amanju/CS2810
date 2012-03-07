/*
 * A.Manjunath CS10B001
 * */
import static org.junit.Assert.*;

import org.junit.Test;


public class PolarComplexArithmeticTestsTest {

	@Test
	public void testRe() {
		PolarComplex z = new PolarComplex(4,0.3);
		assertEquals( z.Re(), 4.0 * Math.cos(0.3), 0.01 );
	}

	@Test
	public void testIm() {
		PolarComplex z = new PolarComplex(4,0.3);
		assertEquals( z.Im(), 4.0 * Math.sin(0.3), 0.01 );
	}

	@Test
	public void testAbs() {
		PolarComplex z = new PolarComplex(4,0.3);
		assertEquals( z.Abs(), 4.0, 0.01 );
	}

	@Test
	public void testArg() {
		PolarComplex z = new PolarComplex(4,0.3);
		assertEquals( z.Arg(), 0.3, 0.01 );
	}

	@Test
	public void testConj() {
		PolarComplex z = new PolarComplex(4,0.3);
		IComplex y = z.conj();
		assertEquals( y.Re(), z.Re(), 0.01 );
		assertEquals( y.Im(), -z.Im(), 0.01 );
	}

	@Test
	public void testLog() {
		PolarComplex z = new PolarComplex(4,0.3);
		IComplex y = z.log();
		assertEquals( y.Re(), Math.log(4), 0.01 );
		assertEquals( y.Im(), 0.3, 0.01 );
	}

	@Test
	public void testExp() {
		PolarComplex z = new PolarComplex(1,0.3);
		IComplex y = z.exp();
		assertEquals( y.Abs(), Math.exp(Math.cos(0.3)), 0.01 );
		assertEquals( y.Arg(), Math.sin(0.3), 0.01 );
	}

	@Test
	public void testRotate() {
		PolarComplex z = new PolarComplex(4,0.3);
		IComplex y = z.rotate(0.2);
		assertEquals( y.Abs(), z.Abs(), 0.01 );
		assertEquals( y.Arg(), z.Arg() + 0.2, 0.01 );
	}

	@Test
	public void testStretch() {
		PolarComplex z = new PolarComplex(3,4);
		IComplex y = z.stretch(2);
		assertEquals( y.Abs(), 2*z.Abs(), 0.01 );
		assertEquals( y.Arg(), z.Arg(), 0.01 );
	}

	@Test
	public void testCompareTo() {
		PolarComplex z = new PolarComplex(3,4);
		PolarComplex y = new PolarComplex(5,6);
		IComplex x = new PolarComplex(3,-4).conj();
		assertTrue(y.compareTo(z) == 1);
		assertTrue( z.compareTo(y) == -1 );
		assertTrue( z.compareTo(x) == 0 );
	}
}
