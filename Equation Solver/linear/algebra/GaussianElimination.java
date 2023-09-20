package linear.algebra;

public class GaussianElimination
{
    private double[][] matrix;
    private int rows;
    private int cols;

    public GaussianElimination(int rows, int cols, double[][] matrix)
    {
        this.rows = rows;
        this.cols = cols;

        this.matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                this.matrix[i][j] = matrix[i][j];
        }
    }

    private void checkMatrixDimensions(double[][] matrix)
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        
        for (int i = 1; i < numRows; i++)
        {
            if (matrix[i].length != numCols)
            {
                System.err.println("Error: Matrix dimensions do not match.");
                return;
            }
        }
    
        System.out.println("Matrix dimensions are valid.");
    }

    public double[][] getMatrix()
    {
        double[][] copyMatrix = new double[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                copyMatrix[i][j] = matrix[i][j];
        }

        return copyMatrix;
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }
    
    public void setMatrix(double[][] newMatrix)
    {
        int newNumRows = newMatrix.length;
        int newcols = newMatrix[0].length;
    
        if (newNumRows != rows || newcols != cols)  throw new IllegalArgumentException("New matrix dimensions must match original matrix");

        //matrix = newMatrix;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix[i][j] = newMatrix[i][j];
        }
    }
    
    public void rowEchelonForm()
    {
        int h = 0;
        int k = 0;
        while (h < rows && k < cols)
        {
            int i_max = h;
            double max = Math.abs(matrix[h][k]);

            for (int i = h; i < rows; i++)
            {
                double abs = Math.abs(matrix[i][k]);

                if (abs > max)
                {
                    i_max = i;
                    max = abs;
                }
            }
            
            if (matrix[i_max][k] == 0) k++;
            
            else
            {
                swapRows(h, i_max);

                for (int i = h + 1; i < rows; i++)
                {
                    double f = matrix[i][k] / matrix[h][k];

                    matrix[i][k] = 0;

                    for (int j = k + 1; j < cols; j++)
                        matrix[i][j] -= matrix[h][j] * f;
                }

                multiplyRow(h,k);

                h++;
                k++;
            }
        }
    }

    private int argMax(int rowI, int colI)
    {
        double maxAbsValue = 0;
        int maxAbsRowI = -1;

        for (int i = rowI + 1; i < rows; i++)
        {
            double absValue = Math.abs(matrix[i][colI]);

            if (absValue > maxAbsValue)
            {
                maxAbsValue = absValue;
                maxAbsRowI = i;
            }
        }

        return maxAbsRowI + 1;
    }
    
    private void swapRows(int h, int i_max) 
    {
        double[] temp = matrix[h];
        matrix[h] = matrix[i_max];
        matrix[i_max] = temp;
    }

    private void multiplyAndAddRow(int addRow, int mulRow, int colInd)
    {
        double f = matrix[addRow][colInd] / matrix[mulRow][colInd];

        for (int j = colInd; j < cols; j++)
            matrix[addRow][j] -= matrix[mulRow][j] * f;
    }

    private void multiplyRow(int rowIndx, int colIndx)
    {
        double div = matrix[rowIndx][colIndx];

        for (int j = 0; j < cols; j++)
            matrix[rowIndx][j] /= div;

    }

    public void backSubstitution()
    {
        for (int i = rows - 1; i >= 0; i--)
        {
            if (Math.abs(matrix[i][i]) < 1e-10)  throw new IllegalArgumentException("System of equation has no unique solution");

            for (int j = i - 1; j >= 0; j--)
                multiplyAndAddRow(j,i,i);
        }
    }

    public void print()
    {
        String equation = "";

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (j == 0)
                {
                    if (matrix[i][j] > 0)   equation += "+" + matrix[i][j] + "x";

                    if (matrix[i][j] < 0)   equation += matrix[i][j] + "x";
                }

                if (j == 1)
                {
                    if (matrix[i][j] > 0)   equation += "+" + matrix[i][j] + "y";

                    if (matrix[i][j] < 0)   equation += matrix[i][j] + "y";
                }

                if (j == 2)
                {
                    if (matrix[i][j] > 0)   equation += "+" + matrix[i][j] + "z";

                    if (matrix[i][j] < 0)   equation += matrix[i][j] + "z";
                }

                if (j == cols-1)
                {
                    if (matrix[i][j] > 0)   equation += "=" + matrix[i][j];

                    if (matrix[i][j] < 0)   equation += "=" + matrix[i][j];
                }
            }

            System.out.println(equation);
        }
    }
}