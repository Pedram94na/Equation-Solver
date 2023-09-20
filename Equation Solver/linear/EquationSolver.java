package linear;
import linear.algebra.GaussianElimination;

class EquationSolver {
    public static void main (String[] args)
    {
        double[][] matrix = new double[args.length][];

        for (int i = 0; i < args.length; i++)
            matrix[i] = stringToDoubleMatrix(args[i])[0];
        
        GaussianElimination ge = new GaussianElimination(matrix.length, matrix[0].length, matrix);
        ge.print();
        
        System.out.println("--------------------");
        ge.rowEchelonForm();
        ge.print();

        System.out.println("--------------------");
        ge.backSubstitution();
        ge.print();
    }
    
    public static double[][] stringToDoubleMatrix(String matrix)
    {
        String[] rows = matrix.split(" ");
        double[][] doubleMatrix = new double[rows.length][];

        for (int i = 0; i < rows.length; i++)
        {
            String[] values = rows[i].split(",");
            doubleMatrix[i] = new double[values.length];

            for (int j = 0; j < values.length; j++)
                doubleMatrix[i][j] = Double.parseDouble(values[j]);
        }

        return doubleMatrix;
    }

}