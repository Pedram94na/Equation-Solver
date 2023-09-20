package linear;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import linear.algebra.GaussianElimination;

public class GaussianEliminationTest {
	double[][] matrix1 = new double[][] {
        {2.0, 3.0, 5.0, 8.0},
        {1.0, 2.0, 3.0, 5.0},
        {4.0, 6.0, 8.0, 12.0} };

	double[][] matrix2 = new double[][] {
		{ 1.0, 3.0,  1.0,  9.0 },
		{ 1.0, 1.0, -1.0,  1.0 },
		{ 3.0, 11.0, 5.0, 35.0 } };

	double[][] rowEchelonForm1 = new double[][] {
        { 1.0, 1.5, 2.0, 3.0 },
        { 0.0, 1.0, 2.0, 4.0 },
        { 0.0, 0.0, 1.0, 2.0 } };

	double[][] matrix1AfterBackSubstitution = new double[][] {
		{ 1.0, 0.0, 0.0, -1.0 },
		{ 0.0, 1.0, 0.0, 0.0  },
		{ 0.0, 0.0, 1.0, 2.0  } };

	@Test
	public void testGetterSetter() {
		GaussianElimination ge = new GaussianElimination(3, 4, matrix1);
		assertEquals(3, ge.getRows());
		assertEquals(4, ge.getCols());
		assertArrayEquals(matrix1, ge.getMatrix());

		ge.setMatrix(matrix2);
		assertArrayEquals(matrix2, ge.getMatrix());
	}

	@Test
	public void testSolve() {
		GaussianElimination ge = new GaussianElimination(3, 4, matrix1);

		ge.rowEchelonForm();
		assertArrayEquals(rowEchelonForm1, ge.getMatrix());

		ge.backSubstitution();
		assertArrayEquals(matrix1AfterBackSubstitution, ge.getMatrix());
	}

	@Test
	public void badEquation() {
		GaussianElimination ge = new GaussianElimination(3, 4, matrix2);
		ge.rowEchelonForm();
		assertThrows(IllegalArgumentException.class, () -> ge.backSubstitution());
	}
}
